package com.wang.jmonkey.modules.sys.service;

import com.wang.jmonkey.modules.sys.model.entity.SysRole;
import com.wang.jmonkey.modules.sys.model.entity.SysUserRole;
import com.baomidou.mybatisplus.service.IService;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 用户角色表 服务类
 * </p>
 *
 * @author HeJiawang
 * @since 2018-12-21
 */
public interface ISysUserRoleService extends IService<SysUserRole> {

    /**
     * 根据角色id删除用户角色关联关系
     * @param roleId 角色id
     * @return
     */
    boolean deleteByRoleId(Serializable roleId);

    /**
     * 根据用户id删除用户角色关联关系
     * @param userId 用户id
     * @return
     */
    boolean deleteByUserId(Serializable userId);

    /**
     * merger用户角色关联信息
     * @param userId 用户id
     * @param roleIds 角色id集合
     * @return
     */
    boolean mergeRoles(String userId, List<String> roleIds);

    /**
     * 获取用户角色信息
     * @param userId 用户id
     * @return 角色信息list
     */
    List<SysRole> selectRoleByUserId(String userId);
}
