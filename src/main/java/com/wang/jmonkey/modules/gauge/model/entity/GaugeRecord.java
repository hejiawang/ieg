package com.wang.jmonkey.modules.gauge.model.entity;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.wang.jmonkey.common.model.BaseEntity;

import com.wang.jmonkey.modules.gauge.model.enums.GaugeAnswerInfoTypeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 量表————服刑人员测评记录
 * </p>
 *
 * @author HeJiawang
 * @since 2019-09-26
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class GaugeRecord extends BaseEntity<GaugeRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private String id;
    /**
     * 答题事件id 通过这个字段关联mysql中服刑人员答题记录，mongodb中服刑人员答题明细，mongodb中服刑人员微表情数据
     */
    private String handleId;
    /**
     * 答题人——服刑人员id
     */
    private String userId;
    /**
     * 量表基本信息id 测评的那个量表
     */
    private String gaugeId;
    /**
     * 开始日期
     */
    private Date startDate;
    /**
     * 结束日期
     */
    private Date endDate;
    /**
     * 结果记录表 气质量表Quality 行为量表Action
     */
    @JSONField(serialzeFeatures= SerializerFeature.WriteEnumUsingToString)
    private GaugeAnswerInfoTypeEnum resultType;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
