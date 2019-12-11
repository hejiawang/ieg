package com.wang.jmonkey.modules.ieg.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.wang.jmonkey.modules.ieg.mapper.IegReportMapper;
import com.wang.jmonkey.modules.ieg.model.dto.IegReportDetailDto;
import com.wang.jmonkey.modules.ieg.model.dto.IegReportListDto;
import com.wang.jmonkey.modules.ieg.model.dto.IegSchoolInfoDto;
import com.wang.jmonkey.modules.ieg.model.entity.IegEnvironment;
import com.wang.jmonkey.modules.ieg.model.param.IegReportSearchParam;
import com.wang.jmonkey.modules.ieg.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * <p>
 * 报考指南——报告信息 服务类 impl
 * </p>
 *
 * @author heJiawang
 * @since 2019-11-23
 */
@Service
public class IegReportServiceImpl implements IIegReportService {

    /**
     * mapper
     */
    @Autowired
    private IegReportMapper mapper;

    /**
     * iegSchoolFeaturesService
     */
    @Autowired
    private IIegSchoolFeaturesService iegSchoolFeaturesService;

    /**
     * iegSchoolService
     */
    @Autowired
    private IIegSchoolService iegSchoolService;

    /**
     * iegSchoolDetailService
     */
    @Autowired
    private IIegSchoolDetailService iegSchoolDetailService;

    /**
     * iegEnvironmentService
     */
    @Autowired
    private IIegEnvironmentService iegEnvironmentService;

    /**
     * iegSchoolProblemService
     */
    @Autowired
    private IIegSchoolProblemService iegSchoolProblemService;

    /**
     * iegSchoolFacultyService
     */
    @Autowired
    private IIegSchoolFacultyService iegSchoolFacultyService;

    /**
     * iegEnrollService
     */
    @Autowired
    private IIegEnrollService iegEnrollService;

    /**
     * 检索页面 list数据
     * @param param param
     * @return IegReportListDto
     */
    @Override
    public Page<IegReportListDto> list(IegReportSearchParam param) {
        List<IegReportListDto> result = mapper.list(param);
        result.forEach(report -> {
            report.setFeatureNames(
                iegSchoolFeaturesService.selectFeatureNames(report.getSchoolId())
            );

            /*
            List<String> feaMajorList = mapper.feaMajorList(report.getSchoolId());
            if (CollectionUtil.isEmpty(feaMajorList)) {
                feaMajorList = mapper.maxMajorList(report.getSchoolId());
            }
            */
            List<String> feaMajorList = mapper.maxMajorList(report.getSchoolId());

            report.setMajorNames(feaMajorList);
        });

        Page<IegReportListDto> pageResult = new Page<>();
        pageResult.setRecords(result)
                .setTotal(mapper.listTotal(param))
                .setCurrent(param.getCurrent())
                .setSize(param.getSize());

        return pageResult;
    }

    /**
     * 院校详细信息
     * @param schoolId 院校id
     * @return 院校详细信息
     */
    @Override
    public IegReportDetailDto detail(String schoolId) {
        IegReportDetailDto result = new IegReportDetailDto();

        IegSchoolInfoDto school = iegSchoolService.findInfoDtoById(schoolId);
        IegEnvironment environment = iegEnvironmentService.findByAreaCity(school.getAreaCity());

        result.setSchool(
                school
        ).setSchoolDetail(
                iegSchoolDetailService.selectBySchoolId(schoolId)
        ).setFeatureList(
                iegSchoolFeaturesService.selectFeatureNames(schoolId)
        ).setEnvironment(
                environment == null ? "" : environment.getDescribe()
        ).setProblemList(
                iegSchoolProblemService.selectBySchoolId(schoolId)
        ).setFacultyList(
                iegSchoolFacultyService.selectBySchoolId(schoolId)
        ).setMajorList(
                mapper.selectMajorBySchoolId(schoolId)
        ).setEnrollList(
                iegEnrollService.selectDtoBySchoolName(school.getName())
        );

        return result;
    }
}
