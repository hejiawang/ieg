package com.wang.jmonkey.modules.sys.mapper;

import com.wang.jmonkey.modules.sys.model.entity.SysTask;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 * 定时任务配置表 Mapper 接口
 * </p>
 *
 * @author HeJiawang
 * @since 2019-01-24
 */
public interface SysTaskMapper extends BaseMapper<SysTask> {

    /**
     * 校验任务名是否存在
     * @param sysTask 任务信息
     * @return > 0 存在
     */
    int checkName(SysTask sysTask);
}
