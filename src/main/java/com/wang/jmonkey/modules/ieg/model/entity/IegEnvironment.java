package com.wang.jmonkey.modules.ieg.model.entity;

import com.wang.jmonkey.common.model.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 报考指南——当地气候饮食情况
 * </p>
 *
 * @author HeJiawang
 * @since 2019-04-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class IegEnvironment extends BaseEntity<IegEnvironment> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private String id;
    /**
     * 院校所在地——省
     */
    private String areaProvince;
    /**
     * 院校所在地——市
     */
    private String areaCity;
    /**
     * 简介
     */
    private String describe;
    /**
     * 信息来源
     */
    private String source;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
