package com.wang.jmonkey.modules.ieg.api;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.wang.jmonkey.common.http.abs.BaseHttp;
import com.wang.jmonkey.common.http.result.HttpPageResult;
import com.wang.jmonkey.common.http.result.HttpResult;
import com.wang.jmonkey.modules.ieg.model.dto.IegEnvironmentAreaTreeDto;
import com.wang.jmonkey.modules.ieg.model.entity.IegEnvironment;
import com.wang.jmonkey.modules.ieg.service.IIegEnvironmentService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * @Description: 报考指南——当地气候饮食情况 api
 * @Auther: HeJiawang
 * @Date: 2019-04-03
 */
@RestController
@RequestMapping("/ieg/environment")
public class IegEnvironmentApi extends BaseHttp {

    @Resource
    private IIegEnvironmentService service;

    /**
     * 信息图片
     */
    @Value("${jmonkey.ieg.school.environment}")
    private String filePath;

    /**
     * 分页查询信息
     * @param page page
     * @param entity 实体信息
     * @return
     */
    @GetMapping(value = "/list")
    public HttpPageResult<IegEnvironment> list(Page<IegEnvironment> page, IegEnvironment entity) {
        EntityWrapper wrapper = new EntityWrapper<IegEnvironment>();

        return new HttpPageResult<>( service.selectPage( page, wrapper ) );
    }

    /**
     * 获取地区树形数据
     * @return 地区树形数据
     */
    @GetMapping(value = "/tree")
    public HttpResult<List<IegEnvironmentAreaTreeDto>> tree() {
        return new HttpResult<>( service.areaTree() );
    }

    /**
     * 保存实体信息
     * @param entity 实体信息
     * @return
     */
    @PostMapping(value = "/save")
    public HttpResult<Boolean> save( @RequestBody IegEnvironment entity ){
        return new HttpResult<>(service.insert(entity));
    }

    /**
     * 修改实体信息
     * @param entity 实体信息
     * @return
     */
    @PutMapping(value = "/modify")
    public HttpResult<Boolean> modify( @RequestBody IegEnvironment entity ){
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
    public HttpResult<IegEnvironment> findById(@PathVariable Serializable id ){
        return new HttpResult<>(service.selectById(id));
    }

    /**
     * 查找实体信息
     * @param areaCity areaCity
     * @return IegEnvironment
     */
    @GetMapping(value = "/findByAreaCity/{areaCity}")
    public HttpResult<IegEnvironment> findByAreaCity(@PathVariable String areaCity) {
        return new HttpResult<>(service.findByAreaCity(areaCity));
    }

    /**
     * checkExist
     * @param entity entity
     * @return Boolean
     */
    @PostMapping(value = "/checkExist")
    public HttpResult<Boolean> checkExist(@RequestBody IegEnvironment entity ) {
        return new HttpResult<>(service.checkExist(entity));
    }

    /**
     * 图片信息
     * @param uploadFile uploadFile
     * @return String
     */
    @PostMapping("/file")
    public HttpResult<String> file(@RequestParam(value = "file") MultipartFile uploadFile ){
        return super.uploadFile(uploadFile, filePath, true);
    }
}