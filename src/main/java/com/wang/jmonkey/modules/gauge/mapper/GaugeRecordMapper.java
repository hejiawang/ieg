package com.wang.jmonkey.modules.gauge.mapper;

import com.wang.jmonkey.modules.gauge.model.dto.GaugeRecordDto;
import com.wang.jmonkey.modules.gauge.model.entity.GaugeRecord;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 量表————服刑人员测评记录 Mapper 接口
 * </p>
 *
 * @author HeJiawang
 * @since 2019-09-26
 */
public interface GaugeRecordMapper extends BaseMapper<GaugeRecord> {

    /**
     * 获取未完成测评个数
     * @param userId userId
     * @return int
     */
    int countMustNo(@Param("userId") String userId);

    /**
     * list信息
     * @param userId userId
     * @return List<GaugeRecordDto>
     */
    List<GaugeRecordDto> selectListByUserId(@Param("userId") String userId);
}
