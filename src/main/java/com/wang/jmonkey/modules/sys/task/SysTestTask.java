package com.wang.jmonkey.modules.sys.task;

import com.wang.jmonkey.common.quartz.JMonkeyBaseTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description: 测试任务类
 * @Auther: HeJiawang
 * @Date: 2019-01-24
 */
public class SysTestTask extends JMonkeyBaseTask {

    private static Logger logger = LoggerFactory.getLogger(SysTestTask.class);

    /**
     * 任务执行
     */
    @Override
    public void run() {
        logger.info("test task run ...... ");
    }
}
