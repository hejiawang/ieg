package com.wang.jmonkey.modules.sys.model.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Description: 系统设置首页统计信息
 * @Auther: HeJiawang
 * @Date: 2019-01-21
 */
@Data
@Accessors(chain = true)
public class SysCountDto {

    private String name;
    private Integer value;
}
