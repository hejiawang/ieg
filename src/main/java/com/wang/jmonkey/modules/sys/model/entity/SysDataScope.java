package com.wang.jmonkey.modules.sys.model.entity;

import com.wang.jmonkey.common.model.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 获取数据规则定义
 * </p>
 *
 * @author HeJiawang
 * @since 2019-03-20
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class SysDataScope extends BaseEntity<SysDataScope> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private String id;
    /**
     * 规则定义名称
     */
    private String name;
    /**
     * 请求链接
     */
    private String url;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
