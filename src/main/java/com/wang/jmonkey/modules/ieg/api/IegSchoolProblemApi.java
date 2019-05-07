package com.wang.jmonkey.modules.ieg.api;

import com.wang.jmonkey.common.http.abs.BaseHttp;
import com.wang.jmonkey.common.http.result.HttpResult;
import com.wang.jmonkey.modules.ieg.model.entity.IegSchoolProblem;
import com.wang.jmonkey.modules.ieg.service.IIegSchoolProblemService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.Serializable;
import java.util.List;

/**
 * @Description: 报考指南——考生对学校的常见问题以及回答 api
 * @Auther: HeJiawang
 * @Date: 2019-03-29
 */
@RestController
@RequestMapping("/ieg/school/problem")
public class IegSchoolProblemApi extends BaseHttp {

    @Resource
    private IIegSchoolProblemService service;

    /**
     * 院系信息图片
     */
    @Value("${jmonkey.ieg.school.problem}")
    private String filePath;

    /**
     * 查询信息
     * @param problem problem
     * @return List<IegSchoolProblem>
     */
    @GetMapping(value = "/list")
    public HttpResult<List<IegSchoolProblem>> list(IegSchoolProblem problem) {
        return new HttpResult<>( service.selectBySchool(problem) );
    }

    /**
     * 保存实体信息
     * @param entity 实体信息
     * @return
     */
    @PostMapping(value = "/save")
    public HttpResult<Boolean> save( @RequestBody IegSchoolProblem entity ){
        return new HttpResult<>(service.insert(entity));
    }

    /**
     * 修改实体信息
     * @param entity 实体信息
     * @return
     */
    @PutMapping(value = "/modify")
    public HttpResult<Boolean> modify( @RequestBody IegSchoolProblem entity ){
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
    public HttpResult<IegSchoolProblem> findById(@PathVariable Serializable id ){
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