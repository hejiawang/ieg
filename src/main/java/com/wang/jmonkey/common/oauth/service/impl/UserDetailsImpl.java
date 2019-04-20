package com.wang.jmonkey.common.oauth.service.impl;

import com.wang.jmonkey.common.constant.SecurityConstants;
import com.wang.jmonkey.common.model.enums.RecordStatusEnum;
import com.wang.jmonkey.common.model.vo.RoleVo;
import com.wang.jmonkey.common.model.vo.UserVo;
import org.apache.commons.codec.binary.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @Description: 授权用户
 * @Auther: HeJiawang
 * @Date: 2018/6/24
 */
public class UserDetailsImpl implements UserDetails {

    private String username;
    private String password;
    private RecordStatusEnum status;
    private List<RoleVo> roleList;

    public UserDetailsImpl(UserVo userVo) {
        this.username = userVo.getUsername();
        this.password = userVo.getPassword();
        this.status = userVo.getDeleteFlag();
        roleList = userVo.getRoleList();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorityList = new ArrayList<>();
        roleList.forEach( role -> authorityList.add(new SimpleGrantedAuthority(role.getCode())) );
        authorityList.add(new SimpleGrantedAuthority(SecurityConstants.BASE_ROLE));
        return authorityList;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return StringUtils.equals(RecordStatusEnum.Disable.getValue(), status.getValue()) ? false : true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return StringUtils.equals(RecordStatusEnum.Used.getValue(), status.getValue()) ? true : false;
    }

}
