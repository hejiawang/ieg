package com.wang.jmonkey.modules.report.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.wang.jmonkey.modules.report.model.dto.ReportStudentDto;
import com.wang.jmonkey.modules.report.model.entity.ReportStudent;
import com.baomidou.mybatisplus.service.IService;
import com.wang.jmonkey.modules.report.model.param.ReportStudentParam;

import java.io.Serializable;

/**
 * <p>
 * 报告————学生基本信息 服务类
 * </p>
 *
 * @author HeJiawang
 * @since 2019-08-03
 */
public interface IReportStudentService extends IService<ReportStudent> {

    /**
     * listAll
     * @param page page
     * @param param param
     * @return Page
     */
    Page<ReportStudentDto> listAll(Page<ReportStudentDto> page, ReportStudentParam param);

    /**
     * 保存实体信息
     * @param param 实体信息
     * @return Boolean
     */
    Boolean save(ReportStudentParam param);

    /**
     * 修改实体信息
     * @param param 实体信息
     * @return Boolean
     */
    Boolean modify(ReportStudentParam param);

    /**
     * selectDtoById
     * @param id id
     * @return ReportStudentDto
     */
    ReportStudentDto selectDtoById(String id);

    /**
     * checkCode
     * @param student student
     * @return Boolean
     */
    Boolean checkCode(ReportStudent student);
}
