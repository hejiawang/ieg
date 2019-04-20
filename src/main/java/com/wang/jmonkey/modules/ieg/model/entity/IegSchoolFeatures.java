package com.wang.jmonkey.modules.ieg.model.entity;

import com.wang.jmonkey.common.model.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 报考指南——学校特性
 * </p>
 *
 * @author HeJiawang
 * @since 2019-03-27
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class IegSchoolFeatures extends BaseEntity<IegSchoolFeatures> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private String id;
    /**
     * 学校基本信息ID
     */
    private String schoolId;
    /**
     * 学校特性类型 字典——ieg-school-features-type
     */
    private String type;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
