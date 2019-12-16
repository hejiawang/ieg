package com.wang.jmonkey.modules.ieg.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.wang.jmonkey.modules.ieg.model.entity.IegSchoolLog;
import com.baomidou.mybatisplus.service.IService;
import com.wang.jmonkey.modules.ieg.model.param.IegSchoolLogParam;

/**
 * <p>
 * 报考指南——顾问查看院校详细信息记录 服务类
 * </p>
 *
 * @author HeJiawang
 * @since 2019-12-16
 */
public interface IIegSchoolLogService extends IService<IegSchoolLog> {

    /**
     * selectPageList
     * @param param param
     * @return IegSchoolLog
     */
    Page<IegSchoolLog> selectPageList(IegSchoolLogParam param);
}
