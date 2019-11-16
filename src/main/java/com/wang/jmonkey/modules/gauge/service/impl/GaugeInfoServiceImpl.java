package com.wang.jmonkey.modules.gauge.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.wang.jmonkey.modules.gauge.model.entity.GaugeInfo;
import com.wang.jmonkey.modules.gauge.mapper.GaugeInfoMapper;
import com.wang.jmonkey.modules.gauge.service.IGaugeInfoService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
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
     * 量表列表
     * @return List<GaugeInfo>
     */
    @Override
    public List<GaugeInfo> selectList() {
        EntityWrapper<GaugeInfo> wrapper = new EntityWrapper<>();
        wrapper.orderBy("sort");

        return super.selectList(wrapper);
    }
}
