package com.wang.jmonkey.modules.ieg.api;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.wang.jmonkey.common.http.abs.BaseHttp;
import com.wang.jmonkey.common.http.result.HttpPageResult;
import com.wang.jmonkey.common.http.result.HttpResult;
import com.wang.jmonkey.modules.ieg.model.dto.IegSchoolDto;
import com.wang.jmonkey.modules.ieg.model.dto.IegSchoolPageDto;
import com.wang.jmonkey.modules.ieg.model.entity.IegSchool;
import com.wang.jmonkey.modules.ieg.model.param.IegSchoolParam;
import com.wang.jmonkey.modules.ieg.model.param.IegSchoolSearchParam;
import com.wang.jmonkey.modules.ieg.service.IIegSchoolService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * @Description: 报考指南——学校基本信息 api
 * @Auther: HeJiawang
 * @Date: 2019-03-26
 */
@RestController
@RequestMapping("/ieg/school")
public class IegSchoolApi extends BaseHttp {

    @Resource
    private IIegSchoolService service;

    /**
     * 学校logo保存路径
     */
    @Value("${jmonkey.ieg.school.img.logo}")
    private String logoImage;

    /**
     * 分页查询信息
     * @param page page
     * @param param 查询参数
     * @return
     */
    @GetMapping(value = "/list")
    public HttpPageResult<IegSchoolPageDto> list(Page<IegSchoolPageDto> page, IegSchoolSearchParam param) {
        return new HttpPageResult<>( service.pageList( page, param ) );
    }

    /**
     * listAll
     * @param param param
     * @return List
     */
    @GetMapping(value = "/listAll")
    public HttpResult<List<IegSchool>> listAll (IegSchoolSearchParam param) {
        return new HttpResult<>( service.listAll( param ) );
    }

    /**
     * 保存实体信息
     * @param schoolParam 院校信息
     * @return Boolean
     */
    @PostMapping(value = "/save")
    public HttpResult<Boolean> save( @RequestBody IegSchoolParam schoolParam ){
        return new HttpResult<>(service.save(schoolParam));
    }

    /**
     * 修改实体信息
     * @param schoolParam 实体信息
     * @return Boolean
     */
    @PutMapping(value = "/modify")
    public HttpResult<Boolean> modify( @RequestBody IegSchoolParam schoolParam ){
        return new HttpResult<>(service.modify(schoolParam));
    }

    /**
     * 删除实体信息
     * @param id 实体ID
     * @return
     */
    @DeleteMapping(value = "/delete/{id}")
    public HttpResult<Boolean> delete( @PathVariable Serializable id ){
        // TODO 紧删除了院校的基本信息, 关联信息并没有删除
        return new HttpResult<>(service.deleteById(id));
    }

    /**
     * 查找实体信息
     * @param id 实体ID
     * @return IegSchoolDto
     */
    @GetMapping(value = "/findDto/{id}")
    public HttpResult<IegSchoolDto> findById(@PathVariable Serializable id ){
        return new HttpResult<>(service.findDtoById(id));
    }

    /**
     * 上传logo图片
     * @param uploadFile uploadFile
     * @return Boolean
     */
    @PostMapping("/logo")
    public HttpResult<String> logo(@RequestParam(value = "file") MultipartFile uploadFile ){
        return super.uploadFile(uploadFile, logoImage, false);
    }

    /**
     * 上传院校详细图片
     * @param uploadFile
     * @return
     */
    @PostMapping("/imgInfo")
    public HttpResult<String> imgInfo(@RequestParam(value = "file") MultipartFile uploadFile) {
        return super.uploadFile(uploadFile, logoImage, true);
    }

}