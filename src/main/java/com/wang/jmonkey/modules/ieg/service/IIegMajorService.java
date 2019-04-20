package com.wang.jmonkey.modules.ieg.service;

import com.wang.jmonkey.modules.ieg.model.dto.IegMajorDto;
import com.wang.jmonkey.modules.ieg.model.dto.IegMajorTreeDto;
import com.wang.jmonkey.modules.ieg.model.entity.IegMajor;
import com.baomidou.mybatisplus.service.IService;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 报考指南——专业定义 服务类
 * </p>
 *
 * @author heJiawang
 * @since 2019-03-24
 */
public interface IIegMajorService extends IService<IegMajor> {

    /**
     * 获取树形数据
     * @param major 专业信息
     * @return 专业树
     */
    List<IegMajorTreeDto> tree(IegMajor major);

    /**
     * 获取专业信息
     * @param id 专业id
     * @return 专业信息
     */
    IegMajorDto selectDtoById(Serializable id);
}
