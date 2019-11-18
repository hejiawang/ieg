package com.wang.jmonkey.modules.gauge.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.wang.jmonkey.modules.gauge.model.entity.GaugeAnswerInfo;
import com.wang.jmonkey.modules.gauge.model.param.GaugeResult;
import com.wang.jmonkey.modules.gauge.model.entity.GaugeResultScl90;
import com.wang.jmonkey.modules.gauge.mapper.GaugeResultScl90Mapper;
import com.wang.jmonkey.modules.gauge.model.enums.GaugeAnswerInfoTypeEnum;
import com.wang.jmonkey.modules.gauge.service.IGaugeAnswerInfoService;
import com.wang.jmonkey.modules.gauge.service.IGaugeResultScl90Service;
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
 * 量表————scl90量表结果记录信息 服务实现类
 * </p>
 *
 * @author HeJiawang
 * @since 2019-10-08
 */
@Slf4j
@Service
public class GaugeResultScl90ServiceImpl extends ServiceImpl<GaugeResultScl90Mapper, GaugeResultScl90>
        implements IGaugeResultScl90Service, GaugeResultStrategy {

    /**
     * gaugeAnswerInfoService
     */
    @Autowired
    private IGaugeAnswerInfoService gaugeAnswerInfoService;

    /**
     * mapper
     */
    @Autowired
    private GaugeResultScl90Mapper mapper;

    /**
     * selectByRecordId
     * @param recordId recordId
     * @return HttpResult
     */
    @Override
    public GaugeResultScl90 selectByRecordId(String recordId) {
        EntityWrapper<GaugeResultScl90> wrapper = new EntityWrapper<>(
                new GaugeResultScl90().setRecordId(recordId)
        );
        return super.selectOne(wrapper);
    }

    /**
     * 获取服刑人员最新的测评结果
     * @param studentId studentId
     * @return GaugeResultScl90
     */
    @Override
    public GaugeResultScl90 selectNewByStudentId(String studentId) {
        return mapper.selectNewByStudentId(studentId);
    }

    @Override
    public boolean assess(String gaugeRecordId, List<GaugeResult> gaugeResultList) {
        if (CollectionUtil.isEmpty(gaugeResultList)) return false;

        Map<String, GaugeAnswerInfo> resultMap = gaugeAnswerInfoService.selectMapByType(GaugeAnswerInfoTypeEnum.SCL90);

        int totalScore = 0, yNum= 0, nNum = 0,
                qutiScore = 0, qiangpozhengScore = 0, renjiguanxiScore = 0,
                yiyuScore = 0, jiaolvScore = 0, diduiScore = 0, kongbuScore = 0,
                pianzhiScore = 0, jingshenScore = 0, qitaScore = 0;

        int qutiNum = 0, qiangpozhengNum = 0, renjiguanxiNum = 0,
                yiyuNum = 0, jiaolvNum = 0, diduiNum = 0, kongbuNum = 0,
                pianzhiNum = 0, jingshenNum = 0, qitaNum = 0;

        // 统计因素个数与分数
        for (GaugeResult result : gaugeResultList) {
            totalScore += result.getAnswerScore();

            if (result.getAnswerScore() >= 2) {
                yNum += 1;
            } else {
                nNum += 1;
            }

            switch (result.getTopicType()) {
                case "didui" :  // 敌对
                    diduiScore += result.getAnswerScore();
                    diduiNum += 1;

                    break;

                case "jiaolv" : // 焦虑
                    jiaolvScore += result.getAnswerScore();
                    jiaolvNum += 1;

                    break;

                case "jingshenbingxing" : // 精神病性
                    jingshenScore += result.getAnswerScore();
                    jingshenNum += 1;

                    break;

                case "kongbu" : // 恐怖
                    kongbuScore += result.getAnswerScore();
                    kongbuNum += 1;

                    break;

                case "pianzhi" : // 偏执
                    pianzhiScore += result.getAnswerScore();
                    pianzhiNum += 1;

                    break;

                case "qita" : // 其他
                    qitaScore += result.getAnswerScore();
                    qitaNum += 1;

                    break;

                case "qiangpozhengzhuang" : // 强迫症状
                    qiangpozhengScore += result.getAnswerScore();
                    qiangpozhengNum += 1;

                    break;

                case "qutihua" : // 躯体化
                    qutiScore += result.getAnswerScore();
                    qutiNum += 1;

                    break;

                case "renjiguanximingan" : // 人际关系敏感
                    renjiguanxiScore += result.getAnswerScore();
                    renjiguanxiNum += 1;

                    break;

                case "yiyu" : // 抑郁
                    yiyuScore += result.getAnswerScore();
                    yiyuNum += 1;

                    break;
            }
        }

        // 统计因子分
        Double totalAverScore = (double) totalScore / gaugeResultList.size(),
                yAverScore = 0.00,
                qutiAverScore = (double) qutiScore / qutiNum,
                qiangpozhengAverScore = (double) qiangpozhengScore / qiangpozhengNum,
                renjiguanxiAverScore = (double) renjiguanxiScore / renjiguanxiNum,
                yiyuAverScore = (double) yiyuScore / yiyuNum,
                jiaolvAverScore = (double) jiaolvScore / jiaolvNum,
                diduiAverScore = (double) diduiScore / diduiNum,
                kongbuAverScore = (double) kongbuScore / kongbuNum,
                pianzhiAverScore = (double) pianzhiScore / pianzhiNum,
                jingshenAverScore = (double) jingshenScore / jingshenNum,
                qitaAverScore = (double) qitaScore / qitaNum;
        if ( 0 != yNum) {
            yAverScore = (double) (totalScore - nNum) / yNum;
        }

        // 获取评分id
        String yAnswerInfoId = "",
                renjiguanxiAnswerInfoId = "",
                yiyuAnswerInfoId = "",
                jiaolvAnswerInfoId = "",
                diduiAnswerInfoId = "",
                kongbuAnswerInfoId = "",
                pianzhiAnswerInfoId = "",
                jingshenAnswerInfoId = "",
                qitaAnswerInfoId = "",
                qutiAnswerInfoId = "",
                qiangpozhengAnswerInfoId = "";

        String result = StringUtils.EMPTY;
        String advice = StringUtils.EMPTY;

        if (renjiguanxiAverScore >= 2) {
            renjiguanxiAnswerInfoId = AnswerBuilder.rjgxmg;
            result = result + resultMap.get(renjiguanxiAnswerInfoId).getName() + "、";
            advice = advice + resultMap.get(renjiguanxiAnswerInfoId).getAdvise() + "、";

            yAnswerInfoId = AnswerBuilder.xlbjk;
        }
        if (yiyuAverScore >= 2) {
            yiyuAnswerInfoId = AnswerBuilder.yy;
            result = result + resultMap.get(yiyuAnswerInfoId).getName() + "、";
            advice = advice + resultMap.get(yiyuAnswerInfoId).getAdvise() + "、";

            yAnswerInfoId = AnswerBuilder.xlbjk;
        }
        if (jiaolvAverScore >= 2) {
            jiaolvAnswerInfoId = AnswerBuilder.jl;
            result = result + resultMap.get(jiaolvAnswerInfoId).getName() + "、";
            advice = advice + resultMap.get(jiaolvAnswerInfoId).getAdvise() + "、";

            yAnswerInfoId = AnswerBuilder.xlbjk;
        }
        if (diduiAverScore >= 2) {
            diduiAnswerInfoId = AnswerBuilder.dd;
            result = result + resultMap.get(diduiAnswerInfoId).getName() + "、";
            advice = advice + resultMap.get(diduiAnswerInfoId).getAdvise() + "、";

            yAnswerInfoId = AnswerBuilder.xlbjk;
        }
        if (kongbuAverScore >= 2) {
            kongbuAnswerInfoId = AnswerBuilder.kb;
            result = result + resultMap.get(kongbuAnswerInfoId).getName() + "、";
            advice = advice + resultMap.get(kongbuAnswerInfoId).getAdvise() + "、";

            yAnswerInfoId = AnswerBuilder.xlbjk;
        }
        if (pianzhiAverScore >= 2) {
            pianzhiAnswerInfoId = AnswerBuilder.pz;
            result = result + resultMap.get(pianzhiAnswerInfoId).getName() + "、";
            advice = advice + resultMap.get(pianzhiAnswerInfoId).getAdvise() + "、";

            yAnswerInfoId = AnswerBuilder.xlbjk;
        }
        if (jingshenAverScore >= 2) {
            jingshenAnswerInfoId = AnswerBuilder.jsbx;
            result = result + resultMap.get(jingshenAnswerInfoId).getName() + "、";
            advice = advice + resultMap.get(jingshenAnswerInfoId).getAdvise() + "、";

            yAnswerInfoId = AnswerBuilder.xlbjk;
        }
        if (qitaAverScore >= 2) {
            qitaAnswerInfoId = AnswerBuilder.smjysc;
            result = result + resultMap.get(qitaAnswerInfoId).getName() + "、";
            advice = advice + resultMap.get(qitaAnswerInfoId).getAdvise() + "、";

            yAnswerInfoId = AnswerBuilder.xlbjk;
        }
        if (qutiAverScore >= 2) {
            qutiAnswerInfoId = AnswerBuilder.qtz;
            result = result + resultMap.get(qutiAnswerInfoId).getName() + "、";
            advice = advice + resultMap.get(qutiAnswerInfoId).getAdvise() + "、";

            yAnswerInfoId = AnswerBuilder.xlbjk;
        }
        if (qiangpozhengAverScore >= 2) {
            qiangpozhengAnswerInfoId = AnswerBuilder.qpz;
            result = result + resultMap.get(qiangpozhengAnswerInfoId).getName() + "、";
            advice = advice + resultMap.get(qiangpozhengAnswerInfoId).getAdvise() + "、";

            yAnswerInfoId = AnswerBuilder.xlbjk;
        }

        if (yNum >= 43 || totalScore >= 160) {
            yAnswerInfoId = AnswerBuilder.xlbjk;
        }
        if (StringUtils.isEmpty(yAnswerInfoId)) {
            yAnswerInfoId = AnswerBuilder.xljk;
        }

        // 拼接结果
        GaugeResultScl90 resultScl90 = new GaugeResultScl90();
        resultScl90.setRecordId(gaugeRecordId)
                .setTotalScore(totalScore).setTotalAverScore(totalAverScore)
                .setYNum(yNum).setYAverScore(yAverScore).setYAnswerInfoId(yAnswerInfoId).setNNum(nNum)
                .setQutiScore(qutiScore).setQutiAverScore(qutiAverScore).setQutiAnswerInfoId(qutiAnswerInfoId)
                .setQiangpozhengScore(qiangpozhengScore).setQiangpozhengAverScore(qiangpozhengAverScore).setQiangpozhengAnswerInfoId(qiangpozhengAnswerInfoId)
                .setRenjiguanxiScore(renjiguanxiScore).setRenjiguanxiAverScore(renjiguanxiAverScore).setRenjiguanxiAnswerInfoId(renjiguanxiAnswerInfoId)
                .setYiyuScore(yiyuScore).setYiyuAverScore(yiyuAverScore).setYiyuAnswerInfoId(yiyuAnswerInfoId)
                .setJiaolvScore(jiaolvScore).setJiaolvAverScore(jiaolvAverScore).setJiaolvAnswerInfoId(jiaolvAnswerInfoId)
                .setDiduiScore(diduiScore).setDiduiAverScore(diduiAverScore).setDiduiAnswerInfoId(diduiAnswerInfoId)
                .setKongbuScore(kongbuScore).setKongbuAverScore(kongbuAverScore).setKongbuAnswerInfoId(kongbuAnswerInfoId)
                .setPianzhiScore(pianzhiScore).setPianzhiAverScore(pianzhiAverScore).setPianzhiAnswerInfoId(pianzhiAnswerInfoId)
                .setJingshenScore(jingshenScore).setJingshenAverScore(jingshenAverScore).setJingshenAnswerInfoId(jingshenAnswerInfoId)
                .setQitaScore(qitaScore).setQitaAverScore(qitaAverScore).setQitaAnswerInfoId(qitaAnswerInfoId);

        // 记录规则解释
        if (StringUtils.isNotEmpty(result)) {   // 去掉最后一个顿号
            result = result.substring(0, result.lastIndexOf("、"));
        }
        if (StringUtils.isNotEmpty(advice)) {
            advice = advice.substring(0, advice.lastIndexOf("、"));
        }
        resultScl90.setResultHeart(resultMap.get(yAnswerInfoId).getDescribe())
                .setResult(result).setAdvise(advice);

        return super.insert(resultScl90);
    }

    /**
     * 写死的内容，与数据库匹配
     */
    @Component
    static class AnswerBuilder {

        /**
         * 心理不健康 总分≥160 或 阳性项目≥43 或 任一因子分≥2
         */
        public static String xlbjk;

        /**
         * 人际关系敏感 因子分≥2
         */
        public static String rjgxmg;

        /**
         * 抑郁 因子分≥2
         */
        public static String yy;

        /**
         * 焦虑 因子分≥2
         */
        public static String jl;

        /**
         * 敌对 因子分≥2
         */
        public static String dd;

        /**
         * 恐怖 因子分≥2
         */
        public static String kb;

        /**
         * 偏执 因子分≥2
         */
        public static String pz;

        /**
         * 精神病性 因子分≥2
         */
        public static String jsbx;

        /**
         * 心里健康
         */
        private static String xljk;

        /**
         * 睡眠及饮食差
         */
        private static String smjysc;

        /**
         * 躯体症
         */
        private static String qtz;

        /**
         * 强迫症
         */
        private static String qpz;

        @Value("${jmonkey.gauge.answer-info.scl90.xlbjk}")
        public void setXlbjk(String xlbjk) {
            this.xlbjk = xlbjk;
        }

        @Value("${jmonkey.gauge.answer-info.scl90.rjgxmg}")
        public void setRjgxmg(String rjgxmg) {
            this.rjgxmg = rjgxmg;
        }

        @Value("${jmonkey.gauge.answer-info.scl90.yy}")
        public void setYy(String yy) {
            this.yy = yy;
        }

        @Value("${jmonkey.gauge.answer-info.scl90.jl}")
        public void setJl(String jl) {
            this.jl = jl;
        }

        @Value("${jmonkey.gauge.answer-info.scl90.dd}")
        public void setDd(String dd) {
            this.dd = dd;
        }

        @Value("${jmonkey.gauge.answer-info.scl90.kb}")
        public void setKb(String kb) {
            this.kb = kb;
        }

        @Value("${jmonkey.gauge.answer-info.scl90.pz}")
        public void setPz(String pz) {
            this.pz = pz;
        }

        @Value("${jmonkey.gauge.answer-info.scl90.jsbx}")
        public void setJsbx(String jsbx) {
            this.jsbx = jsbx;
        }

        @Value("${jmonkey.gauge.answer-info.scl90.xljk}")
        public void setXljk(String xljk) {
            this.xljk = xljk;
        }

        @Value("${jmonkey.gauge.answer-info.scl90.smjysc}")
        public void setSmjysc(String smjysc) {
            this.smjysc = smjysc;
        }

        @Value("${jmonkey.gauge.answer-info.scl90.qtz}")
        public void setQtz(String qtz) {
            this.qtz = qtz;
        }

        @Value("${jmonkey.gauge.answer-info.scl90.qpz}")
        public void setQpz(String qpz) {
            this.qpz = qpz;
        }
    }
}
