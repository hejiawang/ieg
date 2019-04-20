package com.wang.jmonkey.modules.sys.service;

import com.wang.jmonkey.modules.sys.model.entity.SysTask;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 定时任务配置表 服务类
 * </p>
 *
 * @author HeJiawang
 * @since 2019-01-24
 */
public interface ISysTaskService extends IService<SysTask> {

    /**
     * 暂停任务
     * @param sysTask sysTask
     * @return Boolean
     */
    Boolean pause(SysTask sysTask);

    /**
     * 恢复任务
     * @param sysTask sysTask
     * @return Boolean
     */
    Boolean resume(SysTask sysTask);

    /**
     * 立即执行一次任务
     * @param sysTask sysTask
     * @return Boolean
     */
    Boolean startNow(SysTask sysTask);

    /**
     * 删除任务
     * @param sysTask entity
     * @return Boolean
     */
    Boolean delete(SysTask sysTask);

    /**
     * 校验任务名是否重复
     * @param sysTask sysTask
     * @return Boolean true 已存在
     */
    Boolean checkName(SysTask sysTask);

    /**
     * 启动所有的状态为开启的任务
     * @return boolean
     */
    Boolean startAllTask();
}
