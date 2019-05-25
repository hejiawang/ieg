package com.wang.jmonkey.modules.ieg.model.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.wang.jmonkey.common.model.BaseEntity;

import com.wang.jmonkey.common.model.enums.YesOrNoEnum;
import com.wang.jmonkey.modules.ieg.model.enums.IegCourseTypeEnums;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 报考指南——学校专业历年录取信息
 * </p>
 *
 * @author HeJiawang
 * @since 2019-04-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class IegSchoolMajorEnrollRecord extends BaseEntity<IegSchoolMajorEnrollRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private String id;
    /**
     * 学校基本信息ID
     */
    private String schoolMajorId;
    /**
     * 年
     */
    private Integer year;
    /**
     * 类型 W文科 L理科
     */
    @JSONField(serialzeFeatures= SerializerFeature.WriteEnumUsingToString)
    private IegCourseTypeEnums type;
    /**
     * 最低分
     */
    private Double scoreMin;
    /**
     * 最高分
     */
    private Double scoreMax;
    /**
     * 计划招收人数
     */
    private Integer planNumber;
    /**
     * 实际招收人数
     */
    private Integer realNumber;

    /**
     * 校验状态
     */
    @JSONField(serialzeFeatures= SerializerFeature.WriteEnumUsingToString)
    private YesOrNoEnum state;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
