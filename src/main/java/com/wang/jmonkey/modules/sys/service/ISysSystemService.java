package com.wang.jmonkey.modules.sys.service;

import com.wang.jmonkey.modules.sys.model.dto.SysSystemDto;
import com.wang.jmonkey.modules.sys.model.entity.SysSystem;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 系统配置 服务类
 * </p>
 *
 * @author HeJiawang
 * @since 2018-12-10
 */
public interface ISysSystemService extends IService<SysSystem> {

    /**
     * 获取系统信息列表
     * @return
     */
    List<SysSystemDto> selectDtoList();
}
