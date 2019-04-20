package com.wang.jmonkey.modules.sys.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.wang.jmonkey.common.model.enums.YesOrNoEnum;
import com.wang.jmonkey.common.utils.UUIDUtil;
import com.wang.jmonkey.modules.sys.model.entity.SysTask;
import com.wang.jmonkey.modules.sys.mapper.SysTaskMapper;
import com.wang.jmonkey.modules.sys.service.ISysTaskService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 定时任务配置表 服务实现类
 * </p>
 *
 * @author HeJiawang
 * @since 2019-01-24
 */
@Slf4j
@Service
public class SysTaskServiceImpl extends ServiceImpl<SysTaskMapper, SysTask> implements ISysTaskService {

    /**
     * mapper
     */
    @Autowired
    private SysTaskMapper mapper;

    /**
     * scheduler
     */
    @Autowired
    private Scheduler scheduler;

    /**
     * 暂停任务
     * @param sysTask sysTask
     * @return Boolean
     */
    @Override
    public Boolean pause(SysTask sysTask) {
        JobKey key = new JobKey(sysTask.getName(), sysTask.getGroup());
        try {
            scheduler.pauseJob(key);
            sysTask.setStatus(YesOrNoEnum.No);

            return super.updateById(sysTask);
        } catch (SchedulerException e) {
            log.error(e.getMessage());

            return false;
        }
    }

    /**
     * 恢复任务
     * @param sysTask sysTask
     * @return Boolean
     */
    @Override
    public Boolean resume(SysTask sysTask) {
        JobKey key = new JobKey(sysTask.getName(), sysTask.getGroup());
        try {
            scheduler.resumeJob(key);
            sysTask.setStatus(YesOrNoEnum.Yes);

            return super.updateById(sysTask);
        } catch (SchedulerException e) {
            log.error(e.getMessage());

            return false;
        }
    }

    /**
     * 立即执行一次任务
     * @param sysTask sysTask
     * @return Boolean
     */
    @Override
    public Boolean startNow(SysTask sysTask) {
        JobKey key = new JobKey(sysTask.getName(), sysTask.getGroup());
        try {
            scheduler.triggerJob(key);

            return true;
        } catch (SchedulerException e) {
            log.error(e.getMessage());

            return false;
        }
    }

    /**
     * 删除任务
     * @param sysTask entity
     * @return Boolean
     */
    @Override
    public Boolean delete(SysTask sysTask) {
        JobKey key = new JobKey(sysTask.getName(), sysTask.getGroup());
        try {
            scheduler.deleteJob(key);

            return super.deleteById(sysTask.getId());
        } catch (SchedulerException e) {
            log.error(e.getMessage());

            return false;
        }
    }

    /**
     * 校验任务名是否重复
     * @param sysTask sysTask
     * @return Boolean true 已存在
     */
    @Override
    public Boolean checkName(SysTask sysTask) {
        return mapper.checkName(sysTask) > 0;
    }

    /**
     * 启动所有的状态为开启的任务
     * @return boolean
     */
    @Override
    public Boolean startAllTask() {
        List<SysTask> taskList = super.selectList(new EntityWrapper<>());
        taskList.forEach( task -> this.addJob(task) );

        return true;
    }

    /**
     * 修改任务
     * 删除任务后，重新建立
     * @param sysTask 任务信息
     * @return boolean
     */
    @Override
    public boolean updateById(SysTask sysTask) {
        this.delete(sysTask);
        sysTask.setId(UUIDUtil.value());

        return this.insert(sysTask);
    }

    /**
     * 新建任务
     * @param sysTask 任务信息
     * @return boolean
     */
    @Override
    public boolean insert(SysTask sysTask) {
        Boolean result = false;

        if (this.addJob(sysTask)) result = super.insert(sysTask);
        return result;
    }

    /**
     * 像任务管理器中添加任务
     * @param scheduleJob
     * @return
     */
    private boolean addJob(SysTask scheduleJob){
        Class job ;
        try {
            job = Class.forName(scheduleJob.getClassName());
        } catch (ClassNotFoundException e1) {
            log.error(e1.getMessage());

            return false;
        }

        JobDetail jobDetail = JobBuilder.newJob(job)
                .withIdentity(scheduleJob.getName(), scheduleJob.getGroup()).build();
        jobDetail.getJobDataMap().put("scheduleJob", scheduleJob);

        // 表达式调度构建器（可判断创建SimpleScheduleBuilder）
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(scheduleJob.getRule());

        // 按新的cronExpression表达式构建一个新的trigger
        CronTrigger trigger = TriggerBuilder.newTrigger()
                .withIdentity(scheduleJob.getName(), scheduleJob.getGroup())
                .withSchedule(scheduleBuilder).build();

        try {
            scheduler.scheduleJob(jobDetail, trigger);

            JobKey key = new JobKey(scheduleJob.getName(), scheduleJob.getGroup());
            if(scheduleJob.getStatus() == YesOrNoEnum.No) scheduler.pauseJob(key);
            else scheduler.resumeJob(key);
        } catch (SchedulerException e) {
            log.error(e.getMessage());

            return false;
        }

        return true;
    }
}
