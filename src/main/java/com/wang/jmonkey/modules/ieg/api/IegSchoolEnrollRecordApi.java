package com.wang.jmonkey.modules.ieg.api;

import com.wang.jmonkey.common.http.abs.BaseHttp;
import com.wang.jmonkey.common.http.result.HttpResult;
import com.wang.jmonkey.modules.ieg.model.entity.IegSchoolEnrollRecord;
import com.wang.jmonkey.modules.ieg.service.IIegSchoolEnrollRecordService;

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
 * @Date: 2019-03-29
 */
@RestController
@RequestMapping("/ieg/school/enroll/record")
public class IegSchoolEnrollRecordApi extends BaseHttp {

    /**
     * service
     */
    @Resource
    private IIegSchoolEnrollRecordService service;

    /**
     * 院系信息图片
     */
    @Value("${jmonkey.ieg.school.enroll}")
    private String filePath;

    /**
     * 获取院校历年录取信息
     * @param enrollRecord 录取信息
     * @return IegSchoolEnrollRecord list
     */
    @GetMapping(value = "/list")
    public HttpResult<List<IegSchoolEnrollRecord>> list(IegSchoolEnrollRecord enrollRecord) {
        return new HttpResult<>(service.selectBySchool(enrollRecord));
    }

    /**
     * 保存实体信息
     * @param entity 实体信息
     * @return
     */
    @PostMapping(value = "/save")
    public HttpResult<Boolean> save( @RequestBody IegSchoolEnrollRecord entity ){
        return new HttpResult<>(service.insert(entity));
    }

    /**
     * 修改实体信息
     * @param entity 实体信息
     * @return
     */
    @PutMapping(value = "/modify")
    public HttpResult<Boolean> modify( @RequestBody IegSchoolEnrollRecord entity ){
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
    public HttpResult<IegSchoolEnrollRecord> findById(@PathVariable Serializable id ){
        return new HttpResult<>(service.selectById(id));
    }

    /**
     * 信息图片
     * @param uploadFile uploadFile
     * @param schoolId schoolId
     * @return String
     */
    @PostMapping("/file")
    public HttpResult<String> file(@RequestParam(value = "file") MultipartFile uploadFile, String schoolId, String content ){
        return super.uploadFile(uploadFile, filePath + schoolId + File.separator + content + File.separator);
    }
}