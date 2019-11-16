package com.wang.jmonkey.modules.gauge.model.dto;

import com.wang.jmonkey.modules.gauge.model.entity.GaugeAnswer;
import com.wang.jmonkey.modules.gauge.model.entity.GaugeTopic;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @Description: GaugeTopicAnswerDto
 * @Auther: HeJiawang
 * @Date: 2019/9/25
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class GaugeTopicAnswerDto extends GaugeTopic {

    /**
     * answerList
     */
    private List<GaugeAnswer> answerList;
}
