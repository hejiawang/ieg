package com.wang.jmonkey.modules.sys.model.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.wang.jmonkey.common.model.BaseTreeNode;
import com.wang.jmonkey.common.model.enums.YesOrNoEnum;
import com.wang.jmonkey.modules.sys.model.enums.MenuShowTypeEnums;
import com.xiaoleilu.hutool.collection.CollectionUtil;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;

/**
 * @Description: 树形菜单
 * TODO 将系统、菜单、按钮分开后，构建树时本身id与resource表中的id，在代码逻辑中容易混淆，一定要注意
 * @Auther: HeJiawang
 * @Date: 2018/12/11
 */
@Data
@Accessors(chain = true)
public class SysMenuTreeDto extends BaseTreeNode<SysMenuTreeDto> {

    /**
     * 本身id， BaseTreeNode中的id为资源id
     */
    private String rId;

    /**
     * 菜单名称
     */
    private String name;
    /**
     * 图标
     */
    private String icon;
    /**
     * router路径
     */
    private String path;
    /**
     * VUE页面
     */
    private String component;
    /**
     * 是否显示，Yes是，No否
     */
    @JSONField(serialzeFeatures = SerializerFeature.WriteEnumUsingToString)
    private YesOrNoEnum isShow;
    /**
     * 是否在引导页显示，Yes是，No否
     */
    @JSONField(serialzeFeatures = SerializerFeature.WriteEnumUsingToString)
    private YesOrNoEnum isGuide;
    /**
     * 是否是主页，Yes是，No否
     */
    @JSONField(serialzeFeatures = SerializerFeature.WriteEnumUsingToString)
    private YesOrNoEnum isIndex;
    /**
     * 菜单路由方式，Home，Screen
     */
    @JSONField(serialzeFeatures = SerializerFeature.WriteEnumUsingToString)
    private MenuShowTypeEnums showType;
    /**
     * 排序值
     */
    private Integer sort;

    /**
     * 将menu tree dto转换为menu dto信息
     * @return menu dto
     */
    public SysMenuDto converToDto(){
        SysMenuDto dto = new SysMenuDto();
        BeanUtils.copyProperties(this, dto);
        dto.setRId(this.id).setId(this.rId);
        return dto;
    }

    /**
     * clone
     * @return SysMenuTreeDto
     */
    @Override
    public SysMenuTreeDto clone() {
        SysMenuTreeDto menuTree = super.clone();
        menuTree.setChildren(new ArrayList<>());

        return menuTree;
    }
}
