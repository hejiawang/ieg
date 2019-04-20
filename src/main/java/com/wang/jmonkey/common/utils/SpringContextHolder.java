package com.wang.jmonkey.common.utils;

import org.apache.commons.lang3.Validate;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @Description: 以静态变量保存Spring ApplicationContext, 可在任何代码任何地方任何时候取出ApplicaitonContext.
 * @Auther: HeJiawang
 * @Date: 2019-01-24
 */
@Component
public class SpringContextHolder implements ApplicationContextAware, DisposableBean {

    /**
     * applicationContext
     */
    private static ApplicationContext applicationContext = null;

    /**
     * 实现ApplicationContextAware接口, 注入Context到静态变量中.
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if(SpringContextHolder.applicationContext == null) SpringContextHolder.applicationContext = applicationContext;
    }

    /**
     * 实现DisposableBean接口, 在Context关闭时清理静态变量.
     */
    @Override
    public void destroy() throws Exception {
        applicationContext = null;
    }

    /**
     * 校验applicationContext是否存在
     */
    private static void assertContextInjected() {
        Validate.validState(applicationContext != null, "SpringContextHolder.applicaitonContext属性未注入");
    }

    /**
     * 根据spring容器中的名称获取实例
     * @param name
     * @param <T>
     * @return
     */
    public static <T> T getBean(String name) {
        assertContextInjected();

        return (T) applicationContext.getBean(name);
    }

    /**
     * 根据类获取spring容器中的实例
     * @param requiredType
     * @param <T>
     * @return
     */
    public static <T> T getBean(Class<T> requiredType) {
        assertContextInjected();

        return applicationContext.getBean(requiredType);
    }
}
