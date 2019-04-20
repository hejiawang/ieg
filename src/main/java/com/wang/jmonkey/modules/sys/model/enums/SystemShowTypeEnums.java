package com.wang.jmonkey.modules.sys.model.enums;

import com.wang.jmonkey.common.model.BaseEnum;

/**
 * @Description: 系统显示方式
 * @Auther: HeJiawang
 * @Date: 2018/12/10
 */
public enum SystemShowTypeEnums implements BaseEnum {

    Tabs("Tabs", "标签页"),
    Breadcrumb("Breadcrumb", "导航条");

    private String value;
    private String desc;

    SystemShowTypeEnums(final String value, final String desc) {
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
