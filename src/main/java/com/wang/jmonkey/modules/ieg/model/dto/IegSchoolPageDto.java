package com.wang.jmonkey.modules.ieg.model.dto;

import com.wang.jmonkey.modules.ieg.model.entity.IegSchool;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 报考指南——院校table list Dto信息
 * @author heJiawang
 * @since 2019-03-24
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class IegSchoolPageDto extends IegSchool {

    /**
     * 院校隶属名称
     */
    private String attachTypeName;

    /**
     * 院校类型名称
     */
    private String majorTypeName;

    /**
     * 院校特性编码名称
     */
    public List<String> featureNames;
}
