package com.wang.jmonkey.common.oauth.bean;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author HeJiawang
 * @date 2017/10/28
 */
@Data
@Accessors(chain = true)
@Configuration
@ConfigurationProperties(prefix = "jmonkey.auth")
public class AuthServerConfig {

    private String clientId;
    private String clientSecret;
    private String scope;
}
