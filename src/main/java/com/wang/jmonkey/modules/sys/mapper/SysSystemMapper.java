package com.wang.jmonkey.modules.sys.mapper;

import com.wang.jmonkey.modules.sys.model.dto.SysSystemDto;
import com.wang.jmonkey.modules.sys.model.entity.SysSystem;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 系统配置 Mapper 接口
 * </p>
 *
 * @author HeJiawang
 * @since 2018-12-10
 */
public interface SysSystemMapper extends BaseMapper<SysSystem> {

    /**
     * 获取系统Dto信息
     * @return
     */
    List<SysSystemDto> selectDtoList();
}
