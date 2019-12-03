package com.wang.jmonkey.modules.ieg.model.dto;

import com.wang.jmonkey.modules.ieg.model.entity.IegSchool;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * IegSchoolInfoDto
 * @author heJiawang
 * @since 2019-12-3
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class IegSchoolInfoDto extends IegSchool {

    /**
     * 院校类型
     */
    private String majorTypeName;

    /**
     * 院校归属类型
     */
    private String attachTypeName;
}
