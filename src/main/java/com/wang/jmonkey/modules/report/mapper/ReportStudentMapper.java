package com.wang.jmonkey.modules.report.mapper;

import com.wang.jmonkey.modules.report.model.dto.ReportStudentDto;
import com.wang.jmonkey.modules.report.model.entity.ReportStudent;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.wang.jmonkey.modules.report.model.param.ReportStudentParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 报告————学生基本信息 Mapper 接口
 * </p>
 *
 * @author HeJiawang
 * @since 2019-08-03
 */
public interface ReportStudentMapper extends BaseMapper<ReportStudent> {

    /**
     * selectDtoById
     * @param id id
     * @return ReportStudentDto
     */
    ReportStudentDto selectDtoById(@Param("id") String id);

    /**
     * listPage
     * @param param param
     * @return ReportStudentDto
     */
    List<ReportStudentDto> listPage(ReportStudentParam param);

    /**
     * listPageTotal
     * @param param param
     * @return long
     */
    long listPageTotal(ReportStudentParam param);

    /**
     * checkCode
     * @param student student
     * @return Boolean
     */
    int checkCode(ReportStudent student);
}
