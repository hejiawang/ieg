package com.wang.jmonkey.modules.sys.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.wang.jmonkey.common.model.enums.YesOrNoEnum;
import com.wang.jmonkey.common.utils.TreeUtil;
import com.wang.jmonkey.modules.sys.model.dto.SysMenuDto;
import com.wang.jmonkey.modules.sys.model.dto.SysMenuTreeDto;
import com.wang.jmonkey.modules.sys.model.dto.SysResourceTreeDto;
import com.wang.jmonkey.modules.sys.model.dto.SysSystemDto;
import com.wang.jmonkey.modules.sys.model.entity.SysResource;
import com.wang.jmonkey.modules.sys.mapper.SysResourceMapper;
import com.wang.jmonkey.modules.sys.model.enums.ResourceTypeEnums;
import com.wang.jmonkey.modules.sys.service.*;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.xiaoleilu.hutool.collection.CollectionUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 系统资源表 服务实现类
 * </p>
 *
 * @author HeJiawang
 * @since 2018-12-11
 */
@Service
public class SysResourceServiceImpl extends ServiceImpl<SysResourceMapper, SysResource> implements ISysResourceService {

    @Autowired
    private SysResourceMapper mapper;

    @Autowired
    private ISysRoleResourceService roleResourceService;

    @Autowired
    private ISysSystemService systemService;

    @Autowired
    private ISysMenuService menuService;

    /**
     * 资源类型对应的表名
     */
    private Map<ResourceTypeEnums, String> rMap = new HashMap<ResourceTypeEnums, String>(){
        {
            put(ResourceTypeEnums.Menu, "sys_menu");
            put(ResourceTypeEnums.System, "sys_system");
            put(ResourceTypeEnums.Button, "sys_button");
        }
    };

    /**
     * 删除该资源的资源信息，并将其子节点一并删除
     * @param rId 当前资源id
     * @return
     */
    @Transactional
    @Override
    public boolean deleteByRId(Serializable rId) {
        SysResource resource = super.selectOne(
                new EntityWrapper<>(
                        new SysResource().setRId(String.valueOf(rId))
                )
        );
        return this.deleteResource(resource);
    }

    /**
     * 递归删除资源信息，子节点资源信息
     * @param resource 资源信息
     * @return
     */
    private boolean deleteResource(SysResource resource) {
        // 子节点资源信息
        List<SysResource> rChildren = this.selectList(
                new EntityWrapper<>(
                        new SysResource().setParentId(String.valueOf(resource.getId()))
                )
        );
        // 删除子节点信息
        rChildren.forEach( r -> this.deleteResource(r) );

        // 子节点信息删除后，删除自己
        mapper.deleteResource(rMap.get(resource.getType()), resource.getRId());
        return super.deleteById(resource.getId()) && roleResourceService.deleteByRid(resource.getId());
    }


    /**
     * 系统，菜单，构成的树形结果数据
     * @return
     */
    @Override
    public List<SysResourceTreeDto> smTree() {
        return  TreeUtil.bulid( mapper.selectDtoList(2), null );
    }

    /**
     * 系统，菜单，按钮，构成的树形结果数据
     * @return
     */
    @Override
    public List<SysResourceTreeDto> smbTree() {
        return TreeUtil.bulid( mapper.selectDtoList(3), null );
    }

    /**
     * 根据sys_resource表的id获取资源名称
     * @param rId sys_resource表的id
     * @return
     */
    @Override
    public String findNameByRid(String rId) {
        if( StringUtils.isEmpty(rId) ) return "";

        SysResource resource = super.selectById(rId);
        return mapper.findRName(resource.getRId(), rMap.get(resource.getType()));
    }

    /**
     * 是否需要引导页
     * @return true 需要 false不需要
     */
    @Override
    public boolean haveGuide() {
        return mapper.haveGuide() > 0;
    }

    /**
     * 构建引导页显示系统与菜单信息
     * TODO 思考更优算法
     * TODO TreeUtil.bulid方法的使用，可能还存在未知bug
     * TODO 在构建‘引导页显示的菜单’和‘用户有权限的菜单树信息’时，能够同时进行，使用线程优化
     * @return 系统与菜单信息
     */
    @Override
    public List<SysSystemDto> guideInfo() {
        // 当将当前用户授权的资源id、所有菜单信息以及授权过的菜单信息一次性获取出来,方便遍历系统信息时使用
        List<String> rIdList = roleResourceService.findRIdByCurrentUser();
        List<SysMenuTreeDto> menuTreeDtoList = menuService.selectTreeDtoList(); // 所有菜单信息
        List<SysMenuTreeDto> authMenuList = menuService.selectCurrentMenuList();    // 所有授权过的菜单信息

        // 获取所有系统信息
        List<SysSystemDto> systemList = systemService.selectDtoList();
        systemList.forEach( system -> {
            if(rIdList.contains(system.getRId())) system.setIsAuth(YesOrNoEnum.Yes);    // 设置该系统是否已授权

            // 获取要在引导页显示的菜单
            if ( system.getShowMenu() == YesOrNoEnum.Yes ) {
                {
                    List<SysMenuTreeDto> menuTreeCurrentSystem = TreeUtil.bulid(menuTreeDtoList, system.getRId(), true);  // 归属该系统的菜单树
                    system.setMenuList(this.buildGuideMenu(menuTreeCurrentSystem, rIdList));
                }
            }

            {
                // 用户有权限的菜单树信息
                // TODO 优化方案：在循环外部构建Map<String, List<SysMenuTreeDto>>key为system.getRId()，value为List<SysMenuTreeDto> authMenus
                List<SysMenuTreeDto> authMenus = TreeUtil.bulid(authMenuList, system.getRId(), true);
                system.setAuthMenuList( authMenus );
            }
        });

        return systemList;
    }

    /**
     * 构建在引导页显示的菜单
     * @param menuTreeCurrentSystem 所属系统的菜单树
     * @param rIdList 当前用户所有授权的rid
     * @return 引导页显示的菜单
     */
    private List<SysMenuDto> buildGuideMenu(List<SysMenuTreeDto> menuTreeCurrentSystem, List<String> rIdList){
        List<SysMenuDto> showMenuList = new ArrayList<>();
        this.renderGuideMenu(menuTreeCurrentSystem, showMenuList);

        showMenuList.forEach( sysMenuDto -> {
            if (rIdList.contains(sysMenuDto.getRId())) sysMenuDto.setIsAuth(YesOrNoEnum.Yes);
        });

        return showMenuList;
    }

    /**
     * 根据menuTreeCurrentSystem（系统中所有的菜单树）筛选出showMenuList（将在引导页中显示的菜单信息）
     * @param menuTreeCurrentSystem 系统中所有的菜单树
     * @param showMenuList 将在引导页中显示的菜单信息
     */
    private void renderGuideMenu(List<SysMenuTreeDto> menuTreeCurrentSystem, List<SysMenuDto> showMenuList){
        menuTreeCurrentSystem.forEach( sysMenuTreeDto -> {
            // 递归子节点信息
            if (CollectionUtil.isNotEmpty(sysMenuTreeDto.getChildren()))
                this.renderGuideMenu( sysMenuTreeDto.getChildren(), showMenuList );

            // 如果在引导页中显示，将信息加入showMenuList中
            if (YesOrNoEnum.Yes == sysMenuTreeDto.getIsGuide() )
                showMenuList.add(sysMenuTreeDto.converToDto());
        });
    }

}
