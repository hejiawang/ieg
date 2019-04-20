package com.wang.jmonkey.modules.sys.model.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.wang.jmonkey.common.model.BaseEntity;

import com.wang.jmonkey.modules.sys.model.enums.ButtonMethodEnums;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 按钮权限表
 * </p>
 *
 * @author HeJiawang
 * @since 2018-12-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class SysButton extends BaseEntity<SysButton> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.UUID)
    private String id;
    /**
     * 按钮名称
     */
    private String name;
    /**
     * 权限标识
     */
    private String permission;
    /**
     * 请求链接
     */
    private String url;
    /**
     * 请求方法 Get，Post，Put，Delete
     */
    @JSONField(serialzeFeatures = SerializerFeature.WriteEnumUsingToString)
    private ButtonMethodEnums method;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
