package com.wang.jmonkey.modules.report.model.entity;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.wang.jmonkey.common.model.BaseEntity;

import com.wang.jmonkey.common.model.enums.YesOrNoEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 报告————学生来访记录
 * </p>
 *
 * @author HeJiawang
 * @since 2019-08-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class ReportStudentRecord extends BaseEntity<ReportStudentRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private String id;
    /**
     * 考生主表id
     */
    private String studentId;
    /**
     * 来访时间
     */
    private Date recordDate;
    /**
     * 全省排名
     */
    private Integer position;
    /**
     * 总成绩
     */
    private Double totalScore;
    /**
     * 语文成绩
     */
    private Double yuwenScore;
    /**
     * 数学成绩
     */
    private Double shuxuScore;
    /**
     * 英语成绩
     */
    private Double yingyuScore;
    /**
     * 物理成绩
     */
    private Double wuliScore;
    /**
     * 化学成绩
     */
    private Double huaxueScore;
    /**
     * 生物成绩
     */
    private Double shengwuScore;
    /**
     * 历史成绩
     */
    private Double lishiScore;
    /**
     * 地理成绩
     */
    private Double diliScore;
    /**
     * 政治成绩
     */
    private Double zhengzhiScore;
    /**
     * 语文成绩是否算总成绩
     */
    @JSONField(serialzeFeatures= SerializerFeature.WriteEnumUsingToString)
    private YesOrNoEnum yuwenState;
    /**
     * 数学成绩是否算总成绩
     */
    @JSONField(serialzeFeatures= SerializerFeature.WriteEnumUsingToString)
    private YesOrNoEnum shuxuState;
    /**
     * 英语成绩是否算总成绩
     */
    @JSONField(serialzeFeatures= SerializerFeature.WriteEnumUsingToString)
    private YesOrNoEnum yingyuState;
    /**
     * 物理成绩是否算总成绩
     */
    @JSONField(serialzeFeatures= SerializerFeature.WriteEnumUsingToString)
    private YesOrNoEnum wuliState;
    /**
     * 化学成绩是否算总成绩
     */
    @JSONField(serialzeFeatures= SerializerFeature.WriteEnumUsingToString)
    private YesOrNoEnum huaxueState;
    /**
     * 生物成绩是否算总成绩
     */
    @JSONField(serialzeFeatures= SerializerFeature.WriteEnumUsingToString)
    private YesOrNoEnum shengwuState;
    /**
     * 历史成绩是否算总成绩
     */
    @JSONField(serialzeFeatures= SerializerFeature.WriteEnumUsingToString)
    private YesOrNoEnum lishiState;
    /**
     * 地理成绩是否算总成绩
     */
    @JSONField(serialzeFeatures= SerializerFeature.WriteEnumUsingToString)
    private YesOrNoEnum diliState;
    /**
     * 政治成绩是否算总成绩
     */
    @JSONField(serialzeFeatures= SerializerFeature.WriteEnumUsingToString)
    private YesOrNoEnum zhengzhiState;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
