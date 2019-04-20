package com.wang.jmonkey.modules.sys.mapper;

import com.wang.jmonkey.modules.sys.model.entity.SysRole;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author HeJiawang
 * @since 2018-12-10
 */
public interface SysRoleMapper extends BaseMapper<SysRole> {

    /**
     * 校验角色code是否存在
     * @param sysRole 角色信息
     * @return 存在个数
     */
    Integer checkCode(SysRole sysRole);

    /**
     * 校验角色名称是否存在
     * @param sysRole 角色信息
     * @return 存在个数
     */
    Integer checkName(SysRole sysRole);
}
