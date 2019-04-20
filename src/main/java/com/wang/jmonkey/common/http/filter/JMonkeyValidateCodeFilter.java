package com.wang.jmonkey.common.http.filter;

import com.wang.jmonkey.common.constant.SecurityConstants;
import com.wang.jmonkey.common.exception.ValidateCodeException;
import com.wang.jmonkey.common.utils.RedisUtil;
import com.xiaoleilu.hutool.util.StrUtil;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description: 校验验证码
 * @Auther: HeJiawang
 * @Date: 2019/3/4
 */
@WebFilter(urlPatterns = "/oauth/token", filterName = "JMonkeyValidateCodeFilter")
public class JMonkeyValidateCodeFilter implements Filter {

    private static final String EXPIRED_CAPTCHA_ERROR = "验证码已过期，请重新获取";

    /**
     * redis util
     */
    @Autowired
    private RedisUtil redisUtil;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException { }

    /**
     * doFilter
     * @param servletRequest servletRequest
     * @param servletResponse servletResponse
     * @param filterChain filterChain
     * @throws IOException IOException
     * @throws ServletException ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        try {
            String username = request.getParameter("username");
            String errorKey = SecurityConstants.LOIN_CODE_PREFIX + username;
            int errorNum = redisUtil.get(errorKey) == null ? 0 : Integer.valueOf(redisUtil.get(errorKey).toString());
            if (errorNum >= 3) checkCode(request);

            filterChain.doFilter(servletRequest,servletResponse);
        } catch (ValidateCodeException e) {
            response.setStatus(478);
        }
    }

    @Override
    public void destroy() { }

    private void checkCode( HttpServletRequest httpServletRequest ) throws ValidateCodeException {
        String code = httpServletRequest.getParameter("code");
        if (StrUtil.isBlank(code)) throw new ValidateCodeException("请输入验证码");

        String key = SecurityConstants.DEFAULT_CODE_KEY + httpServletRequest.getParameter("randomStr");
        if (!redisUtil.hasKey(key))  throw new ValidateCodeException(EXPIRED_CAPTCHA_ERROR);

        Object codeObj = redisUtil.get(key);
        if (codeObj == null) throw new ValidateCodeException(EXPIRED_CAPTCHA_ERROR);

        String saveCode = codeObj.toString();
        if (StrUtil.isBlank(saveCode)) {
            redisUtil.del(key);
            throw new ValidateCodeException(EXPIRED_CAPTCHA_ERROR);
        }

        if (!StrUtil.equals(saveCode, code)) {
            redisUtil.del(key);
            throw new ValidateCodeException("验证码错误，请重新输入");
        }

        redisUtil.del(key);
    }
}
