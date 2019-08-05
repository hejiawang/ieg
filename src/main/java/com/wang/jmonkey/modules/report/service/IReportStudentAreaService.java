package com.wang.jmonkey.modules.report.service;

import com.wang.jmonkey.modules.report.model.entity.ReportStudentArea;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 报告————学生意向城市 服务类
 * </p>
 *
 * @author HeJiawang
 * @since 2019-08-03
 */
public interface IReportStudentAreaService extends IService<ReportStudentArea> {

    /**
     * margeList
     * @param studentId studentId
     * @param areaList areaList
     * @return boolean
     */
    boolean margeList(String studentId, List<ReportStudentArea> areaList);
}
