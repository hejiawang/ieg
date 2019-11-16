package com.wang.jmonkey.modules.gauge.service;

import com.wang.jmonkey.modules.gauge.model.entity.GaugeAnswerInfo;
import com.baomidou.mybatisplus.service.IService;
import com.wang.jmonkey.modules.gauge.model.enums.GaugeAnswerInfoTypeEnum;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 量表————测评规则信息 服务类
 * </p>
 *
 * @author HeJiawang
 * @since 2019-09-25
 */
public interface IGaugeAnswerInfoService extends IService<GaugeAnswerInfo> {

    /**
     * 根据策略类型获取评分信息
     * @param type 策略类型
     * @return List<GaugeAnswerInfo>
     */
    List<GaugeAnswerInfo> selectListByType(GaugeAnswerInfoTypeEnum type);

    /**
     * 根据策略类型获取评分信息 key:id value:评分信息
     * @param type 策略类型
     * @return Map<String, GaugeAnswerInfo>
     */
    Map<String, GaugeAnswerInfo> selectMapByType(GaugeAnswerInfoTypeEnum type);

    /**
     * 获取所有测评规则信息
     * @return 测评规则信息
     */
    List<GaugeAnswerInfo> listAll();
}
