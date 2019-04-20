package com.wang.jmonkey.modules.ieg.model.enums;

import com.wang.jmonkey.common.model.BaseEnum;

/**
 * @Description: 学历层次 B本科 Z专科 A全部
 * @Auther: HeJiawang
 * @Date: 2019/3/24
 */
public enum IegDegreeTypeEnums implements BaseEnum {

    A("A", "全部"),
    B("B", "本科"),
    Z("Z", "专科");

    private String value;
    private String desc;

    IegDegreeTypeEnums(final String value, final String desc) {
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
