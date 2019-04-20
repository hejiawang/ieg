package com.wang.jmonkey.modules.sys.service.impl;

import com.wang.jmonkey.common.utils.UserUtils;
import com.wang.jmonkey.modules.sys.model.entity.SysButton;
import com.wang.jmonkey.modules.sys.model.entity.SysRole;
import com.wang.jmonkey.modules.sys.model.entity.SysRoleResource;
import com.wang.jmonkey.modules.sys.mapper.SysRoleResourceMapper;
import com.wang.jmonkey.modules.sys.service.ISysRoleResourceService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.xiaoleilu.hutool.collection.CollectionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * 角色资源表 服务实现类
 * </p>
 *
 * @author HeJiawang
 * @since 2019-01-04
 */
@Service
public class SysRoleResourceServiceImpl extends ServiceImpl<SysRoleResourceMapper, SysRoleResource> implements ISysRoleResourceService {

    @Autowired
    private SysRoleResourceMapper mapper;

    /**
     * 获取角色以授权的资源id
     * @param roleId 角色id
     * @return
     */
    @Override
    public List<String> findRidByRole(String roleId) {
        return mapper.findRidByRole(roleId);
    }

    /**
     * 获取当前登录用户的所有有权限的资源id
     * @return 资源id集合
     */
    @Override
    public List<String> findRIdByCurrentUser() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        return mapper.findRIdByRoleCodes(UserUtils.getRole(request));
    }

    /**
     * 为角色授权
     * @param roleId 角色id
     * @param rIds 资源id集合
     * @return
     */
    @CacheEvict(value = "auth_permission", allEntries = true)
    @Transactional
    @Override
    public Boolean auth(String roleId, List<String> rIds) {
        mapper.deleteByRole(roleId);

        if(CollectionUtil.isNotEmpty(rIds)){
            rIds.forEach( rId ->
                super.insert(
                        new SysRoleResource().setRoleId(roleId).setResourceId(rId)
                )
            );
        }
        return true;
    }

    /**
     * 删除角色的授权资源
     * @param roleId 角色id
     * @return
     */
    @Override
    public boolean deleteByRole(String roleId) {
        return mapper.deleteByRole(roleId) >= 0;
    }

    /**
     * 删除角色的授权资源
     * @param rId 资源id
     * @return
     */
    @Override
    public boolean deleteByRid(String rId) {
        return mapper.deleteByRid(rId) >= 0;
    }

    /**
     * 获取角色的权限标识
     * @param roleList 角色list
     * @return 权限标识
     */
    @Override
    public List<String> selectPermissionByRoles(List<SysRole> roleList) {
        return CollectionUtil.isEmpty(roleList) ? null : mapper.selectPermissionByRoles(roleList);
    }

    /**
     * 获取角色授权的访问路径信息
     * @param roleCode 角色编码
     * @return 访问路径信息list
     */
    @Cacheable(value = "auth_permission", key = "#roleCode  + '_permission'")
    @Override
    public Set<SysButton> selectButtonByRole(String roleCode) {
        return mapper.selectButtonByRole(roleCode);
    }
}
