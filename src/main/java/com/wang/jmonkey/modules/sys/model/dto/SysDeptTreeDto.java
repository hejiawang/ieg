package com.wang.jmonkey.modules.sys.model.dto;

import com.wang.jmonkey.common.model.BaseTreeNode;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 部门树形表格数据
 */
@Data
@Accessors(chain = true)
public class SysDeptTreeDto extends BaseTreeNode<SysDeptTreeDto> {

    /**
     * 部门名称
     */
    private String name;
    /**
     * 部门编码
     */
    private String code;
    /**
     * 排序值
     */
    private Integer sort;
    /**
     * 备注
     */
    private String remark;
}
