package com.wang.jmonkey.modules.ieg.model.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.wang.jmonkey.common.model.BaseEntity;

import com.baomidou.mybatisplus.annotations.Version;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 报考指南——顾问维护院校信息的权限
 * </p>
 *
 * @author HeJiawang
 * @since 2019-04-10
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class IegAuth extends BaseEntity<IegAuth> {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @TableId(type = IdType.INPUT)
    private String userId;
    /**
     * 院校ID
     */
    @TableId(type = IdType.INPUT)
    private String schoolId;

    @Override
    protected Serializable pkVal() {
        return this.userId;
    }

}
