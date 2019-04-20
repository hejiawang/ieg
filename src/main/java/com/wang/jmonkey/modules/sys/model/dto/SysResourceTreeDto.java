package com.wang.jmonkey.modules.sys.model.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.wang.jmonkey.common.model.BaseTreeNode;
import com.wang.jmonkey.modules.sys.model.enums.ResourceTypeEnums;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Description: 资源信息树形结果
 * @Auther: HeJiawang
 * @Date: 2018/12/12
 */
@Data
@Accessors(chain = true)
public class SysResourceTreeDto extends BaseTreeNode<SysResourceTreeDto> {

    /**
     * 具体资源id
     */
    private String rId;
    /**
     * 资源类型
     */
    @JSONField(serialzeFeatures = SerializerFeature.WriteEnumUsingToString)
    private ResourceTypeEnums type;
    /**
     * 资源名称
     */
    private String name;
}
