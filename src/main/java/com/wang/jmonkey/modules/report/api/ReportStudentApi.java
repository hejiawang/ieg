package com.wang.jmonkey.modules.report.api;

import com.baomidou.mybatisplus.plugins.Page;
import com.wang.jmonkey.common.http.abs.BaseHttp;
import com.wang.jmonkey.common.http.result.HttpPageResult;
import com.wang.jmonkey.common.http.result.HttpResult;
import com.wang.jmonkey.common.model.vo.UserVo;
import com.wang.jmonkey.modules.report.model.dto.ReportStudentDto;
import com.wang.jmonkey.modules.report.model.entity.ReportStudent;
import com.wang.jmonkey.modules.report.model.param.ReportStudentParam;
import com.wang.jmonkey.modules.report.service.IReportStudentService;

import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;

/**
 * @Description: 报告————学生基本信息 api
 * @Auther: HeJiawang
 * @Date: 2019-08-03
 */
@RestController
@RequestMapping("/report/student")
public class ReportStudentApi extends BaseHttp {

    @Resource
    private IReportStudentService service;

    /**
     * 分页查询信息
     * @param page page
     * @param param 实体信息
     * @return Page
     */
    @GetMapping(value = "/listAll")
    public HttpPageResult<ReportStudentDto> listAll(Page<ReportStudentDto> page,
                                                    ReportStudentParam param) {
        return new HttpPageResult<>( service.listAll( page, param ) );
    }

    /**
     * 保存实体信息
     * @param param 实体信息
     * @return Boolean
     */
    @PostMapping(value = "/save")
    public HttpResult<Boolean> save(@RequestBody ReportStudentParam param, UserVo userVo){
        param.setUserId(userVo.getId());

        return new HttpResult<>(service.save(param));
    }

    /**
     * 修改实体信息
     * @param param 实体信息
     * @return Boolean
     */
    @PutMapping(value = "/modify")
    public HttpResult<Boolean> modify( @RequestBody ReportStudentParam param ){
        return new HttpResult<>(service.modify(param));
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
     * @return ReportStudent
     */
    @GetMapping(value = "/find/{id}")
    public HttpResult<ReportStudentDto> findById(@PathVariable String id ){
        return new HttpResult<>(service.selectDtoById(id));
    }

}