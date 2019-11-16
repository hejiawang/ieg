package com.wang.jmonkey.modules.gauge.api;

import com.wang.jmonkey.common.http.abs.BaseHttp;
import com.wang.jmonkey.common.http.result.HttpResult;
import com.wang.jmonkey.modules.gauge.model.entity.GaugeResultAction;
import com.wang.jmonkey.modules.gauge.service.IGaugeResultActionService;

import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Description: 量表————行为量表结果记录信息 api
 * @Auther: HeJiawang
 * @Date: 2019-09-26
 */
@RestController
@RequestMapping("/gauge/result/action")
public class GaugeResultActionApi extends BaseHttp {

    @Resource
    private IGaugeResultActionService service;

    /**
     * 修改实体信息
     * @param entity 实体信息
     * @return Boolean
     */
    @PutMapping(value = "/modify")
    public HttpResult<Boolean> modify( @RequestBody GaugeResultAction entity ){
        return new HttpResult<>(service.updateById(entity));
    }

    /**
     * 查找实体信息
     * @param recordId recordId
     * @return HttpResult
     */
    @GetMapping(value = "/find/{recordId}")
    public HttpResult<GaugeResultAction> findById(@PathVariable String recordId ){
        return new HttpResult<>(service.selectByRecordId(recordId));
    }

}
