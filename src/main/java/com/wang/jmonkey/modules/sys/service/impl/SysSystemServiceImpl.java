package com.wang.jmonkey.modules.sys.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.wang.jmonkey.modules.sys.model.dto.SysSystemDto;
import com.wang.jmonkey.modules.sys.model.entity.SysResource;
import com.wang.jmonkey.modules.sys.model.entity.SysSystem;
import com.wang.jmonkey.modules.sys.mapper.SysSystemMapper;
import com.wang.jmonkey.modules.sys.model.enums.ResourceTypeEnums;
import com.wang.jmonkey.modules.sys.service.ISysResourceService;
import com.wang.jmonkey.modules.sys.service.ISysSystemService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 系统配置 服务实现类
 * </p>
 *
 * @author HeJiawang
 * @since 2018-12-10
 */
@Service
public class SysSystemServiceImpl extends ServiceImpl<SysSystemMapper, SysSystem> implements ISysSystemService {

    @Autowired
    private SysSystemMapper mapper;

    @Autowired
    private ISysResourceService resourceService;

    /**
     * 获取系统信息列表
     * @return
     */
    @Override
    public List<SysSystemDto> selectDtoList() {
        return mapper.selectDtoList();
    }

    /**
     * 新增系统信息，将系统信息添加至资源表中
     * @param entity 系统信息
     * @return
     */
    @Transactional
    @Override
    public boolean insert(SysSystem entity) {
        super.insert(entity);

        SysResource resource = new SysResource().setRId(entity.getId()).setType(ResourceTypeEnums.System);
        return resourceService.insert(resource);
    }

    /**
     * 删除系统信息，并删除该系统的资源信息
     * @param id 系统信息id
     * @return
     */
    @Transactional
    @Override
    public boolean deleteById(Serializable id) {
        return super.deleteById(id) && resourceService.deleteByRId(id);
    }
}
