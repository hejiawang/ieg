package com.wang.jmonkey.modules.sys.service;

import com.wang.jmonkey.modules.sys.model.dto.SysCountDto;

import java.util.List;

/**
 * <p>
 * 系统设置首页统计信息 服务类
 * </p>
 *
 * @author HeJiawang
 * @since 2019-01-21
 */
public interface ISysCountService {

    /**
     * 系统用户数量、角色数量、部门数量统计
     * @return 用户数量、角色数量、部门数量统计信息
     */
    List<SysCountDto> base();

    /**
     * 用户角色分布统计
     * @return 统计信息
     */
    List<SysCountDto> userRole();

    /**
     * 用户部门分布统计
     * @return 统计信息
     */
    List<SysCountDto>  userDept();
}
