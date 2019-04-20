package com.wang.jmonkey.common.http.aop;

import com.wang.jmonkey.common.constant.SecurityConstants;
import com.wang.jmonkey.common.model.vo.UserVo;
import com.wang.jmonkey.common.utils.UserUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @Description: 对api接口层拦截
 * @Auther: HeJiawang
 * @Date: 2019/1/29
 */
@Slf4j(topic="jmonkey.log")
@Aspect
@Component
public class ApiAop {

    /**
     * cacheManager
     */
    @Autowired
    private CacheManager cacheManager;

    /**
     * 是否记录日志信息
     */
    @Value("${jmonkey.log.save:true}")
    private Boolean isSave;

    /**
     * 是否打印日志信息
     */
    @Value("${jmonkey.log.debug:true}")
    private Boolean isDebug;

    /**
     * 拦截规则
     */
    @Pointcut("execution(public com.wang.jmonkey.common.http.result.* *(..))")
    public void pointCutR() { }

    /**
     * 拦截器具体实现
     *
     * @param pjp 切点 所有返回对象R
     * @return R  结果包装
     */
    @Around("pointCutR()")
    public Object methodRHandler(ProceedingJoinPoint pjp) {
        return methodHandler(pjp);
    }

    /**
     * 拦截方法
     * @param pjp
     * @return
     */
    private Object methodHandler(ProceedingJoinPoint pjp) {
        long startTime = System.currentTimeMillis();

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        String token = UserUtils.getToken(request);
        UserVo userVo = null;
        if (StringUtils.isNotEmpty(token))
            userVo = cacheManager.getCache(SecurityConstants.TOKEN_USER_DETAIL).get(token, UserVo.class);

        String username;
        if (userVo == null) {
            username = UserUtils.getUserName(request);
            if (StringUtils.isNotEmpty(username)) UserUtils.setUser(username);
        } else {
            username = userVo.getUsername();
            UserUtils.setUser(username);
        }

        if (isDebug) log.info("———— api start ————————————————————————————————————————");
        Object result;
        try {
            result = pjp.proceed();
        } catch (Throwable e) {
            log.error("异常信息：", e);
            throw new RuntimeException(e);
        } finally {
            if (isDebug) {
                log.info("login username:{}", username);
                log.info("Remote IP : " + request.getHeader("X-Real-IP"));
                log.info("URL : " + request.getRequestURL().toString());
                log.info("HTTP_METHOD : " + request.getMethod());
                log.info("CLASS_METHOD : " + pjp.getSignature().getDeclaringTypeName() + "." + pjp.getSignature().getName());
                log.info("ARGS : " + Arrays.toString(pjp.getArgs()));
                log.info("use time:" + (System.currentTimeMillis() - startTime));
            }

            if (StringUtils.isNotEmpty(username)) UserUtils.clearAllUserInfo();
        }
        if (isDebug) log.info("———— api end —————————————————————————————————————————");

        return result;
    }
}
