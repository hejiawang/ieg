package com.wang.jmonkey.modules.ieg.model.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.wang.jmonkey.common.model.BaseEntity;

import com.wang.jmonkey.modules.ieg.model.enums.IegDegreeTypeEnums;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 报考指南——学校基本信息
 * </p>
 *
 * @author HeJiawang
 * @since 2019-03-26
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class IegSchool extends BaseEntity<IegSchool> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private String id;
    /**
     * 学校logo
     */
    private String logo;
    /**
     * 学校名称
     */
    private String name;
    /**
     * 院校编码
     */
    private String code;
    /**
     * 院校所在地——省
     */
    private String areaProvince;
    /**
     * 院校所在地——市
     */
    private String areaCity;
    /**
     * 院校所在地——区
     */
    private String areaArea;
    /**
     * 院校所在地详细地址
     */
    private String areaDetail;
    /**
     * 院校网址
     */
    private String website;
    /**
     * 联系方式
     */
    private String phone;
    /**
     * 院校类型 字典——ieg-school-major-type
     */
    private String majorType;
    /**
     * 排名
     */
    private Integer sort;
    /**
     * 院校归属类型 字典——ieg-school-attach-type
     */
    private String attachType;
    /**
     * 院校归属详细信息
     */
    private String attachInfo;
    /**
     * 学历层次 B本科 Z专科 A全部
     */
    @JSONField(serialzeFeatures= SerializerFeature.WriteEnumUsingToString)
    private IegDegreeTypeEnums degreeType;
    /**
     * 满意度
     */
    private Double satisfy;
    /**
     * 满意度——环境
     */
    private Double ratioSatisfyEnvironment;
    /**
     * 满意度——生活
     */
    private Double ratioSatisfyLife;
    /**
     * 满意度——教育
     */
    private Double ratioSatisfyEdu;
    /**
     * 满意度——就业
     */
    private Double ratioSatisfyWork;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
