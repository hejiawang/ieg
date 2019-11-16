package com.wang.jmonkey.modules.gauge.api;

import com.wang.jmonkey.common.http.abs.BaseHttp;
import com.wang.jmonkey.common.http.result.HttpResult;
import com.wang.jmonkey.modules.gauge.model.entity.GaugeResultScl90;
import com.wang.jmonkey.modules.gauge.service.IGaugeResultScl90Service;

import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Description: 量表————scl90量表结果记录信息 api
 * @Auther: HeJiawang
 * @Date: 2019-10-08
 */
@RestController
@RequestMapping("/gauge/result/scl")
public class GaugeResultScl90Api extends BaseHttp {

    @Resource
    private IGaugeResultScl90Service service;

    /**
     * 修改实体信息
     * @param entity 实体信息
     * @return Boolean
     */
    @PutMapping(value = "/modify")
    public HttpResult<Boolean> modify( @RequestBody GaugeResultScl90 entity ){
        return new HttpResult<>(service.updateById(entity));
    }

    /**
     * selectByRecordId
     * @param recordId recordId
     * @return HttpResult
     */
    @GetMapping(value = "/find/{recordId}")
    public HttpResult<GaugeResultScl90> selectByRecordId(@PathVariable String recordId ){
        return new HttpResult<>(service.selectByRecordId(recordId));
    }

}
