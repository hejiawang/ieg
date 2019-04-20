package com.wang.jmonkey.modules.sys.service;

import com.wang.jmonkey.modules.sys.model.dto.SysResourceTreeDto;
import com.wang.jmonkey.modules.sys.model.dto.SysSystemDto;
import com.wang.jmonkey.modules.sys.model.entity.SysResource;
import com.baomidou.mybatisplus.service.IService;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 系统资源表 服务类
 * </p>
 *
 * @author HeJiawang
 * @since 2018-12-11
 */
public interface ISysResourceService extends IService<SysResource> {

    /**
     * 删除该资源的资源信息，并将其子节点一并删除
     * @param rId
     * @return
     */
    boolean deleteByRId(Serializable rId);

    /**
     * 系统，菜单，构成的树形结果数据
     * @return
     */
    List<SysResourceTreeDto> smTree();

    /**
     * 系统，菜单，按钮，构成的树形结果数据
     * @return
     */
    List<SysResourceTreeDto> smbTree();

    /**
     * 根据sys_resource表的id获取资源名称
     * @param rId sys_resource表的id
     * @return
     */
    String findNameByRid(String rId);

    /**
     * 是否需要引导页
     * @return true 需要 false不需要
     */
    boolean haveGuide();

    /**
     * 构建引导页显示系统与菜单信息
     * @return 系统与菜单信息
     */
    List<SysSystemDto> guideInfo();
}
