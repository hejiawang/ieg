package com.wang.jmonkey.modules.ieg.model.dto;

import com.wang.jmonkey.modules.ieg.model.entity.IegSchoolMajor;
import com.wang.jmonkey.modules.ieg.model.entity.IegSchoolMajorFeatures;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 报考指南——院校专业dto信息
 * @author heJiawang
 * @since 2019-04-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class IegSchoolMajorDto extends IegSchoolMajor {

    /**
     * 专业特征名称
     */
    private List<String> featureNames;

    /**
     * 专业特征编码
     */
    private List<String> features;

    /**
     * 所属院系名称
     */
    private String facultyName;
}
