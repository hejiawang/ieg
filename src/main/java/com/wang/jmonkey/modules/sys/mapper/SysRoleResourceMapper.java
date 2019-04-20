package com.wang.jmonkey.modules.sys.mapper;

import com.wang.jmonkey.modules.sys.model.entity.SysButton;
import com.wang.jmonkey.modules.sys.model.entity.SysRole;
import com.wang.jmonkey.modules.sys.model.entity.SysRoleResource;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 角色资源表 Mapper 接口
 * </p>
 *
 * @author HeJiawang
 * @since 2019-01-04
 */
public interface SysRoleResourceMapper extends BaseMapper<SysRoleResource> {

    /**
     * 获取角色以授权的资源id
     * @param roleId 角色id
     * @return 资源id集合
     */
    List<String> findRidByRole(@Param("roleId") String roleId);

    /**
     * 获取角色以授权的资源id
     * @param roleCodeList 角色code集合
     * @return 资源id集合
     */
    List<String> findRIdByRoleCodes(@Param("roleCodeList") List<String> roleCodeList);

    /**
     * 删除角色授权资源
     * @param roleId 角色id
     * @return
     */
    int deleteByRole(@Param("roleId") String roleId);

    /**
     * 删除角色的授权资源
     * @param rId 资源id
     * @return
     */
    int deleteByRid(@Param("rId") String rId);

    /**
     * 获取角色的权限标识
     * @param roleList 角色list
     * @return 权限标识
     */
    List<String> selectPermissionByRoles(@Param("roleList") List<SysRole> roleList);

    /**
     * 获取角色授权的菜单信息
     * @param roleCode 角色编码
     * @return 菜单信息list
     */
    Set<SysButton> selectButtonByRole(@Param("roleCode") String roleCode);
}
