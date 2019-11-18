package com.wang.jmonkey.modules.gauge.model.entity;

import com.wang.jmonkey.common.model.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 量表————行为量表结果记录信息
 * </p>
 *
 * @author HeJiawang
 * @since 2019-09-26
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class GaugeResultAction extends BaseEntity<GaugeResultAction> {

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
     * 测评规则信息id
     */
    private String totalAnswerInfoId;
    /**
     * th分数
     */
    private Integer thScore;
    /**
     * 测评规则信息id
     */
    private String thAnswerInfoId;
    /**
     * ch分数
     */
    private Integer chScore;
    /**
     * 测评规则信息id
     */
    private String chAnswerInfoId;
    /**
     * l分数
     */
    private Integer lScore;
    /**
     * 测评规则信息id, 如果达到l 的标准，记录id，否则为空
     */
    private String lAnswerInfoId;

    /**
     * A型到B型之间的规则解释
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
