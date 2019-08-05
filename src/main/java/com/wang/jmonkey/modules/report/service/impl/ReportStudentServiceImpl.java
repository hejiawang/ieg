package com.wang.jmonkey.modules.report.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.wang.jmonkey.modules.report.model.dto.ReportStudentDto;
import com.wang.jmonkey.modules.report.model.entity.ReportStudent;
import com.wang.jmonkey.modules.report.mapper.ReportStudentMapper;
import com.wang.jmonkey.modules.report.model.entity.ReportStudentArea;
import com.wang.jmonkey.modules.report.model.param.ReportStudentParam;
import com.wang.jmonkey.modules.report.service.IReportStudentAreaService;
import com.wang.jmonkey.modules.report.service.IReportStudentMajorService;
import com.wang.jmonkey.modules.report.service.IReportStudentSchoolService;
import com.wang.jmonkey.modules.report.service.IReportStudentService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

/**
 * <p>
 * 报告————学生基本信息 服务实现类
 * </p>
 *
 * @author HeJiawang
 * @since 2019-08-03
 */
@Service
public class ReportStudentServiceImpl extends ServiceImpl<ReportStudentMapper, ReportStudent> implements IReportStudentService {

    /**
     * mapper
     */
    @Autowired
    private ReportStudentMapper mapper;

    /**
     * areaService
     */
    @Autowired
    private IReportStudentAreaService areaService;

    /**
     * schoolService
     */
    @Autowired
    private IReportStudentSchoolService schoolService;

    /**
     * majorService
     */
    @Autowired
    private IReportStudentMajorService majorService;

    /**
     * listAll
     * @param page page
     * @param param param
     * @return Page
     */
    @Override
    public Page<ReportStudentDto> listAll(Page<ReportStudentDto> page, ReportStudentParam param) {

        // TODO

        return page;
    }

    /**
     * 保存实体信息
     * @param param 实体信息
     * @return Boolean
     */
    @Transactional
    @Override
    public Boolean save(ReportStudentParam param) {
        ReportStudent student = param.converToEntity();

        return super.insert(student)
                && areaService.margeList(student.getId(), param.getAreaList())
                && majorService.margeList(student.getId(), param.getMajorList())
                && schoolService.margeList(student.getId(), param.getSchoolList());
    }

    /**
     * 修改实体信息
     * @param param 实体信息
     * @return Boolean
     */
    @Transactional
    @Override
    public Boolean modify(ReportStudentParam param) {
        ReportStudent student = param.converToEntity();

        return super.updateById(student)
                && areaService.margeList(student.getId(), param.getAreaList())
                && majorService.margeList(student.getId(), param.getMajorList())
                && schoolService.margeList(student.getId(), param.getSchoolList());
    }

    /**
     * selectDtoById
     * @param id id
     * @return ReportStudentDto
     */
    @Override
    public ReportStudentDto selectDtoById(String id) {
        return mapper.selectDtoById(id);
    }
}
