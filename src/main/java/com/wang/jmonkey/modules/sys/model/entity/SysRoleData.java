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
 * 角色数据规则表
 * </p>
 *
 * @author heJiawang
 * @since 2019-03-20
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class SysRoleData extends BaseEntity<SysRoleData> {

    private static final long serialVersionUID = 1L;

    /**
     * 角色ID
     */
    @TableId(type = IdType.INPUT)
    private String roleId;
    /**
     * 数据规则ID   sys_data_scope_rule.id
     */
    @TableId(type = IdType.INPUT)
    private String ruleId;


    @Override
    protected Serializable pkVal() {
        return this.roleId + this.ruleId;
    }

}
