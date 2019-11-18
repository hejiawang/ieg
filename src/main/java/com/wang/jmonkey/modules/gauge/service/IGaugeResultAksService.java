package com.wang.jmonkey.modules.gauge.service;

import com.wang.jmonkey.modules.gauge.model.entity.GaugeResultAks;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 量表————艾克森量表结果记录信息 服务类
 * </p>
 *
 * @author HeJiawang
 * @since 2019-10-19
 */
public interface IGaugeResultAksService extends IService<GaugeResultAks> {

    /**
     * 获取服刑人员最新的测评结果
     * @param studentId studentId
     * @return GaugeResultAks
     */
    GaugeResultAks selectNewByStudentId(String studentId);

    /**
     * selectByRecordId
     * @param recordId recordId
     * @return HttpResult
     */
    GaugeResultAks selectByRecordId(String recordId);
}
