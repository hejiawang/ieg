package com.wang.jmonkey.modules.sys.model.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.wang.jmonkey.common.model.BaseEntity;

import com.wang.jmonkey.common.model.enums.YesOrNoEnum;
import com.wang.jmonkey.modules.sys.model.enums.MenuShowTypeEnums;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 菜单权限表
 * </p>
 *
 * @author HeJiawang
 * @since 2018-12-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class SysMenu extends BaseEntity<SysMenu> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.UUID)
    private String id;
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

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
