package com.wang.jmonkey.modules.sys.service;

import com.wang.jmonkey.modules.sys.model.dto.SysDeptTreeDto;
import com.wang.jmonkey.modules.sys.model.dto.SysDeptUserDto;
import com.wang.jmonkey.modules.sys.model.entity.SysDept;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 部门信息 服务类
 * </p>
 *
 * @author HeJiawang
 * @since 2018-12-20
 */
public interface ISysDeptService extends IService<SysDept> {

    /**
     * 获取部门树形数据
     * @return
     */
    List<SysDeptTreeDto> tree();

    /**
     * 校验部门编码是否存在
     * @param sysDept
     * @return
     */
    Boolean checkCode(SysDept sysDept);

    /**
     * 部门中有哪些用户
     * @return SysDeptUserDto
     */
    List<SysDeptUserDto> deptUserList();
}
