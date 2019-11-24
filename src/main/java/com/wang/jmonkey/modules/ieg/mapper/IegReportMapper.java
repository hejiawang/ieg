package com.wang.jmonkey.modules.ieg.mapper;

import com.wang.jmonkey.modules.ieg.model.dto.IegReportListDto;
import com.wang.jmonkey.modules.ieg.model.param.IegReportSearchParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 报考指南——报告信息 Mapper
 * </p>
 *
 * @author heJiawang
 * @since 2019-11-23
 */
public interface IegReportMapper {

    /**
     * list
     * @param param param
     * @return IegReportListDto
     */
    List<IegReportListDto> list(IegReportSearchParam param);

    /**
     * listTotal
     * @param param param
     * @return long
     */
    long listTotal(IegReportSearchParam param);

    /**
     * 获取院校特色专业
     * @param schoolId 院校id
     * @return 特色专业名称
     */
    List<String> feaMajorList(@Param("schoolId") String schoolId);

    /**
     * 最高分的5个专业
     * @param schoolId 院校id
     * @return 5个专业名称
     */
    List<String> maxMajorList(@Param("schoolId") String schoolId);
}
