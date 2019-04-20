package com.wang.jmonkey.modules.ieg.model.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.wang.jmonkey.common.model.BaseTreeNode;
import com.wang.jmonkey.modules.ieg.model.enums.IegMajorLevelTypeEnums;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Description: 报考指南——专业树信息
 * @Auther: heJiawang
 * @Date: 2019-03-24
 */
@Data
@Accessors(chain = true)
public class IegMajorTreeDto extends BaseTreeNode<IegMajorTreeDto> {

    /**
     * 专业名称
     */
    private String name;

    /**
     * 层级分类 One门类 Two学科 Three专业
     */
    @JSONField(serialzeFeatures= SerializerFeature.WriteEnumUsingToString)
    private IegMajorLevelTypeEnums levelType;
}
