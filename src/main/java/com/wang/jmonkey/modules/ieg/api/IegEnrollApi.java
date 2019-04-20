package com.wang.jmonkey.modules.ieg.api;

import com.baomidou.mybatisplus.plugins.Page;
import com.wang.jmonkey.common.http.abs.BaseHttp;
import com.wang.jmonkey.common.http.result.HttpPageResult;
import com.wang.jmonkey.common.http.result.HttpResult;
import com.wang.jmonkey.modules.ieg.model.entity.IegEnroll;
import com.wang.jmonkey.modules.ieg.service.IIegEnrollService;

import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;

/**
 * @Description: 报考指南——招生录取投档线类型 api
 * @Auther: HeJiawang
 * @Date: 2019-04-04
 */
@RestController
@RequestMapping("/ieg/enroll")
public class IegEnrollApi extends BaseHttp {

    /**
     * sevice
     */
    @Resource
    private IIegEnrollService service;

    /**
     * 分页查询信息
     * @param page page
     * @param entity 实体信息
     * @return
     */
    @GetMapping(value = "/list")
    public HttpPageResult<IegEnroll> list(Page<IegEnroll> page, IegEnroll entity) {
        return new HttpPageResult<>( service.listPage( page, entity ) );
    }

    /**
     * 保存实体信息
     * @param entity 实体信息
     * @return
     */
    @PostMapping(value = "/save")
    public HttpResult<Boolean> save( @RequestBody IegEnroll entity ){
        return new HttpResult<>(service.insert(entity));
    }

    /**
     * 修改实体信息
     * @param entity 实体信息
     * @return
     */
    @PutMapping(value = "/modify")
    public HttpResult<Boolean> modify( @RequestBody IegEnroll entity ){
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
    @GetMapping(value = "/find/{id}")
    public HttpResult<IegEnroll> findById(@PathVariable Serializable id ){
        return new HttpResult<>(service.selectById(id));
    }

    /**
     * 校验信息是否存在
     * @param entity entity
     * @return Boolean
     */
    @PostMapping(value = "/checkExist")
    public HttpResult<Boolean> checkExist( @RequestBody IegEnroll entity ) {
        return new HttpResult<>(service.checkExist(entity));
    }

}