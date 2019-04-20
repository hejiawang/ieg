package com.wang.jmonkey.modules.sys.model.enums;

import com.wang.jmonkey.common.model.BaseEnum;

/**
 * @Description: 资源类型
 * @Auther: HeJiawang
 * @Date: 2018/12/10
 */
public enum ResourceTypeEnums implements BaseEnum {

    System("System", "系统"),
    Menu("Menu", "菜单"),
    Button("Button", "按钮");

    private String value;
    private String desc;

    ResourceTypeEnums(final String value, final String desc) {
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
