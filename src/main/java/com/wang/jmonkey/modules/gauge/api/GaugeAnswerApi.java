package com.wang.jmonkey.modules.gauge.api;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.wang.jmonkey.common.http.abs.BaseHttp;
import com.wang.jmonkey.common.http.result.HttpPageResult;
import com.wang.jmonkey.common.http.result.HttpResult;
import com.wang.jmonkey.modules.gauge.model.entity.GaugeAnswer;
import com.wang.jmonkey.modules.gauge.service.IGaugeAnswerService;

import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;

/**
 * @Description: 量表————题目答案 api
 * @Auther: HeJiawang
 * @Date: 2019-09-25
 */
@RestController
@RequestMapping("/gauge/answer")
public class GaugeAnswerApi extends BaseHttp {

    @Resource
    private IGaugeAnswerService service;

    /**
     * 分页查询信息
     * @param page page
     * @param entity 实体信息
     * @return page
     */
    @GetMapping(value = "/list")
    public HttpPageResult<GaugeAnswer> list(Page<GaugeAnswer> page, GaugeAnswer entity) {
        EntityWrapper wrapper = new EntityWrapper<GaugeAnswer>();

        return new HttpPageResult<>( service.selectPage( page, wrapper ) );
    }

    /**
     * 保存实体信息
     * @param entity 实体信息
     * @return Boolean
     */
    @PostMapping(value = "/save")
    public HttpResult<Boolean> save( @RequestBody GaugeAnswer entity ){
        return new HttpResult<>(service.insert(entity));
    }

    /**
     * 修改实体信息
     * @param entity 实体信息
     * @return Boolean
     */
    @PutMapping(value = "/modify")
    public HttpResult<Boolean> modify( @RequestBody GaugeAnswer entity ){
        return new HttpResult<>(service.updateById(entity));
    }

    /**
     * 删除实体信息
     * @param id 实体ID
     * @return Boolean
     */
    @DeleteMapping(value = "/delete/{id}")
    public HttpResult<Boolean> delete( @PathVariable Serializable id ){
        return new HttpResult<>(service.deleteById(id));
    }

    /**
     * 查找实体信息
     * @param id 实体ID
     * @return HttpResult
     */
    @GetMapping(value = "/find/{id}")
    public HttpResult<GaugeAnswer> findById(@PathVariable Serializable id ){
        return new HttpResult<>(service.selectById(id));
    }

}
