package com.wang.jmonkey.modules.gauge.service;

import com.wang.jmonkey.modules.gauge.model.entity.GaugeAnswer;
import com.baomidou.mybatisplus.service.IService;

import java.util.Map;

/**
 * <p>
 * 量表————题目答案 服务类
 * </p>
 *
 * @author HeJiawang
 * @since 2019-09-25
 */
public interface IGaugeAnswerService extends IService<GaugeAnswer> {

    /**
     * 获取量表题目
     * @param gaugeId gaugeId
     * @return key: AnswerId value: GaugeAnswer
     */
    Map<String,GaugeAnswer> selectMapByGaugeId(String gaugeId);
}
