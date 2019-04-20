package com.wang.jmonkey.modules.sys.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.wang.jmonkey.modules.sys.model.entity.SysDataScopeRule;
import com.baomidou.mybatisplus.service.IService;

import java.io.Serializable;

/**
 * <p>
 * 数据规则 服务类
 * </p>
 *
 * @author HeJiawang
 * @since 2019-03-20
 */
public interface ISysDataScopeRuleService extends IService<SysDataScopeRule> {

    /**
     * 分页查询信息
     * @param page page
     * @param entity 实体信息
     * @return
     */
    Page<SysDataScopeRule> selectPageList(Page<SysDataScopeRule> page, SysDataScopeRule entity);

    /**
     * 根据数据规则id删除数据规则定义信息
     * @param id 数据规则id
     * @return true
     */
    boolean deleteByScopeId(Serializable id);
}
