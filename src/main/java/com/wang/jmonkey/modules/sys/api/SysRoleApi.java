package com.wang.jmonkey.modules.sys.api;

import com.baomidou.mybatisplus.plugins.Page;
import com.wang.jmonkey.common.http.abs.BaseHttp;
import com.wang.jmonkey.common.http.result.HttpPageResult;
import com.wang.jmonkey.common.http.result.HttpResult;
import com.wang.jmonkey.modules.sys.model.entity.SysRole;
import com.wang.jmonkey.modules.sys.service.ISysRoleService;

import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * @Description:  角色信息 api
 * @Auther: HeJiawang
 * @Date: 2018-12-10
 */
@RestController
@RequestMapping("/sys/role")
public class SysRoleApi extends BaseHttp {

    @Resource
    private ISysRoleService service;

    /**
     * 获取所有角色信息
     * @return
     */
    @GetMapping(value = "/listAll")
    public HttpResult<List<SysRole>> listAll(){
        return new HttpResult<>( service.listAll() );
    }

    /**
     * 分页查询信息
     * @param page page
     * @param entity 实体信息
     * @return
     */
    @GetMapping(value = "/list")
    public HttpPageResult<SysRole> list(Page<SysRole> page, SysRole entity) {
        return new HttpPageResult<>( service.selectPage( page, entity ) );
    }

    /**
     * 保存实体信息
     * @param entity 实体信息
     * @return
     */
    @PostMapping(value = "/save")
    public HttpResult<Boolean> save( @RequestBody SysRole entity ){
        return new HttpResult<>(service.insert(entity));
    }

    /**
     * 修改实体信息
     * @param entity 实体信息
     * @return
     */
    @PutMapping(value = "/modify")
    public HttpResult<Boolean> modify( @RequestBody SysRole entity ){
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
    public HttpResult<SysRole> findById(@PathVariable Serializable id ){
        return new HttpResult<>(service.selectById(id));
    }

    /**
     * 校验角色code是否存在
     * @param sysRole 角色信息
     * @return true 存在
     */
    @PostMapping(value = "/checkCode")
    public HttpResult<Boolean> checkCode( @RequestBody SysRole sysRole ){
        return new HttpResult<>(service.checkCode(sysRole));
    }

    /**
     * 校验角色名称是否存在
     * @param sysRole 角色信息
     * @return true 存在
     */
    @PostMapping(value = "/checkName")
    public HttpResult<Boolean> checkName( @RequestBody SysRole sysRole ){
        return new HttpResult<>(service.checkName(sysRole));
    }

}