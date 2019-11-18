package com.wang.jmonkey.modules.gauge.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.wang.jmonkey.modules.gauge.model.entity.GaugeAnswerInfo;
import com.wang.jmonkey.modules.gauge.model.param.GaugeResult;
import com.wang.jmonkey.modules.gauge.model.entity.GaugeResultAction;
import com.wang.jmonkey.modules.gauge.mapper.GaugeResultActionMapper;
import com.wang.jmonkey.modules.gauge.model.enums.GaugeAnswerInfoTypeEnum;
import com.wang.jmonkey.modules.gauge.service.IGaugeAnswerInfoService;
import com.wang.jmonkey.modules.gauge.service.IGaugeResultActionService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.wang.jmonkey.modules.gauge.strategy.GaugeResultStrategy;
import com.xiaoleilu.hutool.collection.CollectionUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 量表————行为量表结果记录信息 服务实现类
 * </p>
 *
 * @author HeJiawang
 * @since 2019-09-26
 */
@Slf4j
@Service
public class GaugeResultActionServiceImpl extends ServiceImpl<GaugeResultActionMapper, GaugeResultAction>
        implements IGaugeResultActionService, GaugeResultStrategy {

    /**
     * gaugeAnswerInfoService
     */
    @Autowired
    private IGaugeAnswerInfoService gaugeAnswerInfoService;

    /**
     * mapper
     */
    @Autowired
    private GaugeResultActionMapper mapper;

    /**
     * selectByRecordId
     * @param recordId recordId
     * @return GaugeResultAction
     */
    @Override
    public GaugeResultAction selectByRecordId(String recordId) {
        EntityWrapper<GaugeResultAction> wrapper = new EntityWrapper<>(
                new GaugeResultAction().setRecordId(recordId)
        );
        return super.selectOne(wrapper);
    }

    /**
     * 获取服刑人员最新的测评结果
     * @param studentId studentId
     * @return GaugeResultAction
     */
    @Override
    public GaugeResultAction selectNewByStudentId(String studentId) {
        return mapper.selectNewByStudentId(studentId);
    }

    /**
     * 计算服刑人员测评结果
     * @param gaugeRecordId gaugeRecordId
     * @param gaugeResultList gaugeResultList
     * @return boolean
     */
    @Override
    public boolean assess(String gaugeRecordId, List<GaugeResult> gaugeResultList) {
        if (CollectionUtil.isEmpty(gaugeResultList)) return false;

        // 1、计算总分、th得分、ch得分、l得分
        int totalScore = 0, thScore = 0, chScore = 0, lScore = 0;
        for (GaugeResult result : gaugeResultList) {
            totalScore += result.getAnswerScore();

            switch (result.getTopicType()) {
                case "TH" :
                    thScore += result.getAnswerScore();
                    break;

                case "CH" :
                    chScore += result.getAnswerScore();
                    break;

                case "L" :
                    lScore += result.getAnswerScore();
                    break;
            }
        }

        // 2、获取量表结果ID
        String totalAnswerInfoId = this.assessTotal(totalScore),
                thAnswerInfoId = this.assessTH(thScore),
                chAnswerInfoId = this.assessCH(chScore),
                lAnswerInfoId = this.assessL(lScore);

        // 3、保存测评结果信息
        GaugeResultAction resultAction = new GaugeResultAction().setRecordId(gaugeRecordId)
                .setTotalScore(totalScore).setTotalAnswerInfoId(totalAnswerInfoId)
                .setThScore(thScore).setThAnswerInfoId(thAnswerInfoId)
                .setChScore(chScore).setChAnswerInfoId(chAnswerInfoId)
                .setLScore(lScore).setLAnswerInfoId(lAnswerInfoId);

        // 4、记录规则解释
        Map<String, GaugeAnswerInfo> resultMap = gaugeAnswerInfoService.selectMapByType(GaugeAnswerInfoTypeEnum.Action);
        resultAction.setResult(resultMap.get(totalAnswerInfoId).getDescribe());
        resultAction.setAdvise(resultMap.get(totalAnswerInfoId).getAdvise());

        return super.insert(resultAction);
    }

    private String assessTotal(int totalScore) {
        if (totalScore >= 37 && totalScore <= 50) {
            return AnswerBuilder.totalA;
        }

        if (totalScore >= 30 && totalScore <=36) {
            return AnswerBuilder.totalAC;
        }

        if (totalScore >= 27 && totalScore <= 29) {
            return AnswerBuilder.totalAB;
        }

        if (totalScore >= 20 && totalScore <= 26) {
            return AnswerBuilder.totalBC;
        }

        if (totalScore >= 1 && totalScore <= 19) {
            return AnswerBuilder.totalB;
        }

        return StringUtils.EMPTY;
    }

    private String assessTH(int thScore) {
        if (thScore >= 18) {
            return AnswerBuilder.thMax;
        }

        if (thScore < 18) {
            return AnswerBuilder.thMin;
        }

        return StringUtils.EMPTY;
    }

    private String assessCH(int chScore) {
        if (chScore >= 18) {
            return AnswerBuilder.chMax;
        }

        if (chScore < 18) {
            return AnswerBuilder.chMin;
        }

        return StringUtils.EMPTY;
    }

    private String assessL(int lScore) {
        if (lScore > 5) {
            return AnswerBuilder.l;
        }

        return StringUtils.EMPTY;
    }

    /**
     * 写死的内容，与数据库匹配
     */
    @Component
    static class AnswerBuilder {

        /**
         * A 型行为
         * 总分≥37 且 总分 ≤50
         *
         * gauge_answer_info_action_1
         */
        public static String totalA;

        /**
         * 普通A型行为
         * 总分≥30 且 总分 ≤36
         *
         * gauge_answer_info_action_2
         */
        public static String totalAC;

        /**
         * AB型行为
         * 总分≥27 且  总分 ≤29
         * gauge_answer_info_action_3
         */
        public static String totalAB;

        /**
         * B 型行为
         * 总分≥1 且  总分 ≤19
         * gauge_answer_info_action_5
         */
        public static String totalB;

        /**
         * 普通B型行为
         * 总分≥20 且  总分 ≤26
         * gauge_answer_info_action_4
         */
        public static String totalBC;

        /**
         * 时间匆忙、紧迫感特征高分
         * TH≥18
         * gauge_answer_info_action_6
         */
        public static String thMax;

        /**
         * 时间匆忙、紧迫感特征低分
         * TH<18
         * gauge_answer_info_action_7
         */
        public static String thMin;

        /**
         * 争强好胜、怀有戒心或敌意特征高分
         * CH≥18
         * gauge_answer_info_action_8
         */
        public static String chMax;

        /**
         * 争强好胜、怀有戒心或敌意特征低分
         * CH<18
         * gauge_answer_info_action_9
         */
        public static String chMin;

        /**
         * 掩饰高分
         * L>5
         * gauge_answer_info_action_10
         */
        public static String l;

        @Value("${jmonkey.gauge.answer-info.action.total-a}")
        public void setTotalA( String totalA ){
            this.totalA = totalA;
        }

        @Value("${jmonkey.gauge.answer-info.action.total-ac}")
        public void setTotalAC( String totalAC ){
            this.totalAC = totalAC;
        }

        @Value("${jmonkey.gauge.answer-info.action.total-ab}")
        public void setTotalAB(String totalAB) {
            this.totalAB = totalAB;
        }

        @Value("${jmonkey.gauge.answer-info.action.total-b}")
        public void setTotalB(String totalB) {
            this.totalB = totalB;
        }

        @Value("${jmonkey.gauge.answer-info.action.total-bc}")
        public void setTotalBC(String totalBC) {
            this.totalBC = totalBC;
        }

        @Value("${jmonkey.gauge.answer-info.action.th-max}")
        public void setThMax(String thMax) {
            this.thMax = thMax;
        }

        @Value("${jmonkey.gauge.answer-info.action.th-min}")
        public void setThMin(String thMin) {
            this.thMin = thMin;
        }

        @Value("${jmonkey.gauge.answer-info.action.ch-max}")
        public void setChMax(String chMax) {
            this.chMax = chMax;
        }

        @Value("${jmonkey.gauge.answer-info.action.ch-min}")
        public void setChMin(String chMin) {
            this.chMin = chMin;
        }

        @Value("${jmonkey.gauge.answer-info.action.l}")
        public void setL(String l) {
            this.l = l;
        }

    }
}
