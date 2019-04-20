package com.wang.jmonkey.modules.sys.model.dto;

import com.wang.jmonkey.modules.sys.model.entity.SysDict;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 字典dto信息
 */
@Data
@Accessors(chain = true)
public class SysDictDto extends SysDict {

    /**
     * 上级字典lable
     */
    private String parentLable;
}
