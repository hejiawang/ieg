package com.wang.jmonkey.modules.ieg.mapper;

import com.wang.jmonkey.modules.ieg.model.dto.IegMajorTreeDto;
import com.wang.jmonkey.modules.ieg.model.entity.IegMajor;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 报考指南——专业定义 Mapper 接口
 * </p>
 *
 * @author heJiawang
 * @since 2019-03-24
 */
public interface IegMajorMapper extends BaseMapper<IegMajor> {

    /**
     * 获取树形数据
     * @param major 专业信息
     * @return 专业树
     */
    List<IegMajorTreeDto> tree(IegMajor major);
}
