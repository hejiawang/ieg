package com.wang.jmonkey.modules.sys.model.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.wang.jmonkey.common.model.BaseEntity;

import com.wang.jmonkey.common.model.enums.YesOrNoEnum;
import com.wang.jmonkey.modules.sys.model.enums.SystemShowTypeEnums;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 系统配置
 * </p>
 *
 * @author HeJiawang
 * @since 2018-12-10
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class SysSystem extends BaseEntity<SysSystem> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.UUID)
    private String id;
    /**
     * 系统名称
     */
    private String name;
    /**
     * 图标
     */
    private String icon;
    /**
     * 是否显示，Yes是，No否
     */
    @JSONField(serialzeFeatures = SerializerFeature.WriteEnumUsingToString)
    private YesOrNoEnum isShow;
    /**
     * 是否需要引导页，Yes是，No否
     */
    @JSONField(serialzeFeatures = SerializerFeature.WriteEnumUsingToString)
    private YesOrNoEnum showGuide;
    /**
     * 若显示引导页，在引导页是否显示子菜单，Yes是，No否
     */
    @JSONField(serialzeFeatures = SerializerFeature.WriteEnumUsingToString)
    private YesOrNoEnum showMenu;
    /**
     * 系统显示方式，Tabs标签页方式 Breadcrumb导航条方式
     */
    @JSONField(serialzeFeatures = SerializerFeature.WriteEnumUsingToString)
    private SystemShowTypeEnums showType;
    /**
     * 排序值
     */
    private Integer sort;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
