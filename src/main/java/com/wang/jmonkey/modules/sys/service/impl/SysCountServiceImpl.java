package com.wang.jmonkey.modules.sys.service.impl;

import com.wang.jmonkey.modules.sys.mapper.SysCountMapper;
import com.wang.jmonkey.modules.sys.model.dto.SysCountDto;
import com.wang.jmonkey.modules.sys.service.ISysCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 系统设置首页统计信息 服务类 impl
 * </p>
 *
 * @author HeJiawang
 * @since 2019-01-21
 */
@Service
public class SysCountServiceImpl implements ISysCountService {

    /**
     * mapper
     */
    @Autowired
    private SysCountMapper mapper;

    /**
     * 系统用户数量、角色数量、部门数量统计
     * @return 用户数量、角色数量、部门数量统计信息
     */
    @Override
    public List<SysCountDto> base() {
        return mapper.base();
    }

    /**
     * 用户角色分布统计
     * @return 统计信息
     */
    @Override
    public List<SysCountDto> userRole() {
        return mapper.userRole();
    }

    /**
     * 用户部门分布统计
     * @return 统计信息
     */
    @Override
    public List<SysCountDto> userDept() {
        return mapper.userDept();
    }
}
