package com.wang.jmonkey.modules.ieg.api;

import com.wang.jmonkey.common.http.abs.BaseHttp;
import com.wang.jmonkey.common.http.result.HttpPageResult;
import com.wang.jmonkey.common.http.result.HttpResult;
import com.wang.jmonkey.modules.ieg.model.dto.IegSchoolLogChartDto;
import com.wang.jmonkey.modules.ieg.model.entity.IegSchoolLog;
import com.wang.jmonkey.modules.ieg.model.param.IegSchoolLogParam;
import com.wang.jmonkey.modules.ieg.service.IIegSchoolLogService;

import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description: 报考指南——顾问查看院校详细信息记录 api
 * @Auther: HeJiawang
 * @Date: 2019-12-16
 */
@RestController
@RequestMapping("/ieg/school/log")
public class IegSchoolLogApi extends BaseHttp {

    @Resource
    private IIegSchoolLogService service;

    /**
     * count
     * @param param param
     * @return IegSchoolLogChartDto
     */
    @PostMapping(value = "/count")
    public HttpResult<List<IegSchoolLogChartDto>> count(@RequestBody IegSchoolLogParam param) {
        return new HttpResult<>(service.count(param));
    }

    /**
     * 分页查询信息
     * @param param param
     * @return IegSchoolLog
     */
    @PostMapping(value = "/list")
    public HttpPageResult<IegSchoolLog> list(@RequestBody IegSchoolLogParam param) {
        param.buildLimitStart();

        return new HttpPageResult<>( service.selectPageList( param ) );
    }

    /**
     * 保存实体信息
     * @param entity 实体信息
     * @return
     */
    @PostMapping(value = "/save")
    public HttpResult<Boolean> save( @RequestBody IegSchoolLog entity ){
        return new HttpResult<>(service.insert(entity));
    }

}
