package com.wang.jmonkey.modules.gauge.model.enums;

import com.wang.jmonkey.common.model.BaseEnum;

/**
 * @Description: 矫心理状况分析与矫正方案生成规则 类型 Gauge量表 Advise建议 Marry婚姻 Age年龄 Edu学历
 * @Auther: HeJiawang
 * @Date: 2019/9/25
 */
public enum GaugeAnswerInfoTypeEnum implements BaseEnum {

    AKS("AKS", "艾克森量表"),
    SCL90("SCL90", "SCL90量表"),
    Quality("Quality", "气质量表"),
    Action("Action", "行为量表");

    private String value;
    private String desc;

    GaugeAnswerInfoTypeEnum(final String value, final String desc) {
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
