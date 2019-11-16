package com.wang.jmonkey.modules.gauge.model.mo;

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
     * 题目id
     */
    private String topicId;
    /**
     * 题目名称
     */
    private String topicName;
    /**
     * 题目类型
     */
    private String topicType;
    /**
     * 题目顺序
     */
    private Integer topicSort;

    /**
     * 答案id 选择的那个答案
     */
    private String answerId;
    /**
     * 答案名称
     */
    private String answerName;
    /**
     * 答案分数
     */
    private Integer answerScore;

    /**
     * 答题开始时间
     */
    private Date startDate;
    /**
     * 答题结束时间
     */
    private Date endDate;
}
