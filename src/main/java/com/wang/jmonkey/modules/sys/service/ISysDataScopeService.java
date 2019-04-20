package com.wang.jmonkey.modules.sys.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.wang.jmonkey.modules.sys.model.dto.SysDataScopeDto;
import com.wang.jmonkey.modules.sys.model.entity.SysDataScope;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 获取数据规则定义 服务类
 * </p>
 *
 * @author HeJiawang
 * @since 2019-03-20
 */
public interface ISysDataScopeService extends IService<SysDataScope> {

    /**
     * 校验名称是否存在
     * @param dataScope
     * @return Boolean
     */
    Boolean checkName(SysDataScope dataScope);

    /**
     * 校验拦截路径是否重复
     * @param dataScope
     * @return Boolean
     */
    Boolean checkUrl(SysDataScope dataScope);

    /**
     * 获取数据规则信息
     * @return List<SysDataScopeDto>
     */
    List<SysDataScopeDto> listDto();

    /**
     * page list
     * @param page
     * @param dataScope
     * @return
     */
    Page<SysDataScope> list(Page<SysDataScope> page, String dataScope);
}
