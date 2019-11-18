package com.wang.jmonkey.modules.gauge.strategy;

import com.wang.jmonkey.modules.gauge.model.param.GaugeResult;

import java.util.List;

/**
 * @Description: GaugeResultStrategy
 * @Auther: HeJiawang
 * @Date: 2019/9/26
 */
public interface GaugeResultStrategy {

    /**
     * 计算服刑人员测评结果
     * @param gaugeRecordId gaugeRecordId
     * @param gaugeResultList gaugeResultList
     * @return boolean
     */
    boolean assess(String gaugeRecordId, List<GaugeResult> gaugeResultList);
}
