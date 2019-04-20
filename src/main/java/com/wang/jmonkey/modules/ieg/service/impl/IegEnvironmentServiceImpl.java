package com.wang.jmonkey.modules.ieg.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.wang.jmonkey.common.utils.TreeUtil;
import com.wang.jmonkey.modules.ieg.model.dto.IegEnvironmentAreaTreeDto;
import com.wang.jmonkey.modules.ieg.model.entity.IegEnvironment;
import com.wang.jmonkey.modules.ieg.mapper.IegEnvironmentMapper;
import com.wang.jmonkey.modules.ieg.service.IIegEnvironmentService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 报考指南——当地气候饮食情况 服务实现类
 * </p>
 *
 * @author HeJiawang
 * @since 2019-04-03
 */
@Service
public class IegEnvironmentServiceImpl extends ServiceImpl<IegEnvironmentMapper, IegEnvironment> implements IIegEnvironmentService {

    /**
     * mapper
     */
    @Autowired
    private IegEnvironmentMapper mapper;

    /**
     * checkExist
     * @param entity entity
     * @return Boolean
     */
    @Override
    public Boolean checkExist(IegEnvironment entity) {
        return mapper.checkExist(entity) > 0;
    }

    /**
     * 获取地区树形数据
     * @return 地区树形数据
     */
    @Override
    public List<IegEnvironmentAreaTreeDto> areaTree() {
        return TreeUtil.bulid( mapper.tree(), null );
    }

    /**
     * 查找实体信息
     * @param areaCity areaCity
     * @return IegEnvironment
     */
    @Override
    public IegEnvironment findByAreaCity(String areaCity) {
        EntityWrapper<IegEnvironment> wrapper = new EntityWrapper<>();
        wrapper.setEntity(new IegEnvironment().setAreaCity(areaCity));
        return super.selectOne(wrapper);
    }
}
