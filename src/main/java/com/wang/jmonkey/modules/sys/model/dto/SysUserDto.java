package com.wang.jmonkey.modules.sys.model.dto;

import com.wang.jmonkey.modules.sys.model.entity.SysDept;
import com.wang.jmonkey.modules.sys.model.entity.SysRole;
import com.wang.jmonkey.modules.sys.model.entity.SysUser;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 用户dto信息
 * @Auther: HeJiawang
 * @Date: 2018-12-21
 */
@Data
@Accessors(chain = true)
public class SysUserDto extends SysUser {

    /**
     * 用户归属部门信息集合
     */
    private List<SysDept> depts;

    /**
     * 用户角色信息集合
     */
    private List<SysRole> roles;

    /**
     * 用户归属部门id
     */
    private List<String> deptIds;

    /**
     * 用户角色id集合
     */
    private List<String> roleIds;
}
