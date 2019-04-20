package com.wang.jmonkey.modules.sys.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.wang.jmonkey.modules.sys.model.entity.SysDataScopeRule;
import com.wang.jmonkey.modules.sys.mapper.SysDataScopeRuleMapper;
import com.wang.jmonkey.modules.sys.service.ISysDataScopeRuleService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * <p>
 * 数据规则 服务实现类
 * </p>
 *
 * @author HeJiawang
 * @since 2019-03-20
 */
@Service
public class SysDataScopeRuleServiceImpl extends ServiceImpl<SysDataScopeRuleMapper, SysDataScopeRule> implements ISysDataScopeRuleService {

    /**
     * 分页查询信息
     * @param page page
     * @param entity 实体信息
     * @return
     */
    @Override
    public Page<SysDataScopeRule> selectPageList(Page<SysDataScopeRule> page, SysDataScopeRule entity) {
        EntityWrapper wrapper = new EntityWrapper<SysDataScopeRule>();
        wrapper.setEntity(entity);

        return super.selectPage(page, wrapper);
    }

    /**
     * 根据数据规则id删除数据规则定义信息
     * @param id 数据规则id
     * @return true
     */
    @Override
    public boolean deleteByScopeId(Serializable id) {
        EntityWrapper wrapper = new EntityWrapper<SysDataScopeRule>();
        wrapper.setEntity(new SysDataScopeRule().setScopeId(String.valueOf(id)));
        return super.delete(wrapper);
    }

    /**
     * Override insert
     * @param entity SysDataScopeRule
     * @return boolean
     */
    @CacheEvict(value = "data_scope_rule", allEntries = true)
    @Override
    public boolean insert(SysDataScopeRule entity) {
        return super.insert(entity);
    }

    /**
     * Override deleteById
     * @param id id
     * @return boolean
     */
    @CacheEvict(value = "data_scope_rule", allEntries = true)
    @Override
    public boolean deleteById(Serializable id) {
        return super.deleteById(id);
    }

    /**
     * Override updateById
     * @param entity SysDataScopeRule
     * @return boolean
     */
    @CacheEvict(value = "data_scope_rule", allEntries = true)
    @Override
    public boolean updateById(SysDataScopeRule entity) {
        return super.updateById(entity);
    }
}
