package com.wang.jmonkey.modules.ieg.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.wang.jmonkey.modules.ieg.model.dto.IegReportDetailDto;
import com.wang.jmonkey.modules.ieg.model.dto.IegReportListDto;
import com.wang.jmonkey.modules.ieg.model.param.IegReportSearchParam;

/**
 * <p>
 * 报考指南——报告信息 服务类
 * </p>
 *
 * @author heJiawang
 * @since 2019-11-23
 */
public interface IIegReportService {

    /**
     * 检索页面 list数据
     * @param param param
     * @return IegReportListDto
     */
    Page<IegReportListDto> list(IegReportSearchParam param);

    /**
     * 院校详细信息
     * @param schoolId 院校id
     * @return 院校详细信息
     */
    IegReportDetailDto detail(String schoolId);
}
