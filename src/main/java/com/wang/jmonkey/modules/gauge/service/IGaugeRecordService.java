package com.wang.jmonkey.modules.gauge.service;

import com.wang.jmonkey.modules.gauge.model.dto.GaugeRecordDto;
import com.wang.jmonkey.modules.gauge.model.entity.GaugeRecord;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 量表————服刑人员测评记录 服务类
 * </p>
 *
 * @author HeJiawang
 * @since 2019-09-26
 */
public interface IGaugeRecordService extends IService<GaugeRecord> {

    /**
     * 获取未完成测评个数
     * @param userId userId
     * @return int
     */
    int countMustNo(String userId);

    /**
     * list信息
     * @param userId userId
     * @return List<GaugeRecordDto>
     */
    List<GaugeRecordDto> selectListByUserId(String userId);

}
