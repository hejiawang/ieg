package com.wang.jmonkey.modules.sys.model.dto;

import com.wang.jmonkey.common.model.BaseTreeNode;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 字典树形表格数据
 */
@Data
@Accessors(chain = true)
public class SysDictTreeDto extends BaseTreeNode<SysDictTreeDto> {

    /**
     * 字典标签
     */
    private String lable;
    /**
     * 字典键值
     */
    private String value;
    /**
     * 字典排序
     */
    private Integer sort;
    /**
     * 备注
     */
    private String remark;
}
