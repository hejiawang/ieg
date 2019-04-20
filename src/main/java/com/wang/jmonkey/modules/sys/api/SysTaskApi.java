package com.wang.jmonkey.modules.sys.api;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.wang.jmonkey.common.http.abs.BaseHttp;
import com.wang.jmonkey.common.http.result.HttpPageResult;
import com.wang.jmonkey.common.http.result.HttpResult;
import com.wang.jmonkey.modules.sys.model.entity.SysTask;
import com.wang.jmonkey.modules.sys.service.ISysTaskService;

import org.quartz.CronExpression;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;

/**
 * @Description: 定时任务配置表 api
 * @Auther: HeJiawang
 * @Date: 2019-01-24
 */
@RestController
@RequestMapping("/sys/task")
public class SysTaskApi extends BaseHttp {

    @Resource
    private ISysTaskService service;

    /**
     * 分页查询信息
     * @param page page
     * @param entity 实体信息
     * @return SysTask
     */
    @GetMapping(value = "/list")
    public HttpPageResult<SysTask> list(Page<SysTask> page, SysTask entity) {
        EntityWrapper wrapper = new EntityWrapper<SysTask>();
        return new HttpPageResult<>( service.selectPage( page, wrapper ) );
    }

    /**
     * 保存实体信息
     * @param entity 实体信息
     * @return Boolean
     */
    @PostMapping(value = "/save")
    public HttpResult<Boolean> save( @RequestBody SysTask entity ){
        return new HttpResult<>(service.insert(entity));
    }

    /**
     * 修改实体信息
     * @param entity 实体信息
     * @return Boolean
     */
    @PutMapping(value = "/modify")
    public HttpResult<Boolean> modify( @RequestBody SysTask entity ){
        return new HttpResult<>(service.updateById(entity));
    }

    /**
     * 删除实体信息
     * @param entity entity
     * @return Boolean
     */
    @PostMapping(value = "/delete")
    public HttpResult<Boolean> delete( @RequestBody SysTask entity ){
        return new HttpResult<>(service.delete(entity));
    }

    /**
     * 查找实体信息
     * @param id 实体ID
     * @return SysTask
     */
    @GetMapping(value = "/find/{id}")
    public HttpResult<SysTask> findById(@PathVariable Serializable id ){
        return new HttpResult<>(service.selectById(id));
    }

    /**
     * 校验任务名称是否存在
     * @param className class name
     * @return Boolean true 存在任务，通过； false 不通过
     */
    @GetMapping(value = "/checkTask")
    public HttpResult<Boolean> checkTask(String className) {
        try {
            Class.forName(className);

            return new HttpResult<>(true);
        } catch (ClassNotFoundException e1) {
            return new HttpResult<>(false);
        }
    }

    /**
     * 验证定时任务规则表达式
     * @param rule rule
     * @return Boolean true 通过； false不通过
     */
    @GetMapping(value = "/checkRule")
    public HttpResult<Boolean> checkRule(String rule){
        return new HttpResult<>(CronExpression.isValidExpression(rule));
    }

    /**
     * 校验任务名是否重复
     * @param sysTask sysTask
     * @return Boolean true 已存在
     */
    @PostMapping(value = "/checkName")
    public HttpResult<Boolean> checkName(@RequestBody SysTask sysTask){
        return new HttpResult<>(service.checkName(sysTask));
    }

    /**
     * 暂停任务
     * @param sysTask sysTask
     * @return Boolean
     */
    @PostMapping(value = "/pause")
    public HttpResult<Boolean> pause(@RequestBody SysTask sysTask){
        return new HttpResult<>(service.pause(sysTask));
    }

    /**
     * 恢复任务
     * @param sysTask sysTask
     * @return Boolean
     */
    @PostMapping(value = "/resume")
    public HttpResult<Boolean> resume(@RequestBody SysTask sysTask){
        return new HttpResult<>(service.resume(sysTask));
    }

    /**
     * 立即执行一次任务
     * @param sysTask sysTask
     * @return Boolean
     */
    @PostMapping(value = "/startNow")
    public HttpResult<Boolean> startNow(@RequestBody SysTask sysTask){
        return new HttpResult<>(service.startNow(sysTask));
    }
}