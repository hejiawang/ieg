package com.wang.jmonkey.modules.gauge.api;

import com.wang.jmonkey.common.http.abs.BaseHttp;
import com.wang.jmonkey.common.http.result.HttpResult;
import com.wang.jmonkey.modules.gauge.model.entity.GaugeResultAks;
import com.wang.jmonkey.modules.gauge.service.IGaugeResultAksService;

import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Description: 量表————艾克森量表结果记录信息 api
 * @Auther: HeJiawang
 * @Date: 2019-10-19
 */
@RestController
@RequestMapping("/gauge/result/aks")
public class GaugeResultAksApi extends BaseHttp {

    @Resource
    private IGaugeResultAksService service;

    /**
     * 修改实体信息
     * @param entity 实体信息
     * @return Boolean
     */
    @PutMapping(value = "/modify")
    public HttpResult<Boolean> modify( @RequestBody GaugeResultAks entity ){
        return new HttpResult<>(service.updateById(entity));
    }

    /**
     * selectByRecordId
     * @param recordId recordId
     * @return HttpResult
     */
    @GetMapping(value = "/find/{recordId}")
    public HttpResult<GaugeResultAks> findById(@PathVariable String recordId ){
        return new HttpResult<>(service.selectByRecordId(recordId));
    }

}
