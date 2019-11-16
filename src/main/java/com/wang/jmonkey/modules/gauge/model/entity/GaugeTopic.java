package com.wang.jmonkey.modules.gauge.model.entity;

import com.wang.jmonkey.common.model.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 量表————题目
 * </p>
 *
 * @author HeJiawang
 * @since 2019-09-25
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class GaugeTopic extends BaseEntity<GaugeTopic> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private String id;
    /**
     * 量表基本信息id
     */
    private String gaugeId;
    /**
     * 题目名称
     */
    private String name;
    /**
     * 排序值
     */
    private Integer sort;
    /**
     * 类型 行为量表gauge_topic_action（TH CH L）气质量表gauge_topic_quality（DZ DX NY YY）
     */
    private String type;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
