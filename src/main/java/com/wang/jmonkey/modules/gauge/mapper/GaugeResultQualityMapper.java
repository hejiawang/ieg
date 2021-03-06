package com.wang.jmonkey.modules.gauge.mapper;

import com.wang.jmonkey.modules.gauge.model.entity.GaugeResultQuality;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 量表————气质量表结果记录信息 Mapper 接口
 * </p>
 *
 * @author HeJiawang
 * @since 2019-09-26
 */
public interface GaugeResultQualityMapper extends BaseMapper<GaugeResultQuality> {

    /**
     * 获取服刑人员最新的测评结果
     * @param studentId studentId
     * @return GaugeResultQuality
     */
    GaugeResultQuality selectNewByStudentId(@Param("studentId") String studentId);
}
