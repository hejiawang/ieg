package com.wang.jmonkey.modules.sys.model.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.wang.jmonkey.common.model.BaseEntity;

import com.wang.jmonkey.common.model.enums.YesOrNoEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 定时任务配置表
 * </p>
 *
 * @author HeJiawang
 * @since 2019-01-24
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class SysTask extends BaseEntity<SysTask> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private String id;
    /**
     * 任务名
     */
    private String name;
    /**
     * 任务组
     */
    private String group;
    /**
     * 定时规则
     */
    private String rule;
    /**
     * 启用状态 Yes启用 No停用
     */
    @JSONField(serialzeFeatures = SerializerFeature.WriteEnumUsingToString)
    private YesOrNoEnum status;
    /**
     * 任务类
     */
    private String className;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
