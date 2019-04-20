package com.wang.jmonkey.modules.ieg.api;

import com.baomidou.mybatisplus.plugins.Page;
import com.wang.jmonkey.common.http.abs.BaseHttp;
import com.wang.jmonkey.common.http.result.HttpPageResult;
import com.wang.jmonkey.common.http.result.HttpResult;
import com.wang.jmonkey.modules.ieg.model.entity.IegEnrollInfo;
import com.wang.jmonkey.modules.ieg.model.param.IegEnrollInfoParam;
import com.wang.jmonkey.modules.ieg.service.IIegEnrollInfoService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.Serializable;

/**
 * @Description: 报考指南——招生录取投档线信息 api
 * @Auther: HeJiawang
 * @Date: 2019-04-04
 */
@RestController
@RequestMapping("/ieg/enroll/info")
public class IegEnrollInfoApi extends BaseHttp {

    @Resource
    private IIegEnrollInfoService service;

    /**
     * Excel file path
     */
    @Value("${jmonkey.ieg.enroll}")
    private String filePath;

    /**
     * 分页查询信息
     * @param page page
     * @param entity 实体信息
     * @return
     */
    @GetMapping(value = "/list")
    public HttpPageResult<IegEnrollInfo> list(Page<IegEnrollInfo> page, IegEnrollInfo entity) {
        return new HttpPageResult<>( service.listPage( page, entity ) );
    }

    /**
     * 保存实体信息
     * @param entity 实体信息
     * @return
     */
    @PostMapping(value = "/save")
    public HttpResult<Boolean> save( @RequestBody IegEnrollInfo entity ){
        return new HttpResult<>(service.insert(entity));
    }

    /**
     * 修改实体信息
     * @param entity 实体信息
     * @return
     */
    @PutMapping(value = "/modify")
    public HttpResult<Boolean> modify( @RequestBody IegEnrollInfo entity ){
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
     * 清空投档分数线信息
     * @param enrollId enrollId
     * @return Boolean
     */
    @DeleteMapping(value = "/delByEnroll/{enrollId}")
    public HttpResult<Boolean> delByEnroll ( @PathVariable String enrollId ) {
        return new HttpResult<>(service.delByEnroll(enrollId));
    }

    /**
     * 查找实体信息
     * @param id 实体ID
     * @return
     */
    @GetMapping(value = "/find/{id}")
    public HttpResult<IegEnrollInfo> findById(@PathVariable Serializable id ){
        return new HttpResult<>(service.selectById(id));
    }

    /**
     * upload excel file
     * @param uploadFile uploadFile
     * @return String
     */
    @PostMapping("/file")
    public HttpResult<String> file(@RequestParam(value = "file") MultipartFile uploadFile ){
        return super.uploadFile(uploadFile, filePath);
    }

    /**
     * 导入投档分数线信息
     * @param param param
     * @return Boolean
     */
    @PostMapping(value = "/importInfo")
    public HttpResult<Boolean> importInfo( @RequestBody IegEnrollInfoParam param ){
        return new HttpResult<>(service.importInfo(param));
    }
}