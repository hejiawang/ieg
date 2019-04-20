package com.wang.jmonkey.modules.sys.mapper;

import com.wang.jmonkey.modules.sys.model.entity.SysRole;
import com.wang.jmonkey.modules.sys.model.entity.SysUserRole;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 用户角色表 Mapper 接口
 * </p>
 *
 * @author HeJiawang
 * @since 2018-12-21
 */
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {

    /**
     * 根据角色id删除用户角色关联关系
     * @param roleId 角色id
     * @return
     */
    int deleteByRoleId(@Param("roleId") Serializable roleId);

    /**
     * 根据用户id删除用户角色关联关系
     * @param userId 用户id
     * @return
     */
    int deleteByUserId(@Param("userId") Serializable userId);

    /**
     * 获取用户角色信息
     * @param userId 用户id
     * @return 角色信息list
     */
    List<SysRole> selectRoleByUserId(@Param("userId") String userId);
}
