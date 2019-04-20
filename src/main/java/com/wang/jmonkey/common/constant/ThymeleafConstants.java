package com.wang.jmonkey.common.constant;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Accessors(chain = true)
@Configuration
@ConditionalOnExpression("!'${jmonkey.thymeleaf}'.isEmpty()")
@ConfigurationProperties(prefix = "jmonkey.thymeleaf")
public class ThymeleafConstants {

    /**
     * 是否使用cnd缓存
     */
    private String staticPath;
}
