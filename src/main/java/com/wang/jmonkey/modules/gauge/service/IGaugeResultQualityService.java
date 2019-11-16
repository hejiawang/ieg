package com.wang.jmonkey.modules.gauge.service;

import com.wang.jmonkey.modules.gauge.model.entity.GaugeResultQuality;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 量表————气质量表结果记录信息 服务类
 * </p>
 *
 * @author HeJiawang
 * @since 2019-09-26
 */
public interface IGaugeResultQualityService extends IService<GaugeResultQuality> {

    /**
     * 获取服刑人员最新的测评结果
     * @param userId userId
     * @return GaugeResultQuality
     */
    GaugeResultQuality selectNewByUserId(String userId);

    /**
     * selectByRecordId
     * @param recordId recordId
     * @return HttpResult
     */
    GaugeResultQuality selectByRecordId(String recordId);
}
