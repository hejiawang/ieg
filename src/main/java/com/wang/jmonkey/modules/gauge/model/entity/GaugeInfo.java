package com.wang.jmonkey.modules.gauge.model.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.wang.jmonkey.common.model.BaseEntity;

import com.wang.jmonkey.common.model.enums.YesOrNoEnum;
import com.wang.jmonkey.modules.gauge.model.enums.GaugeAnswerInfoTypeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 量表————基本信息
 * </p>
 *
 * @author HeJiawang
 * @since 2019-09-25
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class GaugeInfo extends BaseEntity<GaugeInfo> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private String id;
    /**
     * 量表名称
     */
    private String name;
    /**
     * 图标路径
     */
    private String iconPath;
    /**
     * 排序值
     */
    private Integer sort;
    /**
     * 量表测评策略 气质量表Quality 行为量表Action SCL90量表 SCL90
     */
    @JSONField(serialzeFeatures= SerializerFeature.WriteEnumUsingToString)
    private GaugeAnswerInfoTypeEnum answerType;
    /**
     * 量表说明
     */
    private String describe;

    /**
     * 是否收集微表情 Yes收集 No不收集
     */
    @JSONField(serialzeFeatures= SerializerFeature.WriteEnumUsingToString)
    private YesOrNoEnum isLook;

    /**
     * 是否必须完成 Yes必须 No不必须
     */
    @JSONField(serialzeFeatures= SerializerFeature.WriteEnumUsingToString)
    private YesOrNoEnum isMust;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
