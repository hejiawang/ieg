package com.wang.jmonkey.modules.ieg.model.entity;

import com.wang.jmonkey.common.model.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 报考指南——学校历年录取信息
 * </p>
 *
 * @author HeJiawang
 * @since 2019-03-29
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class IegSchoolEnrollRecord extends BaseEntity<IegSchoolEnrollRecord> {

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
     * 年
     */
    private Integer year;
    /**
     * 招生简章
     */
    private String enrollInfo;
    /**
     * 录入规则
     */
    private String enrollRule;
    /**
     * 全国最低分
     */
    private Integer allScoreMin;
    /**
     * 全国最高分
     */
    private Integer allScoreMax;
    /**
     * 全国录取人数
     */
    private Integer allNumber;
    /**
     * 本地最低分
     */
    private Integer localScoreMin;
    /**
     * 本地最高分
     */
    private Integer localScoreMax;
    /**
     * 本地录取人数
     */
    private Integer localNumber;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
