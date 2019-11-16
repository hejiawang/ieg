package com.wang.jmonkey.modules.gauge.mapper;

import com.wang.jmonkey.modules.gauge.model.entity.GaugeResultAks;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 量表————艾克森量表结果记录信息 Mapper 接口
 * </p>
 *
 * @author HeJiawang
 * @since 2019-10-19
 */
public interface GaugeResultAksMapper extends BaseMapper<GaugeResultAks> {

    /**
     * 获取服刑人员最新的测评结果
     * @param userId userId
     * @return GaugeResultAks
     */
    GaugeResultAks selectNewByUserId(@Param("userId") String userId);
}
