package com.wang.jmonkey.modules.gauge.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.wang.jmonkey.modules.gauge.model.entity.GaugeInfo;
import com.wang.jmonkey.modules.gauge.mapper.GaugeInfoMapper;
import com.wang.jmonkey.modules.gauge.model.entity.GaugeRecord;
import com.wang.jmonkey.modules.gauge.model.param.GaugeAnswerParam;
import com.wang.jmonkey.modules.gauge.service.IGaugeInfoService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.wang.jmonkey.modules.gauge.service.IGaugeRecordService;
import com.wang.jmonkey.modules.gauge.strategy.service.GaugeResultStrategyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 量表————基本信息 服务实现类
 * </p>
 *
 * @author HeJiawang
 * @since 2019-09-25
 */
@Service
public class GaugeInfoServiceImpl extends ServiceImpl<GaugeInfoMapper, GaugeInfo> implements IGaugeInfoService {

    /**
     * gaugeRecordService
     */
    @Autowired
    private IGaugeRecordService gaugeRecordService;

    /**
     * gaugeResultStrategyService
     */
    @Autowired
    private GaugeResultStrategyService gaugeResultStrategyService;

    /**
     * 量表列表
     * @return List<GaugeInfo>
     */
    @Override
    public List<GaugeInfo> selectList() {
        EntityWrapper<GaugeInfo> wrapper = new EntityWrapper<>();
        wrapper.orderBy("sort");

        return super.selectList(wrapper);
    }

    /**
     * 量表测评
     * @param param param
     * @return Boolean
     */
    @Override
    public Boolean handle(GaugeAnswerParam param) {
        GaugeRecord record = param.buildGaugeRecord();
        gaugeRecordService.insert(record);

        return gaugeResultStrategyService.render(record.getResultType())
                .assess(record.getId(), param.getResultList());
    }
}
