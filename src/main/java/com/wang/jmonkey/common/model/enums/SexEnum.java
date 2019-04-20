package com.wang.jmonkey.common.model.enums;

import com.wang.jmonkey.common.model.BaseEnum;

/**
 * @Description: 性别
 * @Auther: HeJiawang
 * @Date: 2019/3/17
 */
public enum SexEnum implements BaseEnum {

    Man("Man", "男"),
    Woman("Woman", "女"),
    Other("Other", "其他");

    private String value;
    private String desc;

    SexEnum(final String value, final String desc) {
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
