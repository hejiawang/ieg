package com.wang.jmonkey.modules.gauge.model.dto;

import com.wang.jmonkey.modules.gauge.model.entity.GaugeResultAction;
import com.wang.jmonkey.modules.gauge.model.entity.GaugeResultAks;
import com.wang.jmonkey.modules.gauge.model.entity.GaugeResultQuality;
import com.wang.jmonkey.modules.gauge.model.entity.GaugeResultScl90;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 考生测评结果
 */
@Data
@Accessors(chain = true)
public class GaugeResultDto {

    private GaugeResultQuality quality;

    private GaugeResultAction action;

    private GaugeResultAks aks;

    private GaugeResultScl90 scl;
}
