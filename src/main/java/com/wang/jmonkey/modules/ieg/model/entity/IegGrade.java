package com.wang.jmonkey.modules.ieg.model.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.wang.jmonkey.common.model.BaseEntity;

import com.wang.jmonkey.common.model.enums.YesOrNoEnum;
import com.wang.jmonkey.common.utils.poi.annotation.ExcelField;
import com.wang.jmonkey.modules.ieg.model.enums.IegCourseTypeEnums;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 报考指南——一分一段表
 * </p>
 *
 * @author HeJiawang
 * @since 2019-04-02
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class IegGrade extends BaseEntity<IegGrade> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private String id;
    /**
     * 年
     */
    private Integer year;
    /**
     * 分数
     */
    @ExcelField(title="分数", align=2)
    private Integer score;
    /**
     * 人数
     */
    @ExcelField(title="人数", align=2)
    private Integer number;
    /**
     * 累计排名
     */
    @ExcelField(title="累计排名", align=2)
    private Integer sort;

    /**
     * 类型 W文科 L理科
     */
    @JSONField(serialzeFeatures= SerializerFeature.WriteEnumUsingToString)
    private IegCourseTypeEnums type;

    /**
     * 状态 No未校验 Yes已校验
     */
    @JSONField(serialzeFeatures= SerializerFeature.WriteEnumUsingToString)
    private YesOrNoEnum state;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
