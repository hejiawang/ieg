package com.wang.jmonkey.modules.ieg.model.dto;

import com.wang.jmonkey.modules.ieg.model.entity.IegSchool;
import com.wang.jmonkey.modules.ieg.model.entity.IegSchoolDetail;
import com.wang.jmonkey.modules.ieg.model.entity.IegSchoolFaculty;
import com.wang.jmonkey.modules.ieg.model.entity.IegSchoolProblem;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @Description: IegReportDetailDto
 * @Auther: heJiawang
 * @Date: 2019-12-03
 */
@Data
@Accessors(chain = true)
public class IegReportDetailDto {

    /**
     * 院校基本信息
     */
    private IegSchoolInfoDto school;

    /**
     * 院校详细信息
     */
    private IegSchoolDetail schoolDetail;

    /**
     * 院校特征
     */
    private List<String> featureList;

    /**
     * 院校所在地气候信息
     */
    private String environment;

    /**
     * 院校常见问题汇总
     */
    private List<IegSchoolProblem> problemList;

    /**
     * 院系信息
     */
    private List<IegSchoolFaculty> facultyList;

    /**
     * 院校专业信息
     */
    private List<IegReportMajorDto> majorList;

}
