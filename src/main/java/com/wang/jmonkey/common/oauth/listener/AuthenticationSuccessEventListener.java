package com.wang.jmonkey.common.oauth.listener;

import com.wang.jmonkey.common.constant.SecurityConstants;
import com.wang.jmonkey.common.oauth.service.impl.UserDetailsImpl;
import com.wang.jmonkey.common.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

/**
 * @Description: 登录成功监听事件
 * @Auther: HeJiawang
 * @Date: 2019/3/1
 */
@Component
public class AuthenticationSuccessEventListener implements ApplicationListener<AuthenticationSuccessEvent> {

    /**
     * redis util
     */
    @Autowired
    private RedisUtil redisUtil;

    /**
     * 登录成功后清空登录错误次数
     * @param e AuthenticationSuccessEvent
     */
    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent e) {
        if (e.getAuthentication().getPrincipal() instanceof UserDetailsImpl) {
            UserDetailsImpl u = (UserDetailsImpl)e.getAuthentication().getPrincipal();
            String userName = u.getUsername();

            redisUtil.del(SecurityConstants.LOIN_CODE_PREFIX + userName);
        }
    }
}
