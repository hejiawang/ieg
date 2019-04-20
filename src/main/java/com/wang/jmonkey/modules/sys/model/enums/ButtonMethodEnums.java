package com.wang.jmonkey.modules.sys.model.enums;

import com.wang.jmonkey.common.model.BaseEnum;

/**
 * @Description: 按钮请求方式
 * @Auther: HeJiawang
 * @Date: 2018/12/10
 */
public enum ButtonMethodEnums implements BaseEnum {

    Get("Get", "Get"),
    Post("Post", "Post"),
    Put("Put", "Put"),
    Delete("Delete", "Delete");

    private String value;
    private String desc;

    ButtonMethodEnums(final String value, final String desc) {
        this.value = value;
        this.desc = desc;
    }

    @Override
    public String getValue() {
        return this.value;
    }

    @Override
    public String getDesc(){
        return this.desc;
    }
}
