package com.wang.jmonkey.modules.gauge.model.entity;

import com.wang.jmonkey.common.model.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 量表————气质量表结果记录信息
 * </p>
 *
 * @author HeJiawang
 * @since 2019-09-26
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class GaugeResultQuality extends BaseEntity<GaugeResultQuality> {

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
     * 总分
     */
    private Integer totalScore;
    /**
     * 胆汁分数
     */
    private Integer dzScore;
    /**
     * 多血分数
     */
    private Integer dxScore;
    /**
     * 粘液分数
     */
    private Integer nyScore;
    /**
     * 抑郁分数
     */
    private Integer yyScore;
    /**
     * 测评规则信息id
     */
    private String answerInfoId;

    /**
     * 某个气质的规则解释
     */
    private String result;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
