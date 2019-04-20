package com.wang.jmonkey.common.oauth.listener;

import com.wang.jmonkey.common.constant.SecurityConstants;
import com.wang.jmonkey.common.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.stereotype.Component;

/**
 * @Description: 登录失败监听事件
 * @Auther: HeJiawang
 * @Date: 2019/3/1
 */
@Component
public class AuthenticationFailureListener  implements ApplicationListener<AuthenticationFailureBadCredentialsEvent> {

    /**
     * redis util
     */
    @Autowired
    private RedisUtil redisUtil;

    /**
     * 记录登录错误的次数
     * @param e AuthenticationFailureBadCredentialsEvent
     */
    @Override
    public void onApplicationEvent(AuthenticationFailureBadCredentialsEvent e) {
        if (e.getAuthentication().getPrincipal() instanceof String) {
            String userName = e.getAuthentication().getPrincipal().toString();
            String key = SecurityConstants.LOIN_CODE_PREFIX + userName;

            int loginNum = redisUtil.get(key) == null ? 0 : Integer.valueOf(redisUtil.get(key).toString());
            redisUtil.set(key, loginNum + 1, SecurityConstants.LOIN_CODE_EXPIRE);
        }
    }
}
