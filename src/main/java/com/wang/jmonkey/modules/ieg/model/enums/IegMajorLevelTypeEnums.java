package com.wang.jmonkey.modules.ieg.model.enums;

import com.wang.jmonkey.common.model.BaseEnum;

/**
 * @Description: 专业层级分类 One门类 Two学科 Three专业
 * @Auther: HeJiawang
 * @Date: 2019/3/24
 */
public enum IegMajorLevelTypeEnums implements BaseEnum {

    One("One", "门类"),
    Two("Two", "学科"),
    Three("Three", "专业");

    private String value;
    private String desc;

    IegMajorLevelTypeEnums(final String value, final String desc) {
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
