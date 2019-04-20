package com.wang.jmonkey.modules.sys.mapper;

import com.wang.jmonkey.modules.sys.model.dto.SysDataScopeDto;
import com.wang.jmonkey.modules.sys.model.entity.SysDataScope;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 获取数据规则定义 Mapper 接口
 * </p>
 *
 * @author HeJiawang
 * @since 2019-03-20
 */
public interface SysDataScopeMapper extends BaseMapper<SysDataScope> {

    /**
     * 校验名称是否存在
     * @param dataScope
     * @return int
     */
    int checkName(SysDataScope dataScope);

    /**
     * 校验拦截路径是否重复
     * @param dataScope
     * @return int
     */
    int checkUrl(SysDataScope dataScope);

    /**
     * 获取数据规则信息
     * @return List<SysDataScopeDto>
     */
    List<SysDataScopeDto> listDto();
}
