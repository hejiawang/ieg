package com.wang.jmonkey.modules.sys.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.wang.jmonkey.common.utils.TreeUtil;
import com.wang.jmonkey.modules.sys.model.dto.SysDictDto;
import com.wang.jmonkey.modules.sys.model.dto.SysDictTreeDto;
import com.wang.jmonkey.modules.sys.model.entity.SysDict;
import com.wang.jmonkey.modules.sys.mapper.SysDictMapper;
import com.wang.jmonkey.modules.sys.service.ISysDictService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.xiaoleilu.hutool.collection.CollectionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 字典值 服务实现类
 * </p>
 *
 * @author HeJiawang
 * @since 2018-12-17
 */
@Service
public class SysDictServiceImpl extends ServiceImpl<SysDictMapper, SysDict> implements ISysDictService {

    @Autowired
    private SysDictMapper mapper;

    /**
     * 获取树形表格数据
     * @return
     */
    @Override
    public List<SysDictTreeDto> tree() {
        return  TreeUtil.bulid( mapper.tree(), null );
    }

    /**
     * 校验字典键值是否存在
     * @param sysDict
     * @return
     */
    @Override
    public Boolean checkValue(SysDict sysDict) {
        return mapper.checkValue(sysDict) > 0;
    }

    /**
     * 获取字典dto信息
     * @param id 字典id
     * @return
     */
    @Override
    public SysDictDto selectDtoById(Serializable id) {
        return mapper.selectDtoById(id);
    }

    /**
     * 为字典组件赋值
     * 根据父value获取子字典信息
     * @param parentValue 父字典value
     * @return
     */
    @Cacheable(value = "sys_dict", key = "#parentValue  + '_sys_dict'")
    @Override
    public List<SysDict> findChildren(String parentValue) {
        return mapper.findChildren(parentValue);
    }

    /**
     * 删除字典以及子节点字典
     * @param id 字典id
     * @return
     */
    @CacheEvict(value = "sys_dict", allEntries = true)
    @Transactional
    @Override
    public boolean deleteById(Serializable id) {
        // 递归删除子节点字典信息
        EntityWrapper<SysDict> wrapper = new EntityWrapper<>();
        wrapper.setEntity(new SysDict().setParentId(String.valueOf(id)));
        List<SysDict> dictChildrens = this.selectList(wrapper);
        if(CollectionUtil.isNotEmpty(dictChildrens)){
            dictChildrens.forEach( sysDict -> this.deleteById(sysDict.getId()) );
        }

        // 删除本身信息
        return super.deleteById(id);
    }

    /**
     * 新增字典
     * @param entity 字典信息
     * @return
     */
    @CacheEvict(value = "sys_dict", allEntries = true)
    @Override
    public boolean insert(SysDict entity) {
        return super.insert(entity);
    }

    /**
     * 修改字典信息
     * @param entity 字典信息
     * @return
     */
    @CacheEvict(value = "sys_dict", allEntries = true)
    @Override
    public boolean updateById(SysDict entity) {
        return super.updateById(entity);
    }
}
