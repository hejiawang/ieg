package com.wang.jmonkey.modules.gauge.api;

import com.wang.jmonkey.common.http.abs.BaseHttp;
import com.wang.jmonkey.common.http.result.HttpResult;
import com.wang.jmonkey.modules.gauge.model.entity.GaugeAnswerInfo;
import com.wang.jmonkey.modules.gauge.service.IGaugeAnswerInfoService;

import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * @Description: 量表————测评规则信息 api
 * @Auther: HeJiawang
 * @Date: 2019-09-25
 */
@RestController
@RequestMapping("/gauge/answer/info")
public class GaugeAnswerInfoApi extends BaseHttp {

    @Resource
    private IGaugeAnswerInfoService service;

    /**
     * 获取所有测评规则信息
     * @return 测评规则信息
     */
    @GetMapping(value = "/listAll")
    public HttpResult<List<GaugeAnswerInfo>> listAll() {
        return new HttpResult<>( service.listAll() );
    }

    /**
     * 根据策略类型获取评分信息
     * @param entity 实体信息
     * @return List<GaugeAnswerInfo>
     */
    @GetMapping(value = "/list")
    public HttpResult<List<GaugeAnswerInfo>> list(GaugeAnswerInfo entity) {
        return new HttpResult<>( service.selectListByType( entity.getType() ) );
    }

    /**
     * 修改实体信息
     * @param entity 实体信息
     * @return Boolean
     */
    @PutMapping(value = "/modify")
    public HttpResult<Boolean> modify( @RequestBody GaugeAnswerInfo entity ){
        return new HttpResult<>(service.updateById(entity));
    }

    /**
     * 查找实体信息
     * @param id 实体ID
     * @return HttpResult
     */
    @GetMapping(value = "/find/{id}")
    public HttpResult<GaugeAnswerInfo> findById(@PathVariable Serializable id ){
        return new HttpResult<>(service.selectById(id));
    }

}
