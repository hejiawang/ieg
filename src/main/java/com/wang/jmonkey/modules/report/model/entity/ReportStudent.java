package com.wang.jmonkey.modules.report.model.entity;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.wang.jmonkey.common.model.BaseEntity;

import com.wang.jmonkey.common.model.enums.SexEnum;
import com.wang.jmonkey.modules.report.model.enums.ReportStudentGrade;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 报告————学生基本信息
 * </p>
 *
 * @author HeJiawang
 * @since 2019-08-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class ReportStudent extends BaseEntity<ReportStudent> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private String id;
    /**
     * 接待考生的顾问id
     */
    private String userId;
    /**
     * 考生编码
     */
    private String code;
    /**
     * 考生名称
     */
    private String name;
    /**
     * 性别 man男 woman女 other其他
     */
    @JSONField(serialzeFeatures= SerializerFeature.WriteEnumUsingToString)
    private SexEnum sex;
    /**
     * 出生日期
     */
    private Date birthday;
    /**
     * 年级  One高一 Two高二 Three高三
     */
    @JSONField(serialzeFeatures= SerializerFeature.WriteEnumUsingToString)
    private ReportStudentGrade grade;
    /**
     * 最后到访日期
     */
    private Date reportDate;
    /**
     * 在读学校
     */
    private String schoolName;
    /**
     * 毕业时间
     */
    private Date schoolDate;
    /**
     * 考生详细信息
     */
    private String info;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
