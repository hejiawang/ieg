package com.wang.jmonkey.common.quartz;

import com.wang.jmonkey.modules.sys.service.ISysTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 开机启动时，启动定时任务配置
 * </p>
 *
 * @author HeJiawang
 * @since 2019-01-24
 */
@Component
public class TaskApplicationRunner implements ApplicationRunner {

    /**
     * service
     */
    @Autowired
    private ISysTaskService service;

    /**
     * run
     * @param args
     * @throws Exception
     */
    @Override
    public void run(ApplicationArguments args) throws Exception {
        service.startAllTask();
    }
}
