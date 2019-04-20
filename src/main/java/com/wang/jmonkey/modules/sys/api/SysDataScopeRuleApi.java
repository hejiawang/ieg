package com.wang.jmonkey.modules.sys.api;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.wang.jmonkey.common.http.abs.BaseHttp;
import com.wang.jmonkey.common.http.result.HttpPageResult;
import com.wang.jmonkey.common.http.result.HttpResult;
import com.wang.jmonkey.modules.sys.model.entity.SysDataScopeRule;
import com.wang.jmonkey.modules.sys.service.ISysDataScopeRuleService;

import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;

/**
 * @Description: 数据规则 api
 * @Auther: HeJiawang
 * @Date: 2019-03-20
 */
@RestController
@RequestMapping("/sys/data/scope/rule")
public class SysDataScopeRuleApi extends BaseHttp {

    @Resource
    private ISysDataScopeRuleService service;

    /**
     * 分页查询信息
     * @param page page
     * @param entity 实体信息
     * @return
     */
    @GetMapping(value = "/list")
    public HttpPageResult<SysDataScopeRule> list(Page<SysDataScopeRule> page, SysDataScopeRule entity) {
        return new HttpPageResult<>( service.selectPageList( page, entity ) );
    }

    /**
     * 保存实体信息
     * @param entity 实体信息
     * @return
     */
    @PostMapping(value = "/save")
    public HttpResult<Boolean> save( @RequestBody SysDataScopeRule entity ){
        return new HttpResult<>(service.insert(entity));
    }

    /**
     * 修改实体信息
     * @param entity 实体信息
     * @return
     */
    @PutMapping(value = "/modify")
    public HttpResult<Boolean> modify( @RequestBody SysDataScopeRule entity ){
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
    public HttpResult<SysDataScopeRule> findById(@PathVariable Serializable id ){
        return new HttpResult<>(service.selectById(id));
    }

}