package com.wang.jmonkey.modules.gauge.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.wang.jmonkey.modules.gauge.model.dto.GaugeTopicAnswerDto;
import com.wang.jmonkey.modules.gauge.model.entity.GaugeTopic;
import com.wang.jmonkey.modules.gauge.mapper.GaugeTopicMapper;
import com.wang.jmonkey.modules.gauge.service.IGaugeTopicService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 量表————题目 服务实现类
 * </p>
 *
 * @author HeJiawang
 * @since 2019-09-25
 */
@Service
public class GaugeTopicServiceImpl extends ServiceImpl<GaugeTopicMapper, GaugeTopic> implements IGaugeTopicService {

    /**
     * mapper
     */
    @Autowired
    private GaugeTopicMapper mapper;

    /**
     * 获取量表的内容
     * @param gaugeId 量表id
     * @return 量表内容
     */
    @Override
    public List<GaugeTopicAnswerDto> topicList(String gaugeId) {
        return mapper.topicList(gaugeId);
    }

    /**
     * 获取量表的内容
     * @param gaugeId gaugeId
     * @return key: topicId value: GaugeTopic
     */
    @Override
    public Map<String, GaugeTopic> selectMapByGaugeId(String gaugeId) {
        EntityWrapper<GaugeTopic> wrapper = new EntityWrapper<>();
        wrapper.setEntity(new GaugeTopic().setGaugeId(gaugeId));

        List<GaugeTopic> topicList = super.selectList(wrapper);
        return topicList
                .stream()
                .collect(
                        Collectors.toMap(
                                GaugeTopic::getId,
                                topic -> topic
                        )
                );
    }
}
