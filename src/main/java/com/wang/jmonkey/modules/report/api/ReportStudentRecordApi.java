package com.wang.jmonkey.modules.report.api;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.wang.jmonkey.common.http.abs.BaseHttp;
import com.wang.jmonkey.common.http.result.HttpPageResult;
import com.wang.jmonkey.common.http.result.HttpResult;
import com.wang.jmonkey.modules.report.model.entity.ReportStudentRecord;
import com.wang.jmonkey.modules.report.service.IReportStudentRecordService;

import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * @Description: 报告————学生来访记录 api
 * @Auther: HeJiawang
 * @Date: 2019-08-03
 */
@RestController
@RequestMapping("/report/student/record")
public class ReportStudentRecordApi extends BaseHttp {

    @Resource
    private IReportStudentRecordService service;

    /**
     * 查询信息
     * @param entity 实体信息
     * @return HttpResult
     */
    @GetMapping(value = "/list")
    public HttpResult<List<ReportStudentRecord>> list(ReportStudentRecord entity) {
        return new HttpResult<>( service.list( entity ) );
    }

    /**
     * 保存实体信息
     * @param entity 实体信息
     * @return
     */
    @PostMapping(value = "/save")
    public HttpResult<Boolean> save( @RequestBody ReportStudentRecord entity ){
        return new HttpResult<>(service.insert(entity));
    }

    /**
     * 修改实体信息
     * @param entity 实体信息
     * @return
     */
    @PutMapping(value = "/modify")
    public HttpResult<Boolean> modify( @RequestBody ReportStudentRecord entity ){
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
    public HttpResult<ReportStudentRecord> findById(@PathVariable Serializable id ){
        return new HttpResult<>(service.selectById(id));
    }

}