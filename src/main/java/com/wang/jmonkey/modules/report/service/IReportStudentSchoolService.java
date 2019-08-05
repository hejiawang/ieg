package com.wang.jmonkey.modules.report.service;

import com.wang.jmonkey.modules.report.model.entity.ReportStudentSchool;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 报告————学生意向院校 服务类
 * </p>
 *
 * @author HeJiawang
 * @since 2019-08-03
 */
public interface IReportStudentSchoolService extends IService<ReportStudentSchool> {

    /**
     * margeList
     * @param studentId studentId
     * @param schoolList schoolList
     * @return boolean
     */
    boolean margeList(String studentId, List<ReportStudentSchool> schoolList);
}
