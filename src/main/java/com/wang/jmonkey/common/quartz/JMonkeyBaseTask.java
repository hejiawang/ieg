package com.wang.jmonkey.common.quartz;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @Description: 定时任务父类
 * @Auther: HeJiawang
 * @Date: 2019-01-24
 */
@DisallowConcurrentExecution
public abstract class JMonkeyBaseTask implements Job {
    // @DisallowConcurrentExecution 不能并发执行同一个Job

    /**
     * 执行定时任务
     * 在执行具体任务时，能够在run()前后做其他业务，比如发送消息通知管理员定时任务已经执行
     * @param context
     * @throws JobExecutionException
     */
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        run();
    }

    /**
     * 模板方法，在子类中重写该方法
     */
    public abstract void run();
}
