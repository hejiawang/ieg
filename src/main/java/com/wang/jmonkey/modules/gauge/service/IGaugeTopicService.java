package com.wang.jmonkey.modules.gauge.service;

import com.wang.jmonkey.modules.gauge.model.dto.GaugeTopicAnswerDto;
import com.wang.jmonkey.modules.gauge.model.entity.GaugeTopic;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 量表————题目 服务类
 * </p>
 *
 * @author HeJiawang
 * @since 2019-09-25
 */
public interface IGaugeTopicService extends IService<GaugeTopic> {

    /**
     * 获取量表的内容
     * @param gaugeId 量表id
     * @return 量表内容
     */
    List<GaugeTopicAnswerDto> topicList(String gaugeId);

    /**
     * 获取量表的内容
     * @param gaugeId gaugeId
     * @return key: topicId value: GaugeTopic
     */
    Map<String,GaugeTopic> selectMapByGaugeId(String gaugeId);
}
