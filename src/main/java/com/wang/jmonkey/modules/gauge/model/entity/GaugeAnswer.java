package com.wang.jmonkey.modules.gauge.model.entity;

import com.wang.jmonkey.common.model.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 量表————题目答案
 * </p>
 *
 * @author HeJiawang
 * @since 2019-09-25
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class GaugeAnswer extends BaseEntity<GaugeAnswer> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private String id;
    /**
     * 题目id
     */
    private String topicId;
    /**
     * 量表基本信息id
     */
    private String gaugeId;
    /**
     * 答案名称
     */
    private String name;
    /**
     * 分数
     */
    private Integer score;
    /**
     * 排序值
     */
    private Integer sort;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
