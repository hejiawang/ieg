package com.wang.jmonkey.common.oauth.service.impl;

import com.wang.jmonkey.common.model.vo.UserVo;
import com.wang.jmonkey.modules.sys.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


/**
 * @Description: 授权服务UserDetailService
 * @Auther: HeJiawang
 * @Date: 2018/6/24
 */
@Service("userDetailService")
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private ISysUserService userService;

    /**
     * 用户名登陆
     * @param username 登陆用户名
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserVo userVo = userService.loadUserByUsername(username);
        return new UserDetailsImpl( userVo );
    }
}
