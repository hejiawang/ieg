package com.wang.jmonkey.modules.gauge.mapper;

import com.wang.jmonkey.modules.gauge.model.entity.GaugeResultScl90;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 量表————scl90量表结果记录信息 Mapper 接口
 * </p>
 *
 * @author HeJiawang
 * @since 2019-10-08
 */
public interface GaugeResultScl90Mapper extends BaseMapper<GaugeResultScl90> {

    /**
     * 获取服刑人员最新的测评结果
     * @param studentId studentId
     * @return GaugeResultScl90
     */
    GaugeResultScl90 selectNewByStudentId(@Param("studentId") String studentId);
}
