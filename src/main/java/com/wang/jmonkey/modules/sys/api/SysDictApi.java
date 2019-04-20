package com.wang.jmonkey.modules.sys.api;

import com.wang.jmonkey.common.http.abs.BaseHttp;
import com.wang.jmonkey.common.http.result.HttpResult;
import com.wang.jmonkey.modules.sys.model.dto.SysDictDto;
import com.wang.jmonkey.modules.sys.model.dto.SysDictTreeDto;
import com.wang.jmonkey.modules.sys.model.entity.SysDict;
import com.wang.jmonkey.modules.sys.service.ISysDictService;

import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * @Description: 字典值 api
 * @Auther: HeJiawang
 * @Date: 2018-12-17
 */
@RestController
@RequestMapping("/sys/dict")
public class SysDictApi extends BaseHttp {

    @Resource
    private ISysDictService service;

    /**
     * 获取树形表格数据
     * @return
     */
    @GetMapping(value = "/tree")
    public HttpResult<List<SysDictTreeDto>> tree(){
        return new HttpResult<>(service.tree());
    }

    /**
     * 保存实体信息
     * @param entity 实体信息
     * @return
     */
    @PostMapping(value = "/save")
    public HttpResult<Boolean> save( @RequestBody SysDict entity ){
        return new HttpResult<>(service.insert(entity));
    }

    /**
     * 修改实体信息
     * @param entity 实体信息
     * @return
     */
    @PutMapping(value = "/modify")
    public HttpResult<Boolean> modify( @RequestBody SysDict entity ){
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
    public HttpResult<SysDictDto> findById(@PathVariable Serializable id ){
        return new HttpResult<>(service.selectDtoById(id));
    }

    /**
     * 校验字典键值是否存在
     * @param sysDict
     * @return
     */
    @PostMapping(value = "/checkValue")
    public HttpResult<Boolean> checkValue( @RequestBody SysDict sysDict ){
        return new HttpResult<>(service.checkValue(sysDict));
    }

    /**
     * 为字典组件赋值
     * 根据父value获取子字典信息
     * @param parentValue 父字典value
     * @return
     */
    @GetMapping(value = "/findChildren/{parentValue}")
    public HttpResult<List<SysDict>> findChildren( @PathVariable String parentValue ){
        return new HttpResult<>(service.findChildren(parentValue));
    }
}