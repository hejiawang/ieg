package com.wang.jmonkey.modules.sys.api;

import com.wang.jmonkey.common.http.abs.BaseHttp;
import com.wang.jmonkey.common.http.result.HttpResult;
import com.wang.jmonkey.modules.sys.model.dto.SysDeptTreeDto;
import com.wang.jmonkey.modules.sys.model.dto.SysDeptUserDto;
import com.wang.jmonkey.modules.sys.model.entity.SysDept;
import com.wang.jmonkey.modules.sys.service.ISysDeptService;

import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * @Description: 部门信息 api
 * @Auther: HeJiawang
 * @Date: 2018-12-20
 */
@RestController
@RequestMapping("/sys/dept")
public class SysDeptApi extends BaseHttp {

    @Resource
    private ISysDeptService service;

    /**
     * 获取树形数据
     * @return
     */
    @GetMapping(value = "/tree")
    public HttpResult<List<SysDeptTreeDto>> tree(){
        return new HttpResult<>(service.tree());
    }

    /**
     * 保存实体信息
     * @param entity 实体信息
     * @return
     */
    @PostMapping(value = "/save")
    public HttpResult<Boolean> save( @RequestBody SysDept entity ){
        return new HttpResult<>(service.insert(entity));
    }

    /**
     * 修改实体信息
     * @param entity 实体信息
     * @return
     */
    @PutMapping(value = "/modify")
    public HttpResult<Boolean> modify( @RequestBody SysDept entity ){
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
    public HttpResult<SysDept> findById(@PathVariable Serializable id ){
        return new HttpResult<>(service.selectById(id));
    }

    /**
     * 校验部门编码是否存在
     * @param sysDept
     * @return
     */
    @PostMapping(value = "/checkCode")
    public HttpResult<Boolean> checkCode( @RequestBody SysDept sysDept ){
        return new HttpResult<>(service.checkCode(sysDept));
    }

    /**
     * 部门中有哪些用户
     * @return SysDeptUserDto
     */
    @GetMapping(value = "/deptUserList")
    public HttpResult<List<SysDeptUserDto>> deptUserList(){
        return new HttpResult<>(service.deptUserList());
    }
}