package com.wang.jmonkey.common.http.config;

import com.wang.jmonkey.modules.sys.service.ISysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

/**
 * @Description: WebMvcConfig TODO WebMvcConfigurerAdapter过时了
 * @Auther: HeJiawang
 * @Date: 2019/1/10
 */
@Slf4j
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    /**
     * cacheManager
     */
    @Autowired
    private CacheManager cacheManager;

    /**
     * userService
     */
    @Autowired
    private ISysUserService userService;

    /**
     * 参数转换
     * @param argumentResolvers
     */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new TokenArgumentResolverConfig(cacheManager, userService));
    }
}
