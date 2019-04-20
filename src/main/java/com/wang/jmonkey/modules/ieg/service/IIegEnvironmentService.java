package com.wang.jmonkey.modules.ieg.service;

import com.wang.jmonkey.modules.ieg.model.dto.IegEnvironmentAreaTreeDto;
import com.wang.jmonkey.modules.ieg.model.entity.IegEnvironment;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 报考指南——当地气候饮食情况 服务类
 * </p>
 *
 * @author HeJiawang
 * @since 2019-04-03
 */
public interface IIegEnvironmentService extends IService<IegEnvironment> {

    /**
     * checkExist
     * @param entity entity
     * @return Boolean
     */
    Boolean checkExist(IegEnvironment entity);

    /**
     * 获取地区树形数据
     * @return 地区树形数据
     */
    List<IegEnvironmentAreaTreeDto> areaTree();

    /**
     * 查找实体信息
     * @param areaCity areaCity
     * @return IegEnvironment
     */
    IegEnvironment findByAreaCity(String areaCity);
}
