package com.wang.jmonkey.modules.sys.model.enums;

import com.baomidou.mybatisplus.enums.IEnum;
import com.wang.jmonkey.common.model.BaseEnum;

/**
 * @Description: 菜单路由方式
 * @Auther: HeJiawang
 * @Date: 2018/12/10
 */
public enum MenuShowTypeEnums implements BaseEnum {

    Home("Home", "Home页"),
    Screen("Screen", "独立页");

    private String value;
    private String desc;

    MenuShowTypeEnums(final String value, final String desc) {
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
