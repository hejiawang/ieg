package com.wang.jmonkey.modules.ieg.model.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.wang.jmonkey.common.model.BaseEntity;

import com.wang.jmonkey.modules.ieg.model.enums.IegCourseTypeEnums;
import com.wang.jmonkey.modules.ieg.model.enums.IegDegreeTypeEnums;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 报考指南——学校历年录取信息
 * </p>
 *
 * @author HeJiawang
 * @since 2019-03-31
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class IegSchoolMajor extends BaseEntity<IegSchoolMajor> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private String id;
    /**
     * 专业所属学科id
     */
    private String majorTwoId;
    /**
     * 学校基本信息ID
     */
    private String schoolId;
    /**
     * 所属院校ID
     */
    private String facultyId;
    /**
     * 投档单位id
     */
    private String submitId;
    /**
     * 专业名称
     */
    private String name;
    /**
     * 学历层次 B本科 Z专科
     */
    @JSONField(serialzeFeatures= SerializerFeature.WriteEnumUsingToString)
    private IegDegreeTypeEnums degreeType;
    /**
     * 专业编码
     */
    private String code;
    /**
     * 学科类型 W文科 L理科 A文科/理科
     */
    @JSONField(serialzeFeatures= SerializerFeature.WriteEnumUsingToString)
    private IegCourseTypeEnums courseType;
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
     * 学校学生规模
     */
    private String studentScope;
    /**
     * 学费
     */
    private Integer money;
    /**
     * 学制
     */
    private Integer studyLength;
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
