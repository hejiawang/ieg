package com.wang.jmonkey.modules.report.service;

import com.wang.jmonkey.modules.report.model.entity.ReportStudentRecord;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 报告————学生来访记录 服务类
 * </p>
 *
 * @author HeJiawang
 * @since 2019-08-03
 */
public interface IReportStudentRecordService extends IService<ReportStudentRecord> {

    /**
     * 查询信息
     * @param entity 实体信息
     * @return HttpResult
     */
    List<ReportStudentRecord> list(ReportStudentRecord entity);
}
