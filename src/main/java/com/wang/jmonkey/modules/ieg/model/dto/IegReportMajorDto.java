package com.wang.jmonkey.modules.ieg.model.dto;

import com.wang.jmonkey.modules.ieg.model.entity.IegSchoolMajor;
import com.wang.jmonkey.modules.ieg.model.entity.IegSchoolMajorProblem;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @Description: IegReportMajorDto
 * @Auther: heJiawang
 * @Date: 2019-12-03
 */
@Data
@Accessors(chain = true)
public class IegReportMajorDto extends IegSchoolMajor {

    /**
     * 专业特征名称
     */
    private List<String> featureNames;

    /**
     * 所属院系名称
     */
    private String facultyName;

    /**
     * 专业所属学科id
     */
    private String majorTwoName;

    /**
     * 问题汇总
     */
    private List<IegSchoolMajorProblem> problemList;
}
