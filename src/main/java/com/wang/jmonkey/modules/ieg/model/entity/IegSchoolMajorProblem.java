package com.wang.jmonkey.modules.ieg.model.entity;

import com.wang.jmonkey.common.model.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 报考指南——学校专业问题汇总
 * </p>
 *
 * @author HeJiawang
 * @since 2019-03-31
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class IegSchoolMajorProblem extends BaseEntity<IegSchoolMajorProblem> {

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
     * 问题
     */
    private String problem;
    /**
     * 回答
     */
    private String answer;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
