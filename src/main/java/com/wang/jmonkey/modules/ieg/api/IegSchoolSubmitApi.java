package com.wang.jmonkey.modules.ieg.api;

import com.wang.jmonkey.common.http.abs.BaseHttp;
import com.wang.jmonkey.common.http.result.HttpResult;
import com.wang.jmonkey.modules.ieg.model.entity.IegSchoolSubmit;
import com.wang.jmonkey.modules.ieg.service.IIegSchoolSubmitService;

import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * @Description: 报考指南——投档单位信息 api
 * @Auther: HeJiawang
 * @Date: 2019-04-07
 */
@RestController
@RequestMapping("/ieg/school/submit")
public class IegSchoolSubmitApi extends BaseHttp {

    @Resource
    private IIegSchoolSubmitService service;

    /**
     * list data
     * @param submit submit
     * @return list
     */
    @GetMapping(value = "/list")
    public HttpResult<List<IegSchoolSubmit>> list(IegSchoolSubmit submit) {
        return new HttpResult<>(service.list(submit));
    }

    /**
     * 保存实体信息
     * @param entity 实体信息
     * @return
     */
    @PostMapping(value = "/save")
    public HttpResult<Boolean> save( @RequestBody IegSchoolSubmit entity ){
        return new HttpResult<>(service.insert(entity));
    }

    /**
     * 修改实体信息
     * @param entity 实体信息
     * @return
     */
    @PutMapping(value = "/modify")
    public HttpResult<Boolean> modify( @RequestBody IegSchoolSubmit entity ){
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
    public HttpResult<IegSchoolSubmit> findById(@PathVariable Serializable id ){
        return new HttpResult<>(service.selectById(id));
    }

}