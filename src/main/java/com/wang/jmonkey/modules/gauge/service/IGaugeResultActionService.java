package com.wang.jmonkey.modules.gauge.service;

import com.wang.jmonkey.modules.gauge.model.entity.GaugeResultAction;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 量表————行为量表结果记录信息 服务类
 * </p>
 *
 * @author HeJiawang
 * @since 2019-09-26
 */
public interface IGaugeResultActionService extends IService<GaugeResultAction> {

    /**
     * 获取服刑人员最新的测评结果
     * @param studentId studentId
     * @return GaugeResultAction
     */
    GaugeResultAction selectNewByStudentId(String studentId);

    /**
     * selectByRecordId
     * @param recordId recordId
     * @return GaugeResultAction
     */
    GaugeResultAction selectByRecordId(String recordId);
}
