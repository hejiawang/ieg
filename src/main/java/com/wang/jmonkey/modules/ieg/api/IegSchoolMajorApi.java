package com.wang.jmonkey.modules.ieg.api;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.wang.jmonkey.common.http.abs.BaseHttp;
import com.wang.jmonkey.common.http.result.HttpPageResult;
import com.wang.jmonkey.common.http.result.HttpResult;
import com.wang.jmonkey.modules.ieg.model.dto.IegSchoolMajorDto;
import com.wang.jmonkey.modules.ieg.model.entity.IegMajor;
import com.wang.jmonkey.modules.ieg.model.entity.IegSchoolMajor;
import com.wang.jmonkey.modules.ieg.model.param.IegSchoolMajorParam;
import com.wang.jmonkey.modules.ieg.service.IIegSchoolMajorService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.Serializable;
import java.util.List;

/**
 * @Description: 报考指南——学校历年录取信息 api
 * @Auther: HeJiawang
 * @Date: 2019-03-31
 */
@RestController
@RequestMapping("/ieg/school/major")
public class IegSchoolMajorApi extends BaseHttp {

    @Resource
    private IIegSchoolMajorService service;

    /**
     * 信息图片
     */
    @Value("${jmonkey.ieg.school.major}")
    private String filePath;

    /**
     * 院校专业list
     * @param param param
     * @return IegSchoolMajorDto
     */
    @GetMapping(value = "/list")
    public HttpResult<List<IegSchoolMajorDto>> list(IegSchoolMajorParam param ) {
        return new HttpResult<>(service.list(param));
    }

    /**
     * 保存实体信息
     * @param param 实体信息
     * @return
     */
    @PostMapping(value = "/save")
    public HttpResult<Boolean> save( @RequestBody IegSchoolMajorParam param ){
        return new HttpResult<>(service.save(param));
    }

    /**
     * 修改实体信息
     * @param param 实体信息
     * @return
     */
    @PutMapping(value = "/modify")
    public HttpResult<Boolean> modify( @RequestBody IegSchoolMajorParam param ){
        return new HttpResult<>(service.modify(param));
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
    public HttpResult<IegSchoolMajor> findById(@PathVariable Serializable id ){
        return new HttpResult<>(service.selectById(id));
    }

    /**
     * 信息图片
     * @param uploadFile uploadFile
     * @param schoolId schoolId
     * @return String
     */
    @PostMapping("/file")
    public HttpResult<String> file(@RequestParam(value = "file") MultipartFile uploadFile, String schoolId ){
        return super.uploadFile(uploadFile, filePath + schoolId + File.separator, true);
    }

}