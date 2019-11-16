package com.wang.jmonkey.modules.gauge.service.impl;

import com.wang.jmonkey.modules.gauge.model.dto.GaugeRecordDto;
import com.wang.jmonkey.modules.gauge.model.entity.GaugeRecord;
import com.wang.jmonkey.modules.gauge.mapper.GaugeRecordMapper;
import com.wang.jmonkey.modules.gauge.service.IGaugeRecordService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 量表————服刑人员测评记录 服务实现类
 * </p>
 *
 * @author HeJiawang
 * @since 2019-09-26
 */
@Service
public class GaugeRecordServiceImpl extends ServiceImpl<GaugeRecordMapper, GaugeRecord> implements IGaugeRecordService {

    /**
     * mapper
     */
    @Autowired
    private GaugeRecordMapper mapper;

    /**
     * 获取未完成测评个数
     * @param userId userId
     * @return int
     */
    @Override
    public int countMustNo(String userId) {
        return mapper.countMustNo(userId);
    }

    /**
     * list信息
     * @param userId userId
     * @return List<GaugeRecordDto>
     */
    @Override
    public List<GaugeRecordDto> selectListByUserId(String userId) {
        return mapper.selectListByUserId(userId);
    }

}
