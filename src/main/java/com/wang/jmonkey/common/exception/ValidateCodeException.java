package com.wang.jmonkey.common.exception;

/**
 * @Description: 验证码校验失败异常
 * @Auther: HeJiawang
 * @Date: 2018/6/26
 */
public class ValidateCodeException extends Exception {

    public ValidateCodeException(String msg) {
        super(msg);
    }
}
