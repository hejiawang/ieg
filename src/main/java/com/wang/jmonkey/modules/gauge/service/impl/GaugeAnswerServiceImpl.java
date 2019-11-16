package com.wang.jmonkey.modules.gauge.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.wang.jmonkey.modules.gauge.model.entity.GaugeAnswer;
import com.wang.jmonkey.modules.gauge.mapper.GaugeAnswerMapper;
import com.wang.jmonkey.modules.gauge.service.IGaugeAnswerService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 量表————题目答案 服务实现类
 * </p>
 *
 * @author HeJiawang
 * @since 2019-09-25
 */
@Service
public class GaugeAnswerServiceImpl extends ServiceImpl<GaugeAnswerMapper, GaugeAnswer> implements IGaugeAnswerService {

    /**
     * 获取量表题目
     * @param gaugeId gaugeId
     * @return key: AnswerId value: GaugeAnswer
     */
    @Override
    public Map<String, GaugeAnswer> selectMapByGaugeId(String gaugeId) {
        EntityWrapper<GaugeAnswer> wrapper = new EntityWrapper<>();
        wrapper.setEntity(new GaugeAnswer().setGaugeId(gaugeId));

        List<GaugeAnswer> answerList = super.selectList(wrapper);
        return answerList
                .stream()
                .collect(
                        Collectors.toMap(
                                GaugeAnswer::getId,
                                answer -> answer
                        )
                );
    }
}
