package com.wang.jmonkey.modules.ieg.api;

import com.wang.jmonkey.common.http.abs.BaseHttp;
import com.wang.jmonkey.common.http.result.HttpResult;
import com.wang.jmonkey.modules.ieg.model.entity.IegSchoolMajorEnrollRecord;
import com.wang.jmonkey.modules.ieg.service.IIegSchoolMajorEnrollRecordService;

import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * @Description: 报考指南——学校专业历年录取信息 api
 * @Auther: HeJiawang
 * @Date: 2019-04-01
 */
@RestController
@RequestMapping("/ieg/school/major/enroll/record")
public class IegSchoolMajorEnrollRecordApi extends BaseHttp {

    @Resource
    private IIegSchoolMajorEnrollRecordService service;

    /**
     * 分页查询信息
     * @param entity 实体信息
     * @return
     */
    @GetMapping(value = "/list")
    public HttpResult<List<IegSchoolMajorEnrollRecord>> list(IegSchoolMajorEnrollRecord entity) {
        return new HttpResult<>( service.list(entity) );
    }

    /**
     * 保存实体信息
     * @param entity 实体信息
     * @return
     */
    @PostMapping(value = "/save")
    public HttpResult<Boolean> save( @RequestBody IegSchoolMajorEnrollRecord entity ){
        return new HttpResult<>(service.insert(entity));
    }

    /**
     * 修改实体信息
     * @param entity 实体信息
     * @return
     */
    @PutMapping(value = "/modify")
    public HttpResult<Boolean> modify( @RequestBody IegSchoolMajorEnrollRecord entity ){
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
    public HttpResult<IegSchoolMajorEnrollRecord> findById(@PathVariable Serializable id ){
        return new HttpResult<>(service.selectById(id));
    }

}