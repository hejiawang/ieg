package com.wang.jmonkey.common.oauth.service;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description: 权限 service
 * @Auther: HeJiawang
 * @Date: 2018/6/24
 */
public interface PermissionService {

    /**
     * 判断请求是否有权限
     *
     * @param request HttpServletRequest
     * @param authentication 认证信息
     * @return 是否有权限
     */
    boolean hasPermission(HttpServletRequest request, Authentication authentication);
}
