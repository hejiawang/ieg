package com.wang.jmonkey.modules.gauge.service;

import com.wang.jmonkey.modules.gauge.model.entity.GaugeResultScl90;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 量表————scl90量表结果记录信息 服务类
 * </p>
 *
 * @author HeJiawang
 * @since 2019-10-08
 */
public interface IGaugeResultScl90Service extends IService<GaugeResultScl90> {

    /**
     * 获取服刑人员最新的测评结果
     * @param userId userId
     * @return GaugeResultScl90
     */
    GaugeResultScl90 selectNewByUserId(String userId);

    /**
     * selectByRecordId
     * @param recordId recordId
     * @return HttpResult
     */
    GaugeResultScl90 selectByRecordId(String recordId);
}
