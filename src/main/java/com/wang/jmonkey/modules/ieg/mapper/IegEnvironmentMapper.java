package com.wang.jmonkey.modules.ieg.mapper;

import com.wang.jmonkey.modules.ieg.model.dto.IegEnvironmentAreaTreeDto;
import com.wang.jmonkey.modules.ieg.model.entity.IegEnvironment;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 报考指南——当地气候饮食情况 Mapper 接口
 * </p>
 *
 * @author HeJiawang
 * @since 2019-04-03
 */
public interface IegEnvironmentMapper extends BaseMapper<IegEnvironment> {

    /**
     * checkExist
     * @param entity entity
     * @return int
     */
    int checkExist(IegEnvironment entity);

    /**
     * 获取地区树形数据
     * @return 地区树形数据
     */
    List<IegEnvironmentAreaTreeDto> tree();
}
