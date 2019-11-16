package com.wang.jmonkey.modules.gauge.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.wang.jmonkey.modules.gauge.model.entity.GaugeAnswerInfo;
import com.wang.jmonkey.modules.gauge.model.mo.GaugeResult;
import com.wang.jmonkey.modules.gauge.model.entity.GaugeResultQuality;
import com.wang.jmonkey.modules.gauge.mapper.GaugeResultQualityMapper;
import com.wang.jmonkey.modules.gauge.model.enums.GaugeAnswerInfoTypeEnum;
import com.wang.jmonkey.modules.gauge.service.IGaugeAnswerInfoService;
import com.wang.jmonkey.modules.gauge.service.IGaugeResultQualityService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.wang.jmonkey.modules.gauge.strategy.GaugeResultStrategy;
import com.wang.jmonkey.modules.gauge.utils.GaugeResultUtil;
import com.xiaoleilu.hutool.collection.CollectionUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 量表————气质量表结果记录信息 服务实现类
 * </p>
 *
 * @author HeJiawang
 * @since 2019-09-26
 */
@Slf4j
@Service
public class GaugeResultQualityServiceImpl extends ServiceImpl<GaugeResultQualityMapper, GaugeResultQuality>
        implements IGaugeResultQualityService, GaugeResultStrategy {

    /**
     * gaugeAnswerInfoService
     */
    @Autowired
    private IGaugeAnswerInfoService gaugeAnswerInfoService;

    /**
     * mapper
     */
    @Autowired
    private GaugeResultQualityMapper mapper;

    /**
     * selectByRecordId
     * @param recordId recordId
     * @return HttpResult
     */
    @Override
    public GaugeResultQuality selectByRecordId(String recordId) {
        EntityWrapper<GaugeResultQuality> wrapper = new EntityWrapper<>(
                new GaugeResultQuality().setRecordId(recordId)
        );

        return super.selectOne(wrapper);
    }

    /**
     * 获取服刑人员最新的测评结果
     * @param userId userId
     * @return GaugeResultQuality
     */
    @Override
    public GaugeResultQuality selectNewByUserId(String userId) {
        return mapper.selectNewByUserId(userId);
    }

    /**
     * 计算服刑人员测评结果
     * @param gaugeRecordId gaugeRecordId
     * @param gaugeResultList gaugeResultList
     * @return boolean
     */
    @Override
    public boolean assess(String gaugeRecordId, List<GaugeResult> gaugeResultList) {
        Boolean assessResult = false;

        if (CollectionUtil.isEmpty(gaugeResultList)) return assessResult;

        // 1、计算总分、各个典型性格得分
        int totalScore = 0, nyScore = 0, yyScore = 0, dxScore = 0, dzScore = 0;
        for (GaugeResult result : gaugeResultList) {
            totalScore += result.getAnswerScore();

            switch (result.getTopicType()) {
                case "NY" :
                    nyScore += result.getAnswerScore();
                    break;

                case "YY" :
                    yyScore += result.getAnswerScore();
                    break;

                case "DX" :
                    dxScore += result.getAnswerScore();
                    break;

                case "DZ" :
                    dzScore += result.getAnswerScore();
                    break;
            }
        }

        // 获取测评结果ID
        String answerInfoId = this.assessAnswerInfo(nyScore, yyScore, dxScore, dzScore);

        GaugeResultQuality quality = new GaugeResultQuality();
        quality.setRecordId(gaugeRecordId).setAnswerInfoId(answerInfoId).setTotalScore(totalScore)
                .setDxScore(dxScore).setDzScore(dzScore).setNyScore(nyScore).setYyScore(yyScore);

        // 记录规则解释
        Map<String, GaugeAnswerInfo> resultMap = gaugeAnswerInfoService.selectMapByType(GaugeAnswerInfoTypeEnum.Quality);
        quality.setResult(resultMap.get(answerInfoId).getDescribe());

        assessResult = super.insert(quality);

        return assessResult;
    }

    /**
     * 获取测评结果id
     * TODO 有待优化代码算法、代码结构
     * @param nyScore nyScore
     * @param yyScore yyScore
     * @param dxScore dxScore
     * @param dzScore dzScore
     * @return 测评结果id
     */
    private String assessAnswerInfo(int nyScore, int yyScore, int dxScore, int dzScore) {

        {
            // 计算 胆汁质-多血质-粘液质-抑郁质
            boolean is_dz_dx_ny_yy = assess4(nyScore, yyScore, dxScore, dzScore);
            if (is_dz_dx_ny_yy) return AnswerBuilder.dz_dx_ny_yy;
        }

        {
            // 计算 胆汁质-多血质-粘液质
            Boolean is_dz_dx_ny = assess3(yyScore, nyScore, dxScore, dzScore);
            if(is_dz_dx_ny) return AnswerBuilder.dz_dx_ny;

            // 计算 胆汁质-粘液质-抑郁质
            Boolean is_dz_ny_yy = assess3(dxScore, yyScore, nyScore, dzScore);
            if(is_dz_ny_yy) return AnswerBuilder.dz_ny_yy;

            // 计算 胆汁质-多血质-抑郁质
            Boolean is_dz_dx_yy = assess3(nyScore, yyScore, dxScore, dzScore);
            if(is_dz_dx_yy) return AnswerBuilder.dz_dx_yy;

            // 计算 多血质-粘液质-抑郁质
            Boolean is_dx_ny_yy = assess3(dzScore, yyScore, nyScore, dxScore);
            if(is_dx_ny_yy) return AnswerBuilder.dx_ny_yy;
        }

        {
            // 计算 胆汁质--多血质
            Boolean is_dz_dx = assess2(dzScore, dxScore, yyScore, nyScore);
            if (is_dz_dx) return AnswerBuilder.dz_dx;

            // 计算 多血质--粘液质
            Boolean is_dx_ny = assess2(dxScore, nyScore, yyScore, dzScore);
            if (is_dx_ny) return AnswerBuilder.dx_ny;

            // 计算 粘液质--抑郁质
            Boolean is_ny_yy = assess2(nyScore, yyScore, dzScore, dxScore);
            if (is_ny_yy) return AnswerBuilder.ny_yy;

            // 计算 胆汁质--抑郁质
            Boolean is_dz_yy = assess2(yyScore, dzScore, nyScore, dxScore);
            if (is_dz_yy) return AnswerBuilder.dz_yy;

            // 计算 胆汁质--粘液质
            Boolean is_dz_ny = assess2(dzScore, nyScore, yyScore, dxScore);
            if (is_dz_ny) return AnswerBuilder.dz_ny;

            // 计算 多血质--抑郁质
            Boolean is_dx_yy = assess2(yyScore, dxScore, dzScore, nyScore);
            if (is_dx_yy) return AnswerBuilder.dx_yy;
        }

        {
            // 一般胆汁
            Boolean is_y_dz = assessY(dzScore, nyScore, yyScore, dxScore);
            if (is_y_dz) return AnswerBuilder.y_dz;

            // 一般多血
            Boolean is_y_dx = assessY(dxScore, dzScore, nyScore, yyScore);
            if (is_y_dx) return AnswerBuilder.y_dx;

            // 一般粘液
            Boolean is_y_ny = assessY(nyScore, yyScore, dxScore, dzScore);
            if (is_y_ny) return AnswerBuilder.y_ny;

            // 一般抑郁
            Boolean is_y_yy = assessY(yyScore, dxScore, nyScore, dzScore);
            if (is_y_yy) return AnswerBuilder.y_yy;
        }

        {
            // 典型胆汁
            Boolean is_d_dz = assessD(dzScore, nyScore, yyScore, dxScore);
            if (is_d_dz) return AnswerBuilder.d_dz;

            // 典型多血
            Boolean is_d_dx = assessD(dxScore, dzScore, nyScore, yyScore);
            if (is_d_dx) return AnswerBuilder.d_dx;

            // 典型粘液
            Boolean is_d_ny = assessD(nyScore, yyScore, dxScore, dzScore);
            if (is_d_ny) return AnswerBuilder.d_ny;

            // 典型抑郁
            Boolean is_d_yy = assessD(yyScore, dxScore, nyScore, dzScore);
            if (is_d_yy) return AnswerBuilder.d_yy;
        }

        return StringUtils.EMPTY;
    }

    /**
     * 得分超过 20 分 ,并明显高出其他三种（均高出 4 分及4分以上）
     * @param targerScore targerScore
     * @param otherScore1 otherScore1
     * @param otherScore2 otherScore2
     * @param otherScore3 otherScore3
     * @return true 是
     */
    private Boolean assessD(int targerScore, int otherScore1, int otherScore2, int otherScore3) {
        if (targerScore > 20 &&
                GaugeResultUtil.subAbs(targerScore, otherScore1) >= 4 &&
                GaugeResultUtil.subAbs(targerScore, otherScore2) >= 4 &&
                GaugeResultUtil.subAbs(targerScore, otherScore3) >= 4
                ) {

            return true;
        }

        return false;
    }

    /**
     * 某种气质得分不超过 20 分 大于 10，但明显高出其他三种（均高出 4 分及4分以上）
     * @param targerScore targerScore
     * @param otherScore1 otherScore1
     * @param otherScore2 otherScore2
     * @param otherScore3 otherScore3
     * @return true 是
     */
    private Boolean assessY(int targerScore, int otherScore1, int otherScore2, int otherScore3 ) {
        if (targerScore > 10 &&
                targerScore <=20 &&
                GaugeResultUtil.subAbs(targerScore, otherScore1) >= 4 &&
                GaugeResultUtil.subAbs(targerScore, otherScore2) >= 4 &&
                GaugeResultUtil.subAbs(targerScore, otherScore3) >= 4
                ) {

            return true;
        }

        return false;
    }

    /**
     * 两种气质得分接近（差异低于等于 3 分）而又明显高于其他两种（高出 4 分及4分以上） ，则可定为二种气质的混合型
     * @param targerScore1 targerScore
     * @param targerScore2 targerScore
     * @param otherScore1 otherScore
     * @param otherScore2 otherScore
     * @return true 是
     */
    private Boolean assess2(int targerScore1, int targerScore2, int otherScore1, int otherScore2) {
        if (GaugeResultUtil.subAbsGt(targerScore1, targerScore2, 3)) {
            return false;
        }

        if (GaugeResultUtil.subAbsLt(targerScore1, otherScore1, 4)) {
            return false;
        }

        if (GaugeResultUtil.subAbsLt(targerScore1, otherScore2, 4)) {
            return false;
        }

        if (GaugeResultUtil.subAbsLt(targerScore2, otherScore1, 4)) {
            return false;
        }

        if (GaugeResultUtil.subAbsLt(targerScore2, otherScore2, 4)) {
            return false;
        }

        return true;
    }

    /**
     * 三种气质均高于第四种的得分（高出 4 分及4分以上）且相接近（差异低于等于 3 分），则为三种气质的混合型
     * @param targerScore 第四种的得分
     * @param otherScore 三种气质得分
     * @return true 是
     */
    private Boolean assess3(int targerScore, Integer... otherScore) {
        List<Map<String, Integer>> scoreList = GaugeResultUtil.doubleTurns(otherScore);
        for (Map<String, Integer> map : scoreList) {

            // 取反，只要有一组数据相差大于3，就不是该类型
            if (GaugeResultUtil.subAbsGt(map.get("scoreA"), map.get("scoreB"), 3)) {
                return false;
            }
        }

        List<Integer> otherScoreList = Arrays.asList(otherScore);
        for (Integer score : otherScoreList) {

            // 取反 只要有一种气质得分与 第四种的得分 相差小于4分 就不是该类型
            if (GaugeResultUtil.subAbsLt(score, targerScore, 4)) {
                return false;
            }
        }

        return true;
    }

    /**
     * 胆汁质-多血质-粘液质-抑郁质 组合的测评结果
     * 四种气质的得分均相接近（差异低于等于 3 分），则为四种气质的混合型
     * @param nyScore nyScore
     * @param yyScore yyScore
     * @param dxScore dxScore
     * @param dzScore dzScore
     * @return true 是
     */
    private Boolean assess4(int nyScore, int yyScore, int dxScore, int dzScore) {
        List<Map<String, Integer>> scoreList = GaugeResultUtil.doubleTurns(nyScore, yyScore, dxScore, dzScore);
        for (Map<String, Integer> map : scoreList) {

            // 取反，只要有一组数据相差大于3，就不是该类型
            if (GaugeResultUtil.subAbsGt(map.get("scoreA"), map.get("scoreB"), 3)) {
                return false;
            }
        }

        return true;
    }

    /**
     * 写死的内容，与数据库匹配
     */
    @Component
    static class AnswerBuilder {

        /**
         * 典型胆汁
         * 得分超过 20 分 ,并明显高出其他三种（均高出 4 分及 4 分以上）
         */
        public static String d_dz;

        /**
         * 典型多血
         * 得分超过 20 分 ,并明显高出其他三种（均高出 4 分及 4 分以上）
         *
         * gauge_answer_info_quality_1
         */
        public static String d_dx;

        /**
         * 典型粘液
         * 得分超过 20 分 ,并明显高出其他三种（均高出 4 分及 4 分以上）
         *
         * gauge_answer_info_quality_2
         */
        public static String d_ny;

        /**
         * 典型抑郁
         * 得分超过 20 分 ,并明显高出其他三种（均高出 4 分及 4 分以上）
         *
         * gauge_answer_info_quality_3
         */
        public static String d_yy;

        /**
         * 一般胆汁
         * 某种气质得分不超过 20 分 大于 10，但明显高出其他三种（均高出 4 分及 4 分以上）
         *
         * gauge_answer_info_quality_4
         */
        public static String y_dz;

        /**
         * 一般多血
         * 某种气质得分不超过 20 分 大于 10，但明显高出其他三种（均高出 4 分及 4 分以上）
         *
         * gauge_answer_info_quality_5
         */
        public static String y_dx;

        /**
         * 一般粘液
         * 某种气质得分不超过 20 分 大于 10，但明显高出其他三种（均高出 4 分及 4 分以上）
         *
         * gauge_answer_info_quality_6
         */
        public static String y_ny;

        /**
         * 一般抑郁
         * 某种气质得分不超过 20 分 大于 10，但明显高出其他三种（均高出 4 分及 4 分以上）
         *
         * gauge_answer_info_quality_7
         */
        public static String y_yy;

        /**
         * 胆汁质--多血质
         * 两种气质得分接近（差异低于等于 3 分）而又明显高于其他两种（高出 4 分及 4 分以上） ，则可定为二种气质的混合型
         *
         * gauge_answer_info_quality_8
         */
        public static String dz_dx;

        /**
         * 多血质--粘液质
         * 两种气质得分接近（差异低于等于 3 分）而又明显高于其他两种（高出 4 分及 4 分以上） ，则可定为二种气质的混合型
         *
         * gauge_answer_info_quality_9
         */
        public static String dx_ny;

        /**
         * 粘液质--抑郁质
         * 两种气质得分接近（差异低于等于 3 分）而又明显高于其他两种（高出 4 分及 4 分以上） ，则可定为二种气质的混合型
         *
         * gauge_answer_info_quality_10
         */
        public static String ny_yy;

        /**
         * 胆汁质--抑郁质
         * 两种气质得分接近（差异低于等于 3 分）而又明显高于其他两种（高出 4 分及 4 分以上） ，则可定为二种气质的混合型
         *
         * gauge_answer_info_quality_11
         */
        public static String dz_yy;

        /**
         * 胆汁质--粘液质
         * 两种气质得分接近（差异低于等于 3 分）而又明显高于其他两种（高出 4 分及 4 分以上） ，则可定为二种气质的混合型
         *
         * gauge_answer_info_quality_12
         */
        public static String dz_ny;

        /**
         * 多血质--抑郁质
         * 两种气质得分接近（差异低于等于 3 分）而又明显高于其他两种（高出 4 分及 4 分以上） ，则可定为二种气质的混合型
         *
         * gauge_answer_info_quality_13
         */
        public static String dx_yy;

        /**
         * 胆汁质-多血质-粘液质
         * 三种气质均高于第四种的得分（高出 4 分及 4 分以上）且相接近（差异低于等于 3 分），则为三种气质的混合型
         *
         * gauge_answer_info_quality_14
         */
        public static String dz_dx_ny;

        /**
         * 胆汁质-粘液质-抑郁质
         * 三种气质均高于第四种的得分（高出 4 分及 4 分以上）且相接近（差异低于等于 3 分），则为三种气质的混合型
         *
         * gauge_answer_info_quality_15
         */
        public static String dz_ny_yy;

        /**
         * 胆汁质-多血质-抑郁质
         * 三种气质均高于第四种的得分（高出 4 分及 4 分以上）且相接近（差异低于等于 3 分），则为三种气质的混合型
         *
         * gauge_answer_info_quality_16
         */
        public static String dz_dx_yy;

        /**
         * 多血质-粘液质-抑郁质
         * 三种气质均高于第四种的得分（高出 4 分及 4 分以上）且相接近（差异低于等于 3 分），则为三种气质的混合型
         *
         * gauge_answer_info_quality_17
         */
        public static String dx_ny_yy;

        /**
         * 胆汁质-多血质-粘液质-抑郁质
         * 四种气质的得分均相接近（差异低于等于 3 分），则为四种气质的混合型
         *
         * gauge_answer_info_quality_18
         */
        public static String dz_dx_ny_yy;

        @Value("${jmonkey.gauge.answer-info.quality.d-dz}")
        public void setD_dz(String d_dz) {
            this.d_dz = d_dz;
        }

        @Value("${jmonkey.gauge.answer-info.quality.d-dx}")
        public void setD_dx(String d_dx) {
            this.d_dx = d_dx;
        }

        @Value("${jmonkey.gauge.answer-info.quality.d-ny}")
        public void setD_ny(String d_ny) {
            this.d_ny = d_ny;
        }

        @Value("${jmonkey.gauge.answer-info.quality.d-yy}")
        public void setD_yy(String d_yy) {
            this.d_yy = d_yy;
        }

        @Value("${jmonkey.gauge.answer-info.quality.y-dz}")
        public void setY_dz(String y_dz) {
            this.y_dz = y_dz;
        }

        @Value("${jmonkey.gauge.answer-info.quality.y-dx}")
        public void setY_dx(String y_dx) {
            this.y_dx = y_dx;
        }

        @Value("${jmonkey.gauge.answer-info.quality.y-ny}")
        public void setY_ny(String y_ny) {
            this.y_ny = y_ny;
        }

        @Value("${jmonkey.gauge.answer-info.quality.y-yy}")
        public void setY_yy(String y_yy) {
            this.y_yy = y_yy;
        }

        @Value("${jmonkey.gauge.answer-info.quality.dz-dx}")
        public void setDz_dx(String dz_dx) {
            this.dz_dx = dz_dx;
        }

        @Value("${jmonkey.gauge.answer-info.quality.dx-ny}")
        public void setDx_ny(String dx_ny) {
            this.dx_ny = dx_ny;
        }

        @Value("${jmonkey.gauge.answer-info.quality.ny-yy}")
        public void setNy_yy(String ny_yy) {
            this.ny_yy = ny_yy;
        }

        @Value("${jmonkey.gauge.answer-info.quality.dz-yy}")
        public void setDz_yy(String dz_yy) {
            this.dz_yy = dz_yy;
        }

        @Value("${jmonkey.gauge.answer-info.quality.dz-ny}")
        public void setDz_ny(String dz_ny) {
            this.dz_ny = dz_ny;
        }

        @Value("${jmonkey.gauge.answer-info.quality.dx-yy}")
        public void setDx_yy(String dx_yy) {
            this.dx_yy = dx_yy;
        }

        @Value("${jmonkey.gauge.answer-info.quality.dz-dx-ny}")
        public void setDz_dx_ny(String dz_dx_ny) {
            this.dz_dx_ny = dz_dx_ny;
        }

        @Value("${jmonkey.gauge.answer-info.quality.dz-ny-yy}")
        public void setDz_ny_yy(String dz_ny_yy) {
            this.dz_ny_yy = dz_ny_yy;
        }

        @Value("${jmonkey.gauge.answer-info.quality.dz-dx-yy}")
        public void setDz_dx_yy(String dz_dx_yy) {
            this.dz_dx_yy = dz_dx_yy;
        }

        @Value("${jmonkey.gauge.answer-info.quality.dx-ny-yy}")
        public void setDx_ny_yy(String dx_ny_yy) {
            this.dx_ny_yy = dx_ny_yy;
        }

        @Value("${jmonkey.gauge.answer-info.quality.dz-dx-ny-yy}")
        public void setDz_dx_ny_yy(String dz_dx_ny_yy) {
            this.dz_dx_ny_yy = dz_dx_ny_yy;
        }
    }
}
