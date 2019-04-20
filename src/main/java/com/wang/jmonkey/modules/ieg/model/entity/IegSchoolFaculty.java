package com.wang.jmonkey.modules.ieg.model.entity;

import com.wang.jmonkey.common.model.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 报考指南——学校院系信息
 * </p>
 *
 * @author HeJiawang
 * @since 2019-03-29
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class IegSchoolFaculty extends BaseEntity<IegSchoolFaculty> {

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
     * 学院名称
     */
    private String name;
    /**
     * 学院网址
     */
    private String website;
    /**
     * 学院联系方式
     */
    private String phone;
    /**
     * 排名
     */
    private Integer sort;
    /**
     * 学校院系简介
     */
    private String describe;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
