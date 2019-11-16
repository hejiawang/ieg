package com.wang.jmonkey.modules.gauge.mapper;

import com.wang.jmonkey.modules.gauge.model.dto.GaugeTopicAnswerDto;
import com.wang.jmonkey.modules.gauge.model.entity.GaugeTopic;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 量表————题目 Mapper 接口
 * </p>
 *
 * @author HeJiawang
 * @since 2019-09-25
 */
public interface GaugeTopicMapper extends BaseMapper<GaugeTopic> {

    /**
     * 获取量表的内容
     * @param gaugeId 量表id
     * @return 量表内容
     */
    List<GaugeTopicAnswerDto> topicList(@Param("gaugeId") String gaugeId);
}
