package com.wang.jmonkey.modules.sys.service;

import com.wang.jmonkey.modules.sys.model.entity.SysDept;
import com.wang.jmonkey.modules.sys.model.entity.SysUserDept;
import com.baomidou.mybatisplus.service.IService;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 用户部门表 服务类
 * </p>
 *
 * @author HeJiawang
 * @since 2018-12-21
 */
public interface ISysUserDeptService extends IService<SysUserDept> {

    /**
     * 根据部门id，删除部门用户关联关系
     * @param deptId 部门id
     * @return
     */
    boolean deleteByDeptId(Serializable deptId);

    /**
     * 根据用户id，删除部门用户关联关系
     * @param userId 用户id
     * @return
     */
    boolean deleteByUserId(Serializable userId);

    /**
     * merge 用户部门关联关系信息
     * @param userId 用户id
     * @param deptIds 部门 id 集合
     * @return
     */
    boolean mergeDepts(String userId, List<String> deptIds);

    /**
     * 根据用户id获取用户部门信息
     * @param userId 用户id
     * @return 部门信息list
     */
    List<SysDept> selectDeptByUserId(String userId);
}
