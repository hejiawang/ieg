package com.wang.jmonkey.modules.sys.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.wang.jmonkey.common.utils.TreeUtil;
import com.wang.jmonkey.modules.sys.model.dto.SysDeptTreeDto;
import com.wang.jmonkey.modules.sys.model.dto.SysDeptUserDto;
import com.wang.jmonkey.modules.sys.model.entity.SysDept;
import com.wang.jmonkey.modules.sys.mapper.SysDeptMapper;
import com.wang.jmonkey.modules.sys.service.ISysDeptService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.wang.jmonkey.modules.sys.service.ISysUserDeptService;
import com.xiaoleilu.hutool.collection.CollectionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 部门信息 服务实现类
 * </p>
 *
 * @author HeJiawang
 * @since 2018-12-20
 */
@Service
public class SysDeptServiceImpl extends ServiceImpl<SysDeptMapper, SysDept> implements ISysDeptService {

    @Autowired
    private SysDeptMapper mapper;

    /**
     * 用户部门信息 service
     */
    @Autowired
    private ISysUserDeptService userDeptService;

    /**
     * 获取部门树形数据
     * @return
     */
    @Override
    public List<SysDeptTreeDto> tree() {
        return  TreeUtil.bulid( mapper.tree(), null );
    }

    /**
     * 校验部门编码是否存在
     * @param sysDept
     * @return
     */
    @Override
    public Boolean checkCode(SysDept sysDept) {
        return mapper.checkCode(sysDept) > 0;
    }

    /**
     * 删除部门以及下级部门信息
     * @param id
     * @return
     */
    @Transactional
    @Override
    public boolean deleteById(Serializable id) {
        // 删除子节点部门信息
        EntityWrapper<SysDept> wrapper = new EntityWrapper<>();
        wrapper.setEntity(new SysDept().setParentId(String.valueOf(id)));
        List<SysDept> deptChildrens = this.selectList(wrapper);
        if(CollectionUtil.isNotEmpty(deptChildrens)){
            deptChildrens.forEach( sysDept -> this.deleteById(sysDept.getId()) );
        }

        // 删除自身信息, 删除部门用户关联关系
        return super.deleteById(id) && userDeptService.deleteByDeptId(id);
    }

    /**
     * 部门中有哪些用户
     * @return SysDeptUserDto
     */
    @Override
    public List<SysDeptUserDto> deptUserList() {
        return mapper.deptUserList();
    }
}
