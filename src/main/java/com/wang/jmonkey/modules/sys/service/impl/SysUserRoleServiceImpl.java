package com.wang.jmonkey.modules.sys.service.impl;

import com.wang.jmonkey.modules.sys.model.entity.SysRole;
import com.wang.jmonkey.modules.sys.model.entity.SysUserRole;
import com.wang.jmonkey.modules.sys.mapper.SysUserRoleMapper;
import com.wang.jmonkey.modules.sys.service.ISysUserRoleService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.xiaoleilu.hutool.collection.CollectionUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 用户角色表 服务实现类
 * </p>
 *
 * @author HeJiawang
 * @since 2018-12-21
 */
@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements ISysUserRoleService {

    @Autowired
    private SysUserRoleMapper mapper;

    /**
     * 根据角色id删除用户角色关联关系
     * @param roleId 角色id
     * @return
     */
    @Override
    public boolean deleteByRoleId(Serializable roleId) {
        return mapper.deleteByRoleId(roleId) >= 0;
    }

    /**
     * 根据用户id删除用户角色关联关系
     * @param userId 用户id
     * @return
     */
    @Override
    public boolean deleteByUserId(Serializable userId) {
        return mapper.deleteByUserId(userId) >= 0;
    }

    /**
     * merger用户角色关联信息
     * @param userId 用户id
     * @param roleIds 角色id集合
     * @return
     */
    @Override
    public boolean mergeRoles(String userId, List<String> roleIds) {
        this.deleteByUserId(userId);

        if(CollectionUtil.isNotEmpty(roleIds)){
            roleIds.forEach( roleId ->{
                if(StringUtils.isNotEmpty(roleId))
                    super.insert(
                            new SysUserRole().setUserId(userId).setRoleId(roleId)
                    );
            });
        }

        return true;
    }

    /**
     * 获取用户角色信息
     * @param userId 用户id
     * @return 角色信息list
     */
    @Override
    public List<SysRole> selectRoleByUserId(String userId) {
        return mapper.selectRoleByUserId(userId);
    }
}
