package com.wang.jmonkey.common.http.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.MultipartConfigElement;

/**
 * @Description: 上传文件时经过路由缓存的文件内容
 * @Auther: HeJiawang
 * @Date: 2018/8/13
 */
@Configuration
public class MultipartConfig {

    @Value("${jmonkey.fileTempMemory}")
    private String fileTempMemory;

    @Bean
    MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setLocation(fileTempMemory);
        return factory.createMultipartConfig();
    }
}
