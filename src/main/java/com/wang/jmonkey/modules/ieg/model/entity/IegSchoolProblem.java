package com.wang.jmonkey.modules.ieg.model.entity;

import com.wang.jmonkey.common.model.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 报考指南——考生对学校的常见问题以及回答
 * </p>
 *
 * @author HeJiawang
 * @since 2019-03-29
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class IegSchoolProblem extends BaseEntity<IegSchoolProblem> {

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
