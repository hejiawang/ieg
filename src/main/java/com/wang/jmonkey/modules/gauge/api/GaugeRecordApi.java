package com.wang.jmonkey.modules.gauge.api;

import com.wang.jmonkey.common.http.abs.BaseHttp;
import com.wang.jmonkey.common.http.result.HttpResult;
import com.wang.jmonkey.modules.gauge.model.dto.GaugeRecordDto;
import com.wang.jmonkey.modules.gauge.model.entity.GaugeRecord;
import com.wang.jmonkey.modules.gauge.service.IGaugeRecordService;

import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * @Description: 量表————服刑人员测评记录 api
 * @Auther: HeJiawang
 * @Date: 2019-09-26
 */
@RestController
@RequestMapping("/gauge/record")
public class GaugeRecordApi extends BaseHttp {

    @Resource
    private IGaugeRecordService service;

    /**
     * list信息
     * @param record userId
     * @return List<GaugeRecordDto>
     */
    @GetMapping(value = "/list")
    public HttpResult<List<GaugeRecordDto>> list(GaugeRecord record ) {
        return new HttpResult<>( service.selectListByUserId( record.getUserId() ) );
    }

    /**
     * 保存实体信息
     * @param entity 实体信息
     * @return Boolean
     */
    @PostMapping(value = "/save")
    public HttpResult<Boolean> save( @RequestBody GaugeRecord entity ){
        return new HttpResult<>(service.insert(entity));
    }

    /**
     * 修改实体信息
     * @param entity 实体信息
     * @return Boolean
     */
    @PutMapping(value = "/modify")
    public HttpResult<Boolean> modify( @RequestBody GaugeRecord entity ){
        return new HttpResult<>(service.updateById(entity));
    }

    /**
     * 删除实体信息
     * @param id 实体ID
     * @return Boolean
     */
    @DeleteMapping(value = "/delete/{id}")
    public HttpResult<Boolean> delete( @PathVariable Serializable id ){
        return new HttpResult<>(service.deleteById(id));
    }

    /**
     * 查找实体信息
     * @param id 实体ID
     * @return HttpResult
     */
    @GetMapping(value = "/find/{id}")
    public HttpResult<GaugeRecord> findById(@PathVariable Serializable id ){
        return new HttpResult<>(service.selectById(id));
    }

}
