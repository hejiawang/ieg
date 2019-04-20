package com.wang.jmonkey.modules.sys.api;

import com.wang.jmonkey.common.http.abs.BaseHttp;
import com.wang.jmonkey.common.http.result.HttpResult;
import com.wang.jmonkey.modules.sys.model.entity.SysButton;
import com.wang.jmonkey.modules.sys.model.param.SysButtonParam;
import com.wang.jmonkey.modules.sys.service.ISysButtonService;

import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * @Description: 按钮权限表 api
 * @Auther: HeJiawang
 * @Date: 2018-12-11
 */
@RestController
@RequestMapping("/sys/button")
public class SysButtonApi extends BaseHttp {

    @Resource
    private ISysButtonService service;

    /**
     * 查询list信息
     * @param parentId 父资源id
     * @return
     */
    @GetMapping(value = "/list")
    public HttpResult<List<SysButton>> list(String parentId) {
        return new HttpResult<>( service.selectListByParent(parentId));
    }

    /**
     * 保存实体信息
     * @param param 实体信息
     * @return
     */
    @PostMapping(value = "/save")
    public HttpResult<Boolean> save( @RequestBody SysButtonParam param ){
        return new HttpResult<>(service.insert(param));
    }

    /**
     * 修改实体信息
     * @param entity 实体信息
     * @return
     */
    @PutMapping(value = "/modify")
    public HttpResult<Boolean> modify( @RequestBody SysButton entity ){
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
    public HttpResult<SysButton> findById(@PathVariable Serializable id ){
        return new HttpResult<>(service.selectById(id));
    }

}