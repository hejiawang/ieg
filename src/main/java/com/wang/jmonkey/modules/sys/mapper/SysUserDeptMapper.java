package com.wang.jmonkey.modules.sys.mapper;

import com.wang.jmonkey.modules.sys.model.entity.SysDept;
import com.wang.jmonkey.modules.sys.model.entity.SysUserDept;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 用户部门表 Mapper 接口
 * </p>
 *
 * @author HeJiawang
 * @since 2018-12-21
 */
public interface SysUserDeptMapper extends BaseMapper<SysUserDept> {

    /**
     * 根据部门id，删除部门用户关联关系
     * @param deptId 部门id
     * @return
     */
    int deleteByDeptId(@Param("deptId") Serializable deptId);

    /**
     * 根据用户id，删除部门用户关联关系
     * @param userId 用户id
     * @return
     */
    int deleteByUserId(@Param("userId") Serializable userId);

    /**
     * 根据用户id获取用户部门信息
     * @param userId 用户id
     * @return 部门信息list
     */
    List<SysDept> selectDeptByUserId(@Param("userId") String userId);
}
