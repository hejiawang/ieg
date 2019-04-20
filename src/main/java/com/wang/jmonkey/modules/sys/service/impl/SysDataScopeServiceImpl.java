package com.wang.jmonkey.modules.sys.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.wang.jmonkey.modules.sys.model.dto.SysDataScopeDto;
import com.wang.jmonkey.modules.sys.model.entity.SysDataScope;
import com.wang.jmonkey.modules.sys.mapper.SysDataScopeMapper;
import com.wang.jmonkey.modules.sys.service.ISysDataScopeRuleService;
import com.wang.jmonkey.modules.sys.service.ISysDataScopeService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.wang.jmonkey.modules.sys.service.ISysRoleDataService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 获取数据规则定义 服务实现类
 * </p>
 *
 * @author HeJiawang
 * @since 2019-03-20
 */
@Service
public class SysDataScopeServiceImpl extends ServiceImpl<SysDataScopeMapper, SysDataScope> implements ISysDataScopeService {

    /**
     * mapper
     */
    @Autowired
    private SysDataScopeMapper mapper;

    /**
     * dataScopeRuleService
     */
    @Autowired
    private ISysDataScopeRuleService dataScopeRuleService;

    /**
     * roleDataService
     */
    @Autowired
    private ISysRoleDataService roleDataService;

    /**
     * Override insert
     * @param entity SysDataScope
     * @return boolean
     */
    @CacheEvict(value = "data_scope_rule", allEntries = true)
    @Override
    public boolean insert(SysDataScope entity) {
        return super.insert(entity);
    }

    /**
     * Override updateById
     * @param entity SysDataScope
     * @return boolean
     */
    @CacheEvict(value = "data_scope_rule", allEntries = true)
    @Override
    public boolean updateById(SysDataScope entity) {
        return super.updateById(entity);
    }

    /**
     * 删除数据规则 数据规则定义 角色授权的数据规则
     * @param id 数据规则
     * @return
     */
    @CacheEvict(value = "data_scope_rule", allEntries = true)
    @Transactional
    @Override
    public boolean deleteById(Serializable id) {
        return roleDataService.deleteByScopeId(id)
                && dataScopeRuleService.deleteByScopeId(id)
                && super.deleteById(id);
    }

    /**
     * 校验名称是否存在
     * @param dataScope
     * @return Boolean
     */
    @Override
    public Boolean checkName(SysDataScope dataScope) {
        return mapper.checkName(dataScope) > 0;
    }

    /**
     * 校验拦截路径是否重复
     * @param dataScope
     * @return Boolean
     */
    @Override
    public Boolean checkUrl(SysDataScope dataScope) {
        return mapper.checkUrl(dataScope) > 0;
    }

    /**
     * 获取数据规则信息
     * @return List<SysDataScopeDto>
     */
    @Override
    public List<SysDataScopeDto> listDto() {
        return mapper.listDto();
    }

    /**
     * page list
     * @param page
     * @param dataScope
     * @return
     */
    @Override
    public Page<SysDataScope> list(Page<SysDataScope> page, String dataScope) {
        EntityWrapper wrapper = new EntityWrapper<SysDataScope>();
        if (!StringUtils.isEmpty(dataScope)) wrapper.orderBy("create_date", "desc".equals(dataScope));

        return super.selectPage(page, wrapper);
    }
}
