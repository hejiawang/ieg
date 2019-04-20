package com.wang.jmonkey.modules.sys.model.entity;

import com.wang.jmonkey.common.model.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 数据规则
 * </p>
 *
 * @author HeJiawang
 * @since 2019-03-20
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class SysDataScopeRule extends BaseEntity<SysDataScopeRule> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private String id;
    /**
     * 获取数据规则定义ID
     */
    private String scopeId;
    /**
     * 规则名称
     */
    private String name;
    /**
     * 数据规则控制sql
     */
    private String sqlSegment;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
