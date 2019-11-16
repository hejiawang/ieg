package com.wang.jmonkey.modules.gauge.strategy.service.impl;

import com.wang.jmonkey.modules.gauge.model.enums.GaugeAnswerInfoTypeEnum;
import com.wang.jmonkey.modules.gauge.service.impl.GaugeResultActionServiceImpl;
import com.wang.jmonkey.modules.gauge.service.impl.GaugeResultAksServiceImpl;
import com.wang.jmonkey.modules.gauge.service.impl.GaugeResultQualityServiceImpl;
import com.wang.jmonkey.modules.gauge.service.impl.GaugeResultScl90ServiceImpl;
import com.wang.jmonkey.modules.gauge.strategy.GaugeResultStrategy;
import com.wang.jmonkey.modules.gauge.strategy.service.GaugeResultStrategyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description: 测评结果算法
 * @Auther: HeJiawang
 * @Date: 2019/9/26
 */
@Service
public class GaugeResultStrategyServiceImpl implements GaugeResultStrategyService {

    private final Map<GaugeAnswerInfoTypeEnum, GaugeResultStrategy> strategyMap = new ConcurrentHashMap<>();

    /**
     * 构建策略信息
     * @param strategyList 策略信息
     */
    @Autowired
    public GaugeResultStrategyServiceImpl(List<GaugeResultStrategy> strategyList) {
        for(GaugeResultStrategy strategy : strategyList) {
            if (strategy instanceof GaugeResultActionServiceImpl) {
                strategyMap.put(GaugeAnswerInfoTypeEnum.Action, strategy);
            }

            if (strategy instanceof GaugeResultQualityServiceImpl) {
                strategyMap.put(GaugeAnswerInfoTypeEnum.Quality, strategy);
            }

            if (strategy instanceof GaugeResultScl90ServiceImpl) {
                strategyMap.put(GaugeAnswerInfoTypeEnum.SCL90, strategy);
            }

            if (strategy instanceof GaugeResultAksServiceImpl) {
                strategyMap.put(GaugeAnswerInfoTypeEnum.AKS, strategy);
            }
        }
    }

    @Override
    public GaugeResultStrategy render(GaugeAnswerInfoTypeEnum type) {
        return this.strategyMap.get(type);
    }
}
