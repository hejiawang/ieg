package com.wang.jmonkey.modules.sys.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.wang.jmonkey.modules.sys.model.entity.SysRole;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 *  角色 服务类
 * </p>
 *
 * @author HeJiawang
 * @since 2018-12-10
 */
public interface ISysRoleService extends IService<SysRole> {

    /**
     * 获取角色分页数据
     * @param page 分页信息
     * @param sysRole 查询条件
     * @return 角色分页数据
     */
    Page<SysRole> selectPage(Page<SysRole> page, SysRole sysRole );

    /**
     * 校验角色code是否存在
     * @param sysRole 角色信息
     * @return true 存在
     */
    Boolean checkCode(SysRole sysRole);

    /**
     * 校验角色名称是否存在
     * @param sysRole 角色信息
     * @return true 存在
     */
    Boolean checkName(SysRole sysRole);

    /**
     * 获取所有角色信息
     * @return
     */
    List<SysRole> listAll();
}
