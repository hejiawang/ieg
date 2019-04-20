package com.wang.jmonkey.modules.sys.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.wang.jmonkey.common.model.vo.UserVo;
import com.wang.jmonkey.modules.sys.model.dto.SysUserDto;
import com.wang.jmonkey.modules.sys.model.dto.SysUserInfoDto;
import com.wang.jmonkey.modules.sys.model.entity.SysUser;
import com.baomidou.mybatisplus.service.IService;
import com.wang.jmonkey.modules.sys.model.param.SysUserParam;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author HeJiawang
 * @since 2018-12-07
 */
public interface ISysUserService extends IService<SysUser> {

    /**
     * 获取用户分页信息
     * @param page 分页信息
     * @param param 分页参数
     * @return 分页信息
     */
    Page<SysUserDto> selectPage(Page<SysUserDto> page, SysUserParam param );

    /**
     * 保存用户信息
     * @param userParam 用户param
     * @return 是否保存成功
     */
    Boolean save(SysUserParam userParam);

    /**
     * 修改用户信息
     * @param userParam 用户param
     * @return 是否修改成功
     */
    Boolean modify(SysUserParam userParam);

    /**
     * 获取用户dto信息
     * @param id 用户id
     * @return 用户dto信息
     */
    SysUserDto selectDtoById(Serializable id);

    /**
     * 校验用户登录名是否重复
     * @param sysUser 用户登录名信息
     * @return true 用户名已存在
     */
    Boolean checkUsername(SysUser sysUser);

    /**
     * 修改用户登录密码
     * @param user 用户id以及新的密码
     * @return 修改密码是否成功
     */
    Boolean modifyPassword(SysUser user);

    /**
     * 根据登陆用户名获取用户登陆信息
     * @param username 登陆用户名
     * @return 用户登陆信息
     */
    UserVo loadUserByUsername(String username);

    /**
     * 根据用户登陆名称获取用户信息
     * @return userinfo
     */
    SysUserInfoDto getCurrentUserInfo();

    /**
     * 根据用户名称获取用户信息
     * @param username 用户登录名称
     * @return 用户信息
     */
    SysUser selectByUsername(String username);

    /**
     * 获取所有用户信息
     * @return List<SysUser>
     */
    List<SysUser> selectAll();

    /**
     * 获取登录错误次数
     * @param userName userName
     * @return 登录错误次数
     */
    Integer loginErrorNum(String userName);
}
