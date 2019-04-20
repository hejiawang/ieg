package com.wang.jmonkey.modules.sys.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.wang.jmonkey.common.utils.TreeUtil;
import com.wang.jmonkey.common.utils.UserUtils;
import com.wang.jmonkey.modules.sys.model.dto.SysMenuDto;
import com.wang.jmonkey.modules.sys.model.dto.SysMenuTreeDto;
import com.wang.jmonkey.modules.sys.model.entity.SysMenu;
import com.wang.jmonkey.modules.sys.mapper.SysMenuMapper;
import com.wang.jmonkey.modules.sys.model.entity.SysResource;
import com.wang.jmonkey.modules.sys.model.enums.ResourceTypeEnums;
import com.wang.jmonkey.modules.sys.model.param.SysMenuParam;
import com.wang.jmonkey.modules.sys.service.ISysMenuService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.wang.jmonkey.modules.sys.service.ISysResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 菜单权限表 服务实现类
 * </p>
 *
 * @author HeJiawang
 * @since 2018-12-11
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements ISysMenuService {

    @Autowired
    private SysMenuMapper mapper;

    @Autowired
    private ISysResourceService resourceService;

    /**
     * 构建菜单的树形结构
     * @param pId 上级资源id
     * @return
     */
    @Override
    public List<SysMenuTreeDto> treeList(String pId) {
        return  TreeUtil.bulid( mapper.selectTreeDtoList(null), pId );
    }

    /**
     * 获取所有菜单dto信息
     * @return 菜单dto信息
     */
    @Override
    public List<SysMenuTreeDto> selectTreeDtoList() {
        return mapper.selectTreeDtoList(null);
    }

    /**
     * 获取当前用户已授权的菜单信息
     * @return 菜单树信息
     */
    @Override
    public List<SysMenuTreeDto> selectCurrentMenuList() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        return mapper.selectTreeDtoList(UserUtils.getRole(request));
    }

    /**
     * 增加菜单信息，并增加菜单资源
     * @param param
     * @return
     */
    @Transactional
    @Override
    public boolean insert(SysMenuParam param) {
        SysMenu menu = param.converToEntity();
        super.insert(menu);

        return resourceService.insert(
                new SysResource().setRId(menu.getId()).setParentId(param.getParentId()).setType(ResourceTypeEnums.Menu)
        );
    }

    /**
     * 修改菜单信息
     * @param param 菜单信息
     * @return
     */
    @Transactional
    @Override
    public boolean updateById(SysMenuParam param) {
        // 修改菜单信息
        SysMenu menu = param.converToEntity();
        super.updateById(menu);

        // 修改菜单资源父子结构信息
        EntityWrapper<SysResource> wrapper = new EntityWrapper<>();
        wrapper.setEntity(new SysResource().setRId(menu.getId()));
        SysResource resource = resourceService.selectOne(wrapper).setParentId(param.getParentId());
        return resourceService.updateById(resource);
    }

    /**
     * 获取菜单信息
     * @param id 菜单信息id
     * @return
     */
    @Override
    public SysMenuDto selectDtoById(Serializable id) {
        return mapper.selectDtoById(id);
    }

    /**
     * 校验菜单路径是否重复
     * @param sysMenu sysMenu
     * @return true 重复
     */
    @Override
    public Boolean checkPath(SysMenu sysMenu) {
        return mapper.checkPath(sysMenu) > 0;
    }

    /**
     * 删除菜单信息，并删除该菜单的资源信息
     * @param id 菜单信息id
     * @return
     */
    @Transactional
    @Override
    public boolean deleteById(Serializable id) {
        return super.deleteById(id) && resourceService.deleteByRId(id);
    }

}
