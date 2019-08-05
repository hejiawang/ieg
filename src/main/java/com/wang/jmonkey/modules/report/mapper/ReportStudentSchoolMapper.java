package com.wang.jmonkey.modules.report.mapper;

import com.wang.jmonkey.modules.report.model.entity.ReportStudentSchool;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 报告————学生意向院校 Mapper 接口
 * </p>
 *
 * @author HeJiawang
 * @since 2019-08-03
 */
public interface ReportStudentSchoolMapper extends BaseMapper<ReportStudentSchool> {

    /**
     * deleteByStudentId
     * @param studentId studentId
     * @return int
     */
    int deleteByStudentId(@Param("studentId") String studentId);
}
