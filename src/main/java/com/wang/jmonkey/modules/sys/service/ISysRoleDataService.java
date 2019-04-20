package com.wang.jmonkey.modules.sys.service;

import com.wang.jmonkey.modules.sys.model.dto.SysRoleDataRuleDto;
import com.wang.jmonkey.modules.sys.model.entity.SysRoleData;
import com.baomidou.mybatisplus.service.IService;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 * 角色数据规则表 服务类
 * </p>
 *
 * @author heJiawang
 * @since 2019-03-20
 */
public interface ISysRoleDataService extends IService<SysRoleData> {

    /**
     * 删除角色授权的数据规则信息
     * @param id 数据规则id
     * @return true
     */
    boolean deleteByScopeId(Serializable id);

    /**
     * 为角色授权数据规则
     * @param roleId roleId
     * @param ruleIds ruleIds
     * @return Boolean
     */
    boolean auth(String roleId, List<String> ruleIds);

    /**
     * 获取角色授权的数据规则
     * @param roleId 角色id
     * @return Map<String, String>
     */
    Map<String, String> findByRole(String roleId);

    /**
     * 获取角色授权的数据规则
     * @param roleCode 角色编码
     * @return 数据规则
     */
    Set<SysRoleDataRuleDto> selectByRole(String roleCode);
}
