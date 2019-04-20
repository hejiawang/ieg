package com.wang.jmonkey.modules.sys.mapper;

import com.wang.jmonkey.modules.sys.model.dto.SysDeptTreeDto;
import com.wang.jmonkey.modules.sys.model.dto.SysDeptUserDto;
import com.wang.jmonkey.modules.sys.model.entity.SysDept;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 部门信息 Mapper 接口
 * </p>
 *
 * @author HeJiawang
 * @since 2018-12-20
 */
public interface SysDeptMapper extends BaseMapper<SysDept> {

    /**
     * 获取所有部门dto数据
     * @return
     */
    List<SysDeptTreeDto> tree();

    /**
     * 校验部门信息是否存在
     * @param sysDept
     * @return
     */
    int checkCode(SysDept sysDept);

    /**
     * 部门中有哪些用户
     * @return SysDeptUserDto
     */
    List<SysDeptUserDto> deptUserList();
}
