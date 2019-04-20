package com.wang.jmonkey.common.oauth.Handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wang.jmonkey.common.constant.CommonConstant;
import com.wang.jmonkey.common.exception.DeniedException;
import com.wang.jmonkey.common.http.result.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Description: 授权拒绝处理器，覆盖默认的OAuth2AccessDeniedHandler
 * @Auther: HeJiawang
 * @Date: 2018/6/24
 */
@Component
public class JMonkeyAccessDeniedHandler extends OAuth2AccessDeniedHandler {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException authException) throws IOException, ServletException {
        response.setCharacterEncoding(CommonConstant.UTF8);
        response.setContentType(CommonConstant.CONTENT_TYPE);
        response.setStatus(403);

        HttpResult<String> result = new HttpResult<>(new DeniedException("授权失败，禁止访问"));
        PrintWriter printWriter = response.getWriter();
        printWriter.append(objectMapper.writeValueAsString(result));
    }
}
