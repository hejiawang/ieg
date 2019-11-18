package com.wang.jmonkey.modules.gauge.model.entity;

import com.wang.jmonkey.common.model.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 量表————scl90量表结果记录信息
 * </p>
 *
 * @author HeJiawang
 * @since 2019-10-08
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class GaugeResultScl90 extends BaseEntity<GaugeResultScl90> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private String id;
    /**
     * 服刑人员测评记录id
     */
    private String recordId;
    /**
     * 总分 90个项目单项分相加之和
     */
    private Integer totalScore;
    /**
     * 总均分 总分/90
     */
    private Double totalAverScore;
    /**
     * 阳性项目数 单项分≥2的项目数
     */
    private Integer yNum;
    /**
     * 阳性症状均分 （总分－阴性项目数）/阳性项目数
     */
    private Double yAverScore;
    /**
     * 阴性项目数 单项分=1的项目数
     */
    private Integer nNum;
    /**
     * 测评规则信息id
     */
    private String yAnswerInfoId;
    /**
     * 躯体症状总分 单项分相加之和
     */
    private Integer qutiScore;
    /**
     * 躯体症状因子分 单项分相加之和/项目数
     */
    private Double qutiAverScore;
    /**
     * 测评规则信息id
     */
    private String qutiAnswerInfoId;
    /**
     * 强迫症状总分 单项分相加之和
     */
    private Integer qiangpozhengScore;
    /**
     * 强迫症状因子分 单项分相加之和/项目数
     */
    private Double qiangpozhengAverScore;
    /**
     * 测评规则信息id
     */
    private String qiangpozhengAnswerInfoId;
    /**
     * 人际关系敏感总分 单项分相加之和
     */
    private Integer renjiguanxiScore;
    /**
     * 人际关系敏感因子分 单项分相加之和/项目数
     */
    private Double renjiguanxiAverScore;
    /**
     * 测评规则信息id
     */
    private String renjiguanxiAnswerInfoId;
    /**
     * 抑郁总分 单项分相加之和
     */
    private Integer yiyuScore;
    /**
     * 抑郁因子分 单项分相加之和/项目数
     */
    private Double yiyuAverScore;
    /**
     * 测评规则信息id
     */
    private String yiyuAnswerInfoId;
    /**
     * 焦虑总分 单项分相加之和
     */
    private Integer jiaolvScore;
    /**
     * 焦虑因子分 单项分相加之和/项目数
     */
    private Double jiaolvAverScore;
    /**
     * 测评规则信息id
     */
    private String jiaolvAnswerInfoId;
    /**
     * 敌对总分 单项分相加之和
     */
    private Integer diduiScore;
    /**
     * 敌对因子分 单项分相加之和/项目数
     */
    private Double diduiAverScore;
    /**
     * 测评规则信息id
     */
    private String diduiAnswerInfoId;
    /**
     * 恐怖总分 单项分相加之和
     */
    private Integer kongbuScore;
    /**
     * 恐怖因子分 单项分相加之和/项目数
     */
    private Double kongbuAverScore;
    /**
     * 测评规则信息id
     */
    private String kongbuAnswerInfoId;
    /**
     * 偏执总分 单项分相加之和
     */
    private Integer pianzhiScore;
    /**
     * 偏执因子分 单项分相加之和/项目数
     */
    private Double pianzhiAverScore;
    /**
     * 测评规则信息id
     */
    private String pianzhiAnswerInfoId;
    /**
     * 精神病性总分 单项分相加之和
     */
    private Integer jingshenScore;
    /**
     * 精神病性因子分 单项分相加之和/项目数
     */
    private Double jingshenAverScore;
    /**
     * 测评规则信息id
     */
    private String jingshenAnswerInfoId;
    /**
     * 其他总分 单项分相加之和
     */
    private Integer qitaScore;
    /**
     * 其他因子分 单项分相加之和/项目数
     */
    private Double qitaAverScore;
    /**
     * 测评规则信息id
     */
    private String qitaAnswerInfoId;

    /**
     * 因子分大于等于2的名称
     */
    private String result;

    /**
     * 此人心里不健康或健康的规则
     */
    private String resultHeart;

    /**
     * 专业建议
     */
    private String advise;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
