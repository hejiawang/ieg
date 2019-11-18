package com.wang.jmonkey.modules.gauge.mapper;

import com.wang.jmonkey.modules.gauge.model.entity.GaugeResultAction;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 量表————行为量表结果记录信息 Mapper 接口
 * </p>
 *
 * @author HeJiawang
 * @since 2019-09-26
 */
public interface GaugeResultActionMapper extends BaseMapper<GaugeResultAction> {

    /**
     * 获取服刑人员最新的测评结果
     * @param studentId studentId
     * @return GaugeResultAction
     */
    GaugeResultAction selectNewByStudentId(@Param("studentId") String studentId);
}
