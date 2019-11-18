package com.wang.jmonkey.modules.gauge.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.wang.jmonkey.common.model.enums.SexEnum;
import com.wang.jmonkey.modules.gauge.model.entity.GaugeAnswerInfo;
import com.wang.jmonkey.modules.gauge.model.param.GaugeResult;
import com.wang.jmonkey.modules.gauge.model.entity.GaugeResultAks;
import com.wang.jmonkey.modules.gauge.mapper.GaugeResultAksMapper;
import com.wang.jmonkey.modules.gauge.model.enums.GaugeAnswerInfoTypeEnum;
import com.wang.jmonkey.modules.gauge.service.IGaugeAnswerInfoService;
import com.wang.jmonkey.modules.gauge.service.IGaugeRecordService;
import com.wang.jmonkey.modules.gauge.service.IGaugeResultAksService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.wang.jmonkey.modules.gauge.strategy.GaugeResultStrategy;
import com.wang.jmonkey.modules.gauge.utils.GaugeResultUtil;
import com.wang.jmonkey.modules.report.model.entity.ReportStudent;
import com.wang.jmonkey.modules.report.service.IReportStudentService;
import com.wang.jmonkey.modules.sys.model.entity.SysUser;
import com.wang.jmonkey.modules.sys.service.ISysUserService;
import com.xiaoleilu.hutool.collection.CollectionUtil;
import com.xiaoleilu.hutool.date.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 量表————艾克森量表结果记录信息 服务实现类
 * </p>
 *
 * @author HeJiawang
 * @since 2019-10-19
 */
@Slf4j
@Service
public class GaugeResultAksServiceImpl extends ServiceImpl<GaugeResultAksMapper, GaugeResultAks>
        implements IGaugeResultAksService, GaugeResultStrategy {

    /**
     * gaugeRecordService
     */
    @Autowired
    private IGaugeRecordService gaugeRecordService;

    /**
     * reportStudentService
     */
    @Autowired
    private IReportStudentService reportStudentService;

    /**
     * gaugeAnswerInfoService
     */
    @Autowired
    private IGaugeAnswerInfoService gaugeAnswerInfoService;

    /**
     * mapper
     */
    @Autowired
    private GaugeResultAksMapper mapper;

    /**
     * selectByRecordId
     * @param recordId recordId
     * @return HttpResult
     */
    @Override
    public GaugeResultAks selectByRecordId(String recordId) {
        EntityWrapper<GaugeResultAks> wrapper = new EntityWrapper(
                new GaugeResultAks().setRecordId(recordId)
        );

        return super.selectOne(wrapper);
    }

    /**
     * 获取服刑人员最新的测评结果
     * @param studentId studentId
     * @return GaugeResultAks
     */
    @Override
    public GaugeResultAks selectNewByStudentId(String studentId) {
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

        String studentId = gaugeRecordService.selectById(gaugeRecordId).getStudentId();
        ReportStudent student = reportStudentService.selectById(studentId);

        int age = DateUtil.year(new Date()) - DateUtil.year(student.getBirthday()) + 1;
        String ageD = GaugeResultUtil.renderAge(age);
        SexEnum sex = student.getSex();

        int pScore = 0, eScore = 0, nScore = 0, lScore = 0;
        for (GaugeResult result : gaugeResultList) {
            switch (result.getTopicType()) {
                case "AKS_P" :
                    pScore += result.getAnswerScore();
                    break;

                case "AKS_E" :
                    eScore += result.getAnswerScore();
                    break;

                case "AKS_N" :
                    nScore += result.getAnswerScore();
                    break;

                case "AKS_L" :
                    lScore += result.getAnswerScore();
                    break;
            }
        }

        Integer pTScore = GaugeResultUtil.aksMap.get("P").get(sex).get(ageD).get(pScore);
        Integer eTScore = GaugeResultUtil.aksMap.get("E").get(sex).get(ageD).get(pScore);
        Integer nTScore = GaugeResultUtil.aksMap.get("N").get(sex).get(ageD).get(pScore);
        Integer lTScore = GaugeResultUtil.aksMap.get("L").get(sex).get(ageD).get(pScore);

        String pAnswerInfoId = this.builderPAnswerInfoId(pTScore);
        String eAnswerInfoId = this.builderEAnswerInfoId(eTScore);
        String nAnswerInfoId = this.builderNAnswerInfoId(nTScore);

        GaugeResultAks aks = new GaugeResultAks()
                .setRecordId(gaugeRecordId)
                .setPScore(pScore).setPTScore(pTScore).setPAnswerInfoId(pAnswerInfoId)
                .setEScore(eScore).setETScore(eTScore).setEAnswerInfoId(eAnswerInfoId)
                .setNScore(nScore).setNTScore(nTScore).setNAnswerInfoId(nAnswerInfoId)
                .setLScore(lScore).setLTScore(lTScore);

        // 记录规则解释
        Map<String, GaugeAnswerInfo> resultMap = gaugeAnswerInfoService.selectMapByType(GaugeAnswerInfoTypeEnum.AKS);
        aks.setResult(
                resultMap.get(pAnswerInfoId).getDescribe() + "\n\r"
                        + resultMap.get(eAnswerInfoId).getDescribe() + "\n\r"
                        + resultMap.get(nAnswerInfoId).getDescribe() + "\n\r"
        );
        aks.setAdvise(
                resultMap.get(pAnswerInfoId).getAdvise() + "\n\r"
                + resultMap.get(eAnswerInfoId).getAdvise() + "\n\r"
                + resultMap.get(nAnswerInfoId).getAdvise() + "\n\r"
        );

        return super.insert(aks);
    }

    private String builderPAnswerInfoId(Integer score) {
        if (null == score) {
            return StringUtils.EMPTY;
        }

        if (score>= 43.3 & score <56.7) {
            return AnswerBuilder.p1;
        }

        if ((score >= 56.7 & score < 61.5) | (score >= 38.5 & score < 43.3)) {
            return AnswerBuilder.p2;
        }

        if (score < 38.5) {
            return AnswerBuilder.p3;
        }

        if (score >= 61.5) {
            return AnswerBuilder.p4;
        }

        return StringUtils.EMPTY;
    }

    private String builderEAnswerInfoId(Integer score) {
        if (null == score) {
            return StringUtils.EMPTY;
        }

        if (score>= 43.3 & score <56.7) {
            return AnswerBuilder.e1;
        }

        if ((score >= 56.7 & score < 61.5) | (score >= 38.5 & score < 43.3)) {
            return AnswerBuilder.e2;
        }

        if (score < 38.5) {
            return AnswerBuilder.e3;
        }

        if (score >= 61.5) {
            return AnswerBuilder.e4;
        }

        return StringUtils.EMPTY;
    }

    private String builderNAnswerInfoId(Integer score) {
        if (null == score) {
            return StringUtils.EMPTY;
        }

        if (score>= 43.3 & score <56.7) {
            return AnswerBuilder.n1;
        }

        if ((score >= 56.7 & score < 61.5) | (score >= 38.5 & score < 43.3)) {
            return AnswerBuilder.n2;
        }

        if (score < 38.5) {
            return AnswerBuilder.n3;
        }

        if (score >= 61.5) {
            return AnswerBuilder.n4;
        }

        return StringUtils.EMPTY;
    }

    /**
     * 写死的内容，与数据库匹配
     */
    @Component
    static class AnswerBuilder {
        /**
         * P维度的T得分在≥43.3 以及＜56.7
         */
        public static String p1;
        /**
         * P维度的T得分在≥56.7 以及＜61.5 或者 ≥38.5 以及＜43.3
         */
        public static String p2;
        /**
         * P维度的T得分＜38.5
         */
        public static String p3;
        /**
         * P维度的T得分≥61.5
         */
        public static String p4;
        /**
         * N维度的T得分在≥43.3 以及＜56.7
         */
        public static String n1;
        /**
         * N维度的T得分在≥56.7 以及＜61.5 或者 ≥38.5 以及＜43.3
         */
        public static String n2;
        /**
         * N维度的T得分＜38.5
         */
        public static String n3;
        /**
         * N维度的T得分≥61.5
         */
        public static String n4;
        /**
         * E维度的T得分在≥43.3 以及＜56.7
         */
        public static String e1;
        /**
         * E维度的T得分在≥56.7 以及＜61.5 或者 ≥38.5 以及＜43.3
         */
        public static String e2;
        /**
         * E维度的T得分＜38.5
         */
        public static String e3;
        /**
         * E维度的T得分≥61.5
         */
        public static String e4;

        @Value("${jmonkey.gauge.answer-info.aks.p1}")
        public void setP1(String p1) {
            this.p1 = p1;
        }
        @Value("${jmonkey.gauge.answer-info.aks.p2}")
        public void setP2(String p2) {
            this.p2 = p2;
        }
        @Value("${jmonkey.gauge.answer-info.aks.p3}")
        public void setP3(String p3) {
            this.p3 = p3;
        }
        @Value("${jmonkey.gauge.answer-info.aks.p4}")
        public void setP4(String p4) {
            this.p4 = p4;
        }
        @Value("${jmonkey.gauge.answer-info.aks.n1}")
        public void setN1(String n1) {
            this.n1 = n1;
        }
        @Value("${jmonkey.gauge.answer-info.aks.n2}")
        public void setN2(String n2) {
            this.n2 = n2;
        }
        @Value("${jmonkey.gauge.answer-info.aks.n3}")
        public void setN3(String n3) {
            this.n3 = n3;
        }
        @Value("${jmonkey.gauge.answer-info.aks.n4}")
        public void setN4(String n4) {
            this.n4 = n4;
        }
        @Value("${jmonkey.gauge.answer-info.aks.e1}")
        public void setE1(String e1) {
            this.e1 = e1;
        }
        @Value("${jmonkey.gauge.answer-info.aks.e2}")
        public void setE2(String e2) {
            this.e2 = e2;
        }
        @Value("${jmonkey.gauge.answer-info.aks.e3}")
        public void setE3(String e3) {
            this.e3 = e3;
        }
        @Value("${jmonkey.gauge.answer-info.aks.e4}")
        public void setE4(String e4) {
            this.e4 = e4;
        }
    }

}
