package com.wang.jmonkey.modules.gauge.model.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.wang.jmonkey.common.model.BaseEntity;

import com.wang.jmonkey.modules.gauge.model.enums.GaugeAnswerInfoTypeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 量表————测评规则信息
 * </p>
 *
 * @author HeJiawang
 * @since 2019-09-25
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class GaugeAnswerInfo extends BaseEntity<GaugeAnswerInfo> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private String id;
    /**
     * 规则类型 每个类型有多个规则 气质量表Quality 行为量表Action
     */
    @JSONField(serialzeFeatures= SerializerFeature.WriteEnumUsingToString)
    private GaugeAnswerInfoTypeEnum type;
    /**
     * 规则名称
     */
    private String name;
    /**
     * 规则解释
     */
    private String describe;
    /**
     * 矫正建议
     */
    private String advise;
    /**
     * 评分规则描述 ，不可修改
     */
    private String content;
    /**
     * 排序值
     */
    private Integer sort;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
