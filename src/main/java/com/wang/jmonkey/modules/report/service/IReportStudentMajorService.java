package com.wang.jmonkey.modules.report.service;

import com.wang.jmonkey.modules.report.model.entity.ReportStudentMajor;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 报告————学生意向专业 服务类
 * </p>
 *
 * @author HeJiawang
 * @since 2019-08-03
 */
public interface IReportStudentMajorService extends IService<ReportStudentMajor> {

    /**
     * margeList
     * @param studentId studentId
     * @param majorList majorList
     * @return boolean
     */
    boolean margeList(String studentId, List<ReportStudentMajor> majorList);
}
