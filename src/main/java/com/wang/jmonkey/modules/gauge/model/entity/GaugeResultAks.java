package com.wang.jmonkey.modules.gauge.model.entity;

import com.wang.jmonkey.common.model.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 量表————艾克森量表结果记录信息
 * </p>
 *
 * @author HeJiawang
 * @since 2019-10-19
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class GaugeResultAks extends BaseEntity<GaugeResultAks> {

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
     * p纬度 粗分
     */
    private Integer pScore;
    /**
     * p纬度 T分
     */
    private Integer pTScore;
    /**
     * 测评规则信息id
     */
    private String pAnswerInfoId;
    /**
     * e纬度 粗分
     */
    private Integer eScore;
    /**
     * e纬度 T分
     */
    private Integer eTScore;
    /**
     * 测评规则信息id
     */
    private String eAnswerInfoId;
    /**
     * n纬度 粗分
     */
    private Integer nScore;
    /**
     * n纬度 T分
     */
    private Integer nTScore;
    /**
     * 测评规则信息id
     */
    private String nAnswerInfoId;
    /**
     * l纬度 粗分
     */
    private Integer lScore;
    /**
     * l纬度 T分
     */
    private Integer lTScore;

    /**
     * PEN规则解释
     */
    private String result;

    /**
     * 专业建议
     */
    private String advise;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
