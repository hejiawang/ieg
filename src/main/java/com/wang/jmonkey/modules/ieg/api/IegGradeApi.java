package com.wang.jmonkey.modules.ieg.api;

import com.baomidou.mybatisplus.plugins.Page;
import com.wang.jmonkey.common.http.abs.BaseHttp;
import com.wang.jmonkey.common.http.result.HttpPageResult;
import com.wang.jmonkey.common.http.result.HttpResult;
import com.wang.jmonkey.modules.ieg.model.entity.IegGrade;
import com.wang.jmonkey.modules.ieg.model.param.IegGradeParam;
import com.wang.jmonkey.modules.ieg.service.IIegGradeService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.Serializable;

/**
 * @Description: 报考指南——一分一段表 api
 * @Auther: HeJiawang
 * @Date: 2019-04-02
 */
@RestController
@RequestMapping("/ieg/grade")
public class IegGradeApi extends BaseHttp {

    /**
     * service
     */
    @Resource
    private IIegGradeService service;

    /**
     * 信息图片
     */
    @Value("${jmonkey.ieg.grade}")
    private String filePath;

    /**
     * 分页查询信息
     * @param page page
     * @param entity 实体信息
     * @return
     */
    @GetMapping(value = "/list")
    public HttpPageResult<IegGrade> list(Page<IegGrade> page, IegGrade entity) {
        return new HttpPageResult<>( service.list( page, entity ) );
    }

    /**
     * 保存实体信息
     * @param entity 实体信息
     * @return
     */
    @PostMapping(value = "/save")
    public HttpResult<Boolean> save( @RequestBody IegGrade entity ){
        return new HttpResult<>(service.insert(entity));
    }

    /**
     * 修改实体信息
     * @param entity 实体信息
     * @return
     */
    @PutMapping(value = "/modify")
    public HttpResult<Boolean> modify( @RequestBody IegGrade entity ){
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
     * 批量删除
     * @param entity 删除条件
     * @return true
     */
    @GetMapping(value = "/delByYearAndType")
    public HttpResult<Boolean> delByYearAndType (IegGrade entity) {
        return new HttpResult<>(service.delByYearAndType(entity));
    }

    /**
     * 查找实体信息
     * @param id 实体ID
     * @return
     */
    @GetMapping(value = "/find/{id}")
    public HttpResult<IegGrade> findById(@PathVariable Serializable id ){
        return new HttpResult<>(service.selectById(id));
    }

    /**
     * 消息
     * @param uploadFile uploadFile
     * @return String
     */
    @PostMapping("/file")
    public HttpResult<String> file(@RequestParam(value = "file") MultipartFile uploadFile ){
        return super.uploadFile(uploadFile, filePath, true);
    }

    /**
     * 批量导入
     * @param param param
     * @return Boolean
     */
    @PostMapping(value = "/importGrade")
    public HttpResult<Boolean> importGrade(@RequestBody IegGradeParam param) {
        return new HttpResult<>(service.importGrade(param));
    }

    /**
     * 批量校验
     * @param param param
     * @return Boolean
     */
    @PostMapping(value = "/checkGrade")
    public HttpResult<Boolean> checkGrade(@RequestBody IegGradeParam param) {
        return service.checkGrade(param);
    }

    /**
     * 校验是否存在
     * @param param param
     * @return true 已存在
     */
    @PostMapping(value = "/checkExist")
    public HttpResult<Boolean> checkExist( @RequestBody IegGradeParam param){
        return new HttpResult<>(service.checkExist(param));
    }

}