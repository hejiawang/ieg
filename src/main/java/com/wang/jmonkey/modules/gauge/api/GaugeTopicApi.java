package com.wang.jmonkey.modules.gauge.api;

import com.wang.jmonkey.common.http.abs.BaseHttp;
import com.wang.jmonkey.common.http.result.HttpResult;
import com.wang.jmonkey.modules.gauge.model.dto.GaugeTopicAnswerDto;
import com.wang.jmonkey.modules.gauge.model.entity.GaugeTopic;
import com.wang.jmonkey.modules.gauge.service.IGaugeTopicService;

import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * @Description: 量表————题目 api
 * @Auther: HeJiawang
 * @Date: 2019-09-25
 */
@RestController
@RequestMapping("/gauge/topic")
public class GaugeTopicApi extends BaseHttp {

    @Resource
    private IGaugeTopicService service;

    /**
     * 获取量表的详细信息（题目。答案）
     * @param gaugeId 量表id
     * @return 量表信息
     */
    @GetMapping(value = "/topic/{gaugeId}")
    public HttpResult<List<GaugeTopicAnswerDto>> topicList(@PathVariable String gaugeId) {
        return new HttpResult<>( service.topicList(gaugeId) );
    }

    /**
     * 保存实体信息
     * @param entity 实体信息
     * @return Boolean
     */
    @PostMapping(value = "/save")
    public HttpResult<Boolean> save( @RequestBody GaugeTopic entity ){
        return new HttpResult<>(service.insert(entity));
    }

    /**
     * 修改实体信息
     * @param entity 实体信息
     * @return Boolean
     */
    @PutMapping(value = "/modify")
    public HttpResult<Boolean> modify( @RequestBody GaugeTopic entity ){
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
    public HttpResult<GaugeTopic> findById(@PathVariable Serializable id ){
        return new HttpResult<>(service.selectById(id));
    }

}
