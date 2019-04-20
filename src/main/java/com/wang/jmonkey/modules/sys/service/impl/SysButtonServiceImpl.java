package com.wang.jmonkey.modules.sys.service.impl;

import com.wang.jmonkey.modules.sys.model.entity.SysButton;
import com.wang.jmonkey.modules.sys.mapper.SysButtonMapper;
import com.wang.jmonkey.modules.sys.model.entity.SysResource;
import com.wang.jmonkey.modules.sys.model.enums.ResourceTypeEnums;
import com.wang.jmonkey.modules.sys.model.param.SysButtonParam;
import com.wang.jmonkey.modules.sys.service.ISysButtonService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.wang.jmonkey.modules.sys.service.ISysResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 按钮权限表 服务实现类
 * </p>
 *
 * @author HeJiawang
 * @since 2018-12-11
 */
@Service
public class SysButtonServiceImpl extends ServiceImpl<SysButtonMapper, SysButton> implements ISysButtonService {

    @Autowired
    private SysButtonMapper mapper;

    @Autowired
    private ISysResourceService resourceService;

    /**
     * 新增按钮
     * @param param 按钮信息
     * @return
     */
    @Override
    public boolean insert(SysButtonParam param) {
        SysButton button = param.converToEntity();
        super.insert(button);

        return resourceService.insert(
                new SysResource().setRId(button.getId()).setParentId(param.getParentId()).setType(ResourceTypeEnums.Button)
        );
    }

    /**
     * 查询list信息
     * @param parentId 父资源id
     * @return
     */
    @Override
    public List<SysButton> selectListByParent(String parentId) {
        return mapper.selectListByParent(parentId);
    }

    /**
     * 删除按钮信息，并删除该按钮的资源信息
     * @param id 按钮信息id
     * @return
     */
    @CacheEvict(value = "auth_permission", allEntries = true)
    @Transactional
    @Override
    public boolean deleteById(Serializable id) {
        return super.deleteById(id) && resourceService.deleteByRId(id);
    }

    /**
     * 修改按钮信息
     * @param entity 按钮信息
     * @return
     */
    @CacheEvict(value = "auth_permission", allEntries = true)
    @Override
    public boolean updateById(SysButton entity) {
        return super.updateById(entity);
    }
}
