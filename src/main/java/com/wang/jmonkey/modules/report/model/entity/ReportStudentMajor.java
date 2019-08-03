package com.wang.jmonkey.modules.report.model.entity;

import com.wang.jmonkey.common.model.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 报告————学生意向专业
 * </p>
 *
 * @author HeJiawang
 * @since 2019-08-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class ReportStudentMajor extends BaseEntity<ReportStudentMajor> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private String id;
    /**
     * 考生主表id
     */
    private String studentId;
    /**
     * 考生意向专业
     */
    private String majorName;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
