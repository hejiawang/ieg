package com.wang.jmonkey.modules.sys.model.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.wang.jmonkey.common.model.BaseEntity;

import com.wang.jmonkey.modules.sys.model.enums.ResourceTypeEnums;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 系统资源表
 * </p>
 *
 * @author HeJiawang
 * @since 2018-12-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class SysResource extends BaseEntity<SysResource> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.UUID)
    private String id;
    /**
     * 资源类型 System,Menu,Button
     */
    @JSONField(serialzeFeatures = SerializerFeature.WriteEnumUsingToString)
    private ResourceTypeEnums type;
    /**
     * 资源id 例 type==System r_id==sys_system.id
     */
    private String rId;
    /**
     * 父资源ID
     */
    private String parentId;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
