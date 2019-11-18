package com.wang.jmonkey.modules.gauge.model.param;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @Description: mongodb中存储的服刑人员答题结果
 * @Auther: HeJiawang
 * @Date: 2019/9/26
 */
@Data
@Accessors(chain = true)
public class GaugeResult {

    /**
     * 题目类型
     */
    private String topicType;
    /**
     * 答案分数
     */
    private Integer answerScore;
}
