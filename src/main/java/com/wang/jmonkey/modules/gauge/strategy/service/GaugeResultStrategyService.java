package com.wang.jmonkey.modules.gauge.strategy.service;

import com.wang.jmonkey.modules.gauge.strategy.GaugeResultStrategy;
import com.wang.jmonkey.modules.gauge.model.enums.GaugeAnswerInfoTypeEnum;

/**
 * @Description: 测评结果算法
 * @Auther: HeJiawang
 * @Date: 2019/9/26
 */
public interface GaugeResultStrategyService {

    /**
     * 选择使用的测评结果算法
     * @param type 类型
     * @return 测评算法
     */
    GaugeResultStrategy render(GaugeAnswerInfoTypeEnum type);
}
