package com.wang.jmonkey.modules.sys.mapper;

import com.wang.jmonkey.common.model.vo.UserVo;
import com.wang.jmonkey.modules.sys.model.dto.SysUserDto;
import com.wang.jmonkey.modules.sys.model.entity.SysUser;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.wang.jmonkey.modules.sys.model.param.SysUserParam;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author HeJiawang
 * @since 2018-12-07
 */
public interface SysUserMapper extends BaseMapper<SysUser> {

    /**
     * 获取用户dto信息
     * @param id 用户id
     * @return
     */
    SysUserDto selectDtoById(@Param("id") Serializable id);

    /**
     * 获取用户分页信息
     * @param param
     * @return
     */
    List<SysUserDto> selectDtoPage(SysUserParam param);

    /**
     * 校验用户登录名是否重复
     * @param sysUser 用户登录名信息
     * @return
     */
    Integer checkUsername(SysUser sysUser);

    /**
     * 根据登陆用户名获取用户登陆信息
     * @param username 登陆用户名
     * @return 用户登陆信息
     */
    UserVo loadUserByUsername(@Param("username") String username);
}
