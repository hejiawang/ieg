package com.wang.jmonkey.modules.ieg.model.entity;

import com.wang.jmonkey.common.model.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 报考指南——招生录取投档线类型
 * </p>
 *
 * @author HeJiawang
 * @since 2019-04-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class IegEnroll extends BaseEntity<IegEnroll> {

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
     * 学科类型 W文科 L理科
     */
    private String courseType;
    /**
     * 学历层次 B本科 Z专科
     */
    private String degreeType;
    /**
     * 招收类型 Common普通 Art艺术 Gym体育
     */
    private String enrollType;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
