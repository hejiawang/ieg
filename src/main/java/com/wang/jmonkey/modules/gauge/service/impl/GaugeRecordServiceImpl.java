package com.wang.jmonkey.modules.gauge.service.impl;

import com.wang.jmonkey.modules.gauge.model.dto.GaugeRecordDto;
import com.wang.jmonkey.modules.gauge.model.dto.GaugeResultDto;
import com.wang.jmonkey.modules.gauge.model.entity.GaugeRecord;
import com.wang.jmonkey.modules.gauge.mapper.GaugeRecordMapper;
import com.wang.jmonkey.modules.gauge.service.*;
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
     * gaugeResultActionService
     */
    @Autowired
    private IGaugeResultActionService gaugeResultActionService;

    /**
     * gaugeResultAksService
     */
    @Autowired
    private IGaugeResultAksService gaugeResultAksService;

    /**
     * gaugeResultQualityService
     */
    @Autowired
    private IGaugeResultQualityService gaugeResultQualityService;

    /**
     * gaugeResultScl90Service
     */
    @Autowired
    private IGaugeResultScl90Service gaugeResultScl90Service;

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
     * 获取学生测评结果
     * @param studentId studentId
     * @return GaugeResultDto
     */
    @Override
    public GaugeResultDto result(String studentId) {
        GaugeResultDto result = new GaugeResultDto();

        result.setAction(gaugeResultActionService.selectNewByStudentId(studentId))
                .setAks(gaugeResultAksService.selectNewByStudentId(studentId))
                .setQuality(gaugeResultQualityService.selectNewByStudentId(studentId))
                .setScl(gaugeResultScl90Service.selectNewByStudentId(studentId));

        return result;
    }
}
