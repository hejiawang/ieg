package com.wang.jmonkey.modules.ieg.model.dto;

import com.wang.jmonkey.modules.ieg.model.entity.IegSchool;
import com.wang.jmonkey.modules.ieg.model.entity.IegSchoolDetail;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 报考指南——院校Dto信息
 * @author heJiawang
 * @since 2019-03-24
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class IegSchoolDto extends IegSchool {

    /**
     * 院校特性编码
     */
    public List<String> features;

    /**
     * 院校详细信息
     */
    private IegSchoolDetail detail;

}
