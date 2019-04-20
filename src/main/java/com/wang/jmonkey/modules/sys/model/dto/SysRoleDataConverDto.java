package com.wang.jmonkey.modules.sys.model.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * SysRoleDataConverDto
 * @author HeJiawang
 * @since 2019-03-21
 */
@Data
@Accessors(chain = true)
public class SysRoleDataConverDto {

    private String scopeId;

    private String ruleId;
}
