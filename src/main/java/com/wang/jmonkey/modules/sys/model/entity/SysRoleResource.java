package com.wang.jmonkey.modules.sys.model.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.wang.jmonkey.common.model.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 角色资源表
 * </p>
 *
 * @author HeJiawang
 * @since 2019-01-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class SysRoleResource extends BaseEntity<SysRoleResource> {

    private static final long serialVersionUID = 1L;

    /**
     * 角色ID
     */
    @TableId(type = IdType.INPUT)
    private String roleId;
    /**
     * 资源ID
     */
    @TableId(type = IdType.INPUT)
    private String resourceId;


    @Override
    protected Serializable pkVal() {
        return this.roleId + this.resourceId;
    }

}
