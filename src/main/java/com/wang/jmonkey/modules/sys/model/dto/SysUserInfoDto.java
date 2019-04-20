package com.wang.jmonkey.modules.sys.model.dto;

import com.wang.jmonkey.modules.sys.model.entity.SysDept;
import com.wang.jmonkey.modules.sys.model.entity.SysRole;
import com.wang.jmonkey.modules.sys.model.entity.SysUser;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @Description: 登录后获取用户信息
 * @Auther: HeJiawang
 * @Date: 2019/1/9
 */
@Data
@Accessors(chain = true)
public class SysUserInfoDto {

    /**
     * 用户基本信息
     */
    private SysUser user;

    /**
     * 用户角色信息
     */
    private List<SysRole> roleList;

    /**
     * 用户部门信息
     */
    private List<SysDept> deptList;

    /**
     * 用户授权的权限标识
     */
    private List<String> permissionList;

    /**
     * 系统中是否需要显示引导页
     */
    private boolean isGuide;

    /**
     * 引导页以及页面头部显示的系统信息
     */
    private List<SysSystemDto> systemList;
}
