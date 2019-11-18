package com.wang.jmonkey.modules.gauge.api;

import com.wang.jmonkey.common.http.abs.BaseHttp;
import com.wang.jmonkey.common.http.result.HttpResult;
import com.wang.jmonkey.modules.gauge.model.entity.GaugeInfo;
import com.wang.jmonkey.modules.gauge.model.param.GaugeAnswerParam;
import com.wang.jmonkey.modules.gauge.service.IGaugeInfoService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * @Description: 量表————基本信息 api
 * @Auther: HeJiawang
 * @Date: 2019-09-25
 */
@RestController
@RequestMapping("/gauge/info")
public class GaugeInfoApi extends BaseHttp {

    @Resource
    private IGaugeInfoService service;

    /**
     * imgPath
     */
    @Value("${jmonkey.gauge.icon-path}")
    private String imgPath;

    /**
     * list查询信息
     * @return page
     */
    @GetMapping(value = "/list")
    public HttpResult<List<GaugeInfo>> list() {
        return new HttpResult<>( service.selectList() );
    }

    /**
     * 修改实体信息
     * @param entity 实体信息
     * @return Boolean
     */
    @PutMapping(value = "/modify")
    public HttpResult<Boolean> modify( @RequestBody GaugeInfo entity ){
        return new HttpResult<>(service.updateById(entity));
    }

    /**
     * 查找实体信息
     * @param id 实体ID
     * @return HttpResult
     */
    @GetMapping(value = "/find/{id}")
    public HttpResult<GaugeInfo> findById(@PathVariable Serializable id ){
        return new HttpResult<>(service.selectById(id));
    }

    /**
     * uploadImg
     * @param uploadFile file
     * @return imgPath
     */
    @PostMapping("/uploadIcon")
    public HttpResult<String> uploadImg(@RequestParam(value = "file") MultipartFile uploadFile ){
        return super.uploadFile(uploadFile, imgPath, false);
    }

    /**
     * 量表测评
     * @param param param
     * @return Boolean
     */
    @PostMapping("/handle")
    public HttpResult<Boolean> handle(@RequestBody GaugeAnswerParam param) {
        return new HttpResult<>(service.handle(param));
    }

}
