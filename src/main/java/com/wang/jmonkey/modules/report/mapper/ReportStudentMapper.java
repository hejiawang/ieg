package com.wang.jmonkey.modules.report.mapper;

import com.wang.jmonkey.modules.report.model.dto.ReportStudentDto;
import com.wang.jmonkey.modules.report.model.entity.ReportStudent;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

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
}
