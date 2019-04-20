package com.wang.jmonkey.modules.sys.api;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.wang.jmonkey.common.http.abs.BaseHttp;
import com.wang.jmonkey.common.http.result.HttpPageResult;
import com.wang.jmonkey.common.http.result.HttpResult;
import com.wang.jmonkey.modules.sys.model.dto.SysDataScopeDto;
import com.wang.jmonkey.modules.sys.model.entity.SysDataScope;
import com.wang.jmonkey.modules.sys.service.ISysDataScopeService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * @Description: 获取数据规则定义 api
 * @Auther: HeJiawang
 * @Date: 2019-03-20
 */
@RestController
@RequestMapping("/sys/data/scope")
public class SysDataScopeApi extends BaseHttp {

    @Resource
    private ISysDataScopeService service;

    /**
     * 分页查询信息
     * @param page page
     * @return
     */
    @GetMapping(value = "/list")
    public HttpPageResult<SysDataScope> list(Page<SysDataScope> page) {
        return new HttpPageResult<>( service.list(page, dataScope) );
    }

    /**
     * 获取数据规则信息
     * @return List<SysDataScopeDto>
     */
    @GetMapping(value = "/listDto")
    public HttpResult<List<SysDataScopeDto>> listDto() {
        return new HttpResult<>(service.listDto());
    }

    /**
     * 保存实体信息
     * @param entity 实体信息
     * @return
     */
    @PostMapping(value = "/save")
    public HttpResult<Boolean> save( @RequestBody SysDataScope entity ){
        return new HttpResult<>(service.insert(entity));
    }

    /**
     * 修改实体信息
     * @param entity 实体信息
     * @return
     */
    @PutMapping(value = "/modify")
    public HttpResult<Boolean> modify( @RequestBody SysDataScope entity ){
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
    public HttpResult<SysDataScope> findById(@PathVariable Serializable id ){
        return new HttpResult<>(service.selectById(id));
    }

    /**
     * 校验名称是否存在
     * @param dataScope
     * @return Boolean
     */
    @PostMapping(value = "/checkName")
    public HttpResult<Boolean> checkName( @RequestBody SysDataScope dataScope){
        return new HttpResult<>(service.checkName(dataScope));
    }

    /**
     * 校验拦截路径是否重复
     * @param dataScope
     * @return Boolean
     */
    @PostMapping(value = "/checkUrl")
    public HttpResult<Boolean> checkUrl( @RequestBody SysDataScope dataScope){
        return new HttpResult<>(service.checkUrl(dataScope));
    }
}