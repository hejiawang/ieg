package com.wang.jmonkey.modules.ieg.api;

import com.wang.jmonkey.common.http.abs.BaseHttp;
import com.wang.jmonkey.common.http.result.HttpPageResult;
import com.wang.jmonkey.common.http.result.HttpResult;
import com.wang.jmonkey.modules.ieg.model.dto.IegReportDetailDto;
import com.wang.jmonkey.modules.ieg.model.dto.IegReportListDto;
import com.wang.jmonkey.modules.ieg.model.param.IegReportSearchParam;
import com.wang.jmonkey.modules.ieg.service.IIegReportService;
import com.xiaoleilu.hutool.collection.CollectionUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * @Description: 报考指南——报告信息 api
 * @Auther: heJiawang
 * @Date: 2019-11-23
 */
@RestController
@RequestMapping("/ieg/report")
public class IegReportApi extends BaseHttp {

    /**
     * service
     */
    @Autowired
    private IIegReportService service;

    /**
     * 检索页面 list数据
     * @param param param
     * @return IegReportListDto
     */
    @PostMapping(value = "/list")
    public HttpPageResult<IegReportListDto> list (@RequestBody IegReportSearchParam param) {
        param.buildLimitStart();

        // 处理省市区参数
        List<String> areaList = param.getArea();
        param.setAreaProvince(
                CollectionUtil.isNotEmpty(areaList) && areaList.size() > 0 ? areaList.get(0) : StringUtils.EMPTY
        );
        param.setAreaCity(
                CollectionUtil.isNotEmpty(areaList) && areaList.size() > 1 ? areaList.get(1) : StringUtils.EMPTY
        );
        param.setAreaArea(
                CollectionUtil.isNotEmpty(areaList) && areaList.size() > 2 ? areaList.get(2) : StringUtils.EMPTY
        );

        if (StringUtils.isNotEmpty(param.getName())) {
            param.setNameList(
                    Arrays.asList(param.getName().split(" "))
            );
        }

        if (StringUtils.isNotEmpty(param.getMajor())) {
            param.setMajorList(
                    Arrays.asList(param.getMajor().split(" "))
            );
        }

        return new HttpPageResult<>(service.list(param));
    }

    /**
     * 院校详细信息
     * @param schoolId 院校id
     * @return 院校详细信息
     */
    @GetMapping(value = "/detail/{schoolId}")
    public HttpResult<IegReportDetailDto> detail(@PathVariable String schoolId) {
        return new HttpResult<>(service.detail(schoolId));
    }

}
