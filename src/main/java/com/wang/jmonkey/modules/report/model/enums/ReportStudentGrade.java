package com.wang.jmonkey.modules.report.model.enums;

import com.wang.jmonkey.common.model.BaseEnum;

/**
 * @Description: 年级
 * @Auther: HeJiawang
 * @Date: 2019/8/3
 */
public enum ReportStudentGrade implements BaseEnum {

    One("One", "高一"),
    Two("Two", "高二"),
    Three("Three", "高三");

    private String value;
    private String desc;

    ReportStudentGrade(final String value, final String desc) {
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
