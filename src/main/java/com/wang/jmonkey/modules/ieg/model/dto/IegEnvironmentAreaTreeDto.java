package com.wang.jmonkey.modules.ieg.model.dto;

import com.wang.jmonkey.common.model.BaseTreeNode;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Description: 报考指南——地区树
 * @Auther: heJiawang
 * @Date: 2019-04-03
 */
@Data
@Accessors(chain = true)
public class IegEnvironmentAreaTreeDto extends BaseTreeNode<IegEnvironmentAreaTreeDto> {

    /**
     * 地区名称
     */
    private String title;
}
