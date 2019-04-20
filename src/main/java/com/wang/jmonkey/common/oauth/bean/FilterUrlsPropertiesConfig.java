package com.wang.jmonkey.common.oauth.bean;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lengleng
 * @date 2018/1/9
 */
@Data
@Accessors(chain = true)
@Configuration
@ConditionalOnExpression("!'${jmonkey.urls}'.isEmpty()")
@ConfigurationProperties(prefix = "jmonkey.urls")
public class FilterUrlsPropertiesConfig {

    /**
     * 过滤不需要权限的请求
     */
    private List<String> anon = new ArrayList<>();

    /**
     * 过滤静态资源
     */
    private List<String> resources = new ArrayList<>();
}
