package com.wang.jmonkey.modules.gauge.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.wang.jmonkey.modules.gauge.model.entity.GaugeAnswerInfo;
import com.wang.jmonkey.modules.gauge.mapper.GaugeAnswerInfoMapper;
import com.wang.jmonkey.modules.gauge.model.enums.GaugeAnswerInfoTypeEnum;
import com.wang.jmonkey.modules.gauge.service.IGaugeAnswerInfoService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.xiaoleilu.hutool.collection.CollectionUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 量表————测评规则信息 服务实现类
 * </p>
 *
 * @author HeJiawang
 * @since 2019-09-25
 */
@Service
public class GaugeAnswerInfoServiceImpl extends ServiceImpl<GaugeAnswerInfoMapper, GaugeAnswerInfo> implements IGaugeAnswerInfoService {

    /**
     * 根据策略类型获取评分信息
     * @param type 策略类型
     * @return List<GaugeAnswerInfo>
     */
    @Override
    public List<GaugeAnswerInfo> selectListByType(GaugeAnswerInfoTypeEnum type) {
        EntityWrapper<GaugeAnswerInfo> wrapper = new EntityWrapper<>(
                new GaugeAnswerInfo().setType(type)
        );
        wrapper.orderBy("sort");

        return selectList(wrapper);
    }

    /**
     * 根据策略类型获取评分信息 key:id value:评分信息
     * @param type 策略类型
     * @return Map<String, GaugeAnswerInfo>
     */
    @Override
    public Map<String, GaugeAnswerInfo> selectMapByType(GaugeAnswerInfoTypeEnum type) {
        Map<String, GaugeAnswerInfo> result = CollectionUtil.newHashMap();

        List<GaugeAnswerInfo> answerInfoList = this.selectListByType(type);
        if (CollectionUtil.isNotEmpty(answerInfoList)) {
            answerInfoList.forEach(answerInfo ->
                result.put(answerInfo.getId(), answerInfo)
            );
        }

        return result;
    }

    /**
     * 获取所有测评规则信息
     * @return 测评规则信息
     */
    @Override
    public List<GaugeAnswerInfo> listAll() {
        EntityWrapper<GaugeAnswerInfo> wrapper = new EntityWrapper<>();
        return super.selectList(wrapper);
    }
}
