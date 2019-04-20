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
 * 用户部门表
 * </p>
 *
 * @author HeJiawang
 * @since 2018-12-21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class SysUserDept extends BaseEntity<SysUserDept> {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @TableId(type = IdType.INPUT)
    private String userId;
    /**
     * 部门ID
     */
    @TableId(type = IdType.INPUT)
    private String deptId;


    @Override
    protected Serializable pkVal() {
        return this.userId + this.deptId;
    }

}
