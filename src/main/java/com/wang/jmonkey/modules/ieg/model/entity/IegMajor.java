package com.wang.jmonkey.modules.ieg.model.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.wang.jmonkey.common.model.BaseEntity;

import com.wang.jmonkey.modules.ieg.model.enums.IegDegreeTypeEnums;
import com.wang.jmonkey.modules.ieg.model.enums.IegMajorLevelTypeEnums;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 报考指南——专业定义
 * </p>
 *
 * @author heJiawang
 * @since 2019-03-24
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class IegMajor extends BaseEntity<IegMajor> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private String id;
    /**
     * 上级专业ID
     */
    private String parentId;
    /**
     * 学历层次 B本科 Z专科
     */
    @JSONField(serialzeFeatures= SerializerFeature.WriteEnumUsingToString)
    private IegDegreeTypeEnums degreeType;
    /**
     * 层级分类 One门类 Two学科 Three专业
     */
    @JSONField(serialzeFeatures= SerializerFeature.WriteEnumUsingToString)
    private IegMajorLevelTypeEnums levelType;
    /**
     * 专业名称
     */
    private String name;
    /**
     * 专业编码
     */
    private String code;
    /**
     * 排序值
     */
    private Integer sort;
    /**
     * 专业简介
     */
    private String describe;
    /**
     * 从业方向
     */
    private String workDirection;
    /**
     * 专业课程
     */
    private String course;
    /**
     * 全国普通高校毕业生规模
     */
    private String studentScope;

    /**
     * 男女比例——男生比例
     */
    private Double ratioSexMan;
    /**
     * 男女比例——女生比例
     */
    private Double ratioSexWoman;

    /**
     * 高考文理科比例——文科比例
     */
    private Double ratioCourseArts;
    /**
     * 高考文理科比例——理科比例
     */
    private Double ratioCourseSci;

    /**
     * 专业满意度评价——综合满意度
     */
    private Double ratioAssessWhole;
    /**
     * 专业满意度评价——就业满意度
     */
    private Double ratioAssessWork;
    /**
     * 专业满意度评价——教学质量满意度
     */
    private Double ratioAssessStudy;
    /**
     * 专业满意度评价——办学条件满意度
     */
    private Double ratioAssessShool;
    /**
     * 专业满意度评价——难易程度
     */
    private Double ratioAssessDifficulty;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
