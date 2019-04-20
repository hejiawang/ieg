package com.wang.jmonkey.modules.sys.api;

import com.wang.jmonkey.common.http.abs.BaseHttp;
import com.wang.jmonkey.common.http.result.HttpResult;
import com.wang.jmonkey.modules.sys.model.dto.SysSystemDto;
import com.wang.jmonkey.modules.sys.model.entity.SysSystem;
import com.wang.jmonkey.modules.sys.service.ISysSystemService;

import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * @Description: 系统配置 api
 * @Auther: HeJiawang
 * @Date: 2018-12-10
 */
@RestController
@RequestMapping("/sys/system")
public class SysSystemApi extends BaseHttp {

    @Resource
    private ISysSystemService service;

    /**
     * 分页查询信息
     * @return
     */
    @GetMapping(value = "/list")
    public HttpResult<List<SysSystemDto>> list() {
        return new HttpResult<>( service.selectDtoList() );
    }

    /**
     * 保存实体信息
     * @param entity 实体信息
     * @return
     */
    @PostMapping(value = "/save")
    public HttpResult<Boolean> save( @RequestBody SysSystem entity ){
        return new HttpResult<>(service.insert(entity));
    }

    /**
     * 修改实体信息
     * @param entity 实体信息
     * @return
     */
    @PutMapping(value = "/modify")
    public HttpResult<Boolean> modify( @RequestBody SysSystem entity ){
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
    public HttpResult<SysSystem> findById(@PathVariable Serializable id ){
        return new HttpResult<>(service.selectById(id));
    }

}