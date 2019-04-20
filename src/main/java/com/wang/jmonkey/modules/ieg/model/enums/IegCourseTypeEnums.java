package com.wang.jmonkey.modules.ieg.model.enums;

import com.wang.jmonkey.common.model.BaseEnum;

/**
 * @Description: 一分一段表类型
 * @Auther: HeJiawang
 * @Date: 2019/4/2
 */
public enum IegCourseTypeEnums implements BaseEnum {

    W("W", "文科"),
    L("L", "理科"),
    A("A", "文科/理科");

    private String value;
    private String desc;

    IegCourseTypeEnums(final String value, final String desc) {
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
