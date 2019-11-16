package com.wang.jmonkey.modules.gauge.api;

import com.wang.jmonkey.common.http.abs.BaseHttp;
import com.wang.jmonkey.common.http.result.HttpResult;
import com.wang.jmonkey.modules.gauge.model.entity.GaugeResultQuality;
import com.wang.jmonkey.modules.gauge.service.IGaugeResultQualityService;

import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Description: 量表————气质量表结果记录信息 api
 * @Auther: HeJiawang
 * @Date: 2019-09-26
 */
@RestController
@RequestMapping("/gauge/result/quality")
public class GaugeResultQualityApi extends BaseHttp {

    @Resource
    private IGaugeResultQualityService service;

    /**
     * 修改实体信息
     * @param entity 实体信息
     * @return Boolean
     */
    @PutMapping(value = "/modify")
    public HttpResult<Boolean> modify( @RequestBody GaugeResultQuality entity ){
        return new HttpResult<>(service.updateById(entity));
    }

    /**
     * selectByRecordId
     * @param recordId recordId
     * @return HttpResult
     */
    @GetMapping(value = "/find/{recordId}")
    public HttpResult<GaugeResultQuality> selectByRecordId(@PathVariable String recordId ){
        return new HttpResult<>(service.selectByRecordId(recordId));
    }

}
