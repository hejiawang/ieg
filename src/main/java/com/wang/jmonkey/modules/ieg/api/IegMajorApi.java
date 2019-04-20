package com.wang.jmonkey.modules.ieg.api;

import com.wang.jmonkey.common.http.abs.BaseHttp;
import com.wang.jmonkey.common.http.result.HttpResult;
import com.wang.jmonkey.modules.ieg.model.dto.IegMajorDto;
import com.wang.jmonkey.modules.ieg.model.dto.IegMajorTreeDto;
import com.wang.jmonkey.modules.ieg.model.entity.IegMajor;
import com.wang.jmonkey.modules.ieg.service.IIegMajorService;

import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * @Description: 报考指南——专业定义 api
 * @Auther: heJiawang
 * @Date: 2019-03-24
 */
@RestController
@RequestMapping("/ieg/major")
public class IegMajorApi extends BaseHttp {

    @Resource
    private IIegMajorService service;

    /**
     * 获取树形数据
     * @param major 专业信息
     * @return 专业树
     */
    @GetMapping(value = "/tree")
    public HttpResult<List<IegMajorTreeDto>> tree(IegMajor major) {
        return new HttpResult<>( service.tree(major) );
    }

    /**
     * 保存实体信息
     * @param entity 实体信息
     * @return
     */
    @PostMapping(value = "/save")
    public HttpResult<Boolean> save( @RequestBody IegMajor entity ){
        return new HttpResult<>(service.insert(entity));
    }

    /**
     * 修改实体信息
     * @param entity 实体信息
     * @return
     */
    @PutMapping(value = "/modify")
    public HttpResult<Boolean> modify( @RequestBody IegMajor entity ){
        return new HttpResult<>(service.updateById(entity));
    }

    /**
     * 删除实体信息
     * @param id 实体ID
     * @return
     */
    @DeleteMapping(value = "/delete/{id}")
    public HttpResult<Boolean> delete( @PathVariable Serializable id ){
        return new HttpResult<>(service.deleteById(id));
    }

    /**
     * 查找实体信息
     * @param id 实体ID
     * @return
     */
    @GetMapping(value = "/findDto/{id}")
    public HttpResult<IegMajorDto> findDtoById(@PathVariable Serializable id ){
        return new HttpResult<>(service.selectDtoById(id));
    }

}