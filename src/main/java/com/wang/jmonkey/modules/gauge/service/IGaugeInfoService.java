package com.wang.jmonkey.modules.gauge.service;

import com.wang.jmonkey.modules.gauge.model.entity.GaugeInfo;
import com.baomidou.mybatisplus.service.IService;
import com.wang.jmonkey.modules.gauge.model.param.GaugeAnswerParam;

import java.util.List;

/**
 * <p>
 * 量表————基本信息 服务类
 * </p>
 *
 * @author HeJiawang
 * @since 2019-09-25
 */
public interface IGaugeInfoService extends IService<GaugeInfo> {

    /**
     * 量表列表
     * @return List<GaugeInfo>
     */
    List<GaugeInfo> selectList();

    /**
     * 量表测评
     * @param param param
     * @return Boolean
     */
    Boolean handle(GaugeAnswerParam param);
}
