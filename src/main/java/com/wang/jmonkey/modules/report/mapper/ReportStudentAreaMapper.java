package com.wang.jmonkey.modules.report.mapper;

import com.wang.jmonkey.modules.report.model.entity.ReportStudentArea;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 报告————学生意向城市 Mapper 接口
 * </p>
 *
 * @author HeJiawang
 * @since 2019-08-03
 */
public interface ReportStudentAreaMapper extends BaseMapper<ReportStudentArea> {

    /**
     * deleteByStudentId
     * @param studentId studentId
     * @return int
     */
    int deleteByStudentId(@Param("studentId") String studentId);
}
