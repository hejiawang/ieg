package com.wang.jmonkey.modules.gauge.model.param;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.wang.jmonkey.modules.gauge.model.entity.GaugeRecord;
import com.wang.jmonkey.modules.gauge.model.enums.GaugeAnswerInfoTypeEnum;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.beans.BeanUtils;

import java.util.Date;
import java.util.List;

/**
 * @Description: 提交答题信息
 * @Auther: HeJiawang
 * @Date: 2019/9/25
 */
@Data
@Accessors(chain = true)
public class GaugeAnswerParam {

    /**
     * 答题人
     */
    private String studentId;

    /**
     * 量表基本信息id 测评的那个量表
     */
    private String gaugeId;

    /**
     * 答题开始时间
     */
    private Date startDate;

    /**
     * 答题结束时间
     */
    private Date endDate;

    /**
     * 结果记录表 气质量表Quality 行为量表Action
     */
    @JSONField(serialzeFeatures= SerializerFeature.WriteEnumUsingToString)
    private GaugeAnswerInfoTypeEnum resultType;

    /**
     * 提交答题信息明细
     */
    private List<GaugeResult> resultList;

    /**
     * buildGaugeRecord
     * @return GaugeRecord
     */
    public GaugeRecord buildGaugeRecord() {
        GaugeRecord record = new GaugeRecord();
        BeanUtils.copyProperties(this, record);

        return record;
    }
}
