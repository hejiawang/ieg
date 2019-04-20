package com.wang.jmonkey.common.constant;

/**
 * @Description: 授权类型
 * @Auther: HeJiawang
 * @Date: 2018/6/23
 */
public interface SecurityConstants {

    /**
     * 授权码模式
     */
    String AUTHORIZATION_CODE = "authorization_code";
    /**
     * 密码模式
     */
    String PASSWORD = "password";
    /**
     * 刷新token
     */
    String REFRESH_TOKEN = "refresh_token";

    /**
     * 基础角色
     */
    String BASE_ROLE = "ROLE_BASE";

    /**
     * 超级管理员角色
     */
    String ADMIN_ROLE = "admin";

    /**
     * jwt签名
     */
    String JWT_KEY = "JMONKEY_JWT_KEY";

    /**
     * 资源服务的ID
     */
    String RESOURCE_ID = "JMONKEY_RESOURCE_ID";

    /**
     * token-uservo
     */
    String TOKEN_USER_DETAIL = "token-user-detail";

    /**
     * 项目的license
     */
    String JMONKEY_LICENSE = "made by hejiawang";

    /**
     * 前缀
     */
    String JMONKEY_PREFIX = "jmonkey_";

    /**
     * 默认保存code的前缀
     */
    String DEFAULT_CODE_KEY = "DEFAULT_CODE_KEY";

    /**
     * 默认生成图形验证码过期时间
     */
    int DEFAULT_IMAGE_EXPIRE = 120;

    /**
     * oauth token
     */
    String OAUTH_TOKEN_URL = "/oauth/token";

    /**
     * 图片边框
     */
    String DEFAULT_IMAGE_BORDER = "no";

    /**
     * 边框颜色，合法值： r,g,b (and optional alpha) 或者 white,black,blue.
     */
    String DEFAULT_COLOR_FONT = "black";

    /**
     * 默认图片间隔
     */
    String DEFAULT_CHAR_SPACE = "5";

    /**
     * 默认生成图形验证码宽度
     */
    String DEFAULT_IMAGE_WIDTH = "100";

    /**
     * 默认生成图像验证码高度
     */
    String DEFAULT_IMAGE_HEIGHT = "40";

    /**
     * 验证码文字大小
     */
    String DEFAULT_IMAGE_FONT_SIZE = "30";

    /**
     * 默认生成图形验证码长度
     */
    String DEFAULT_IMAGE_LENGTH = "4";

    /**
     * 登录错误次数过期时间
     */
    int LOIN_CODE_EXPIRE = 300;

    /**
     * 记录登录错误次数的key前缀
     */
    String LOIN_CODE_PREFIX = "jmonkey_login_code_";
}
