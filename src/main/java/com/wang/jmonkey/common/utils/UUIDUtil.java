package com.wang.jmonkey.common.utils;

import java.util.UUID;

/**
 * @Description: 生成UUID字符串工具类
 * @Auther: HeJiawang
 * @Date: 2018/12/28
 */
public class UUIDUtil {

    public static String value(){
        return UUID.randomUUID().toString().replace("-", "");
    }
}
