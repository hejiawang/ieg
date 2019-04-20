package com.wang.jmonkey.modules.ieg.model.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.wang.jmonkey.common.model.BaseEntity;

import com.baomidou.mybatisplus.annotations.Version;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 报考指南——投档单位信息
 * </p>
 *
 * @author HeJiawang
 * @since 2019-04-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class IegSchoolSubmit extends BaseEntity<IegSchoolSubmit> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private String id;
    /**
     * 院校ID
     */
    private String schoolId;
    /**
     * 投档单位编码
     */
    private String code;
    /**
     * 投档单位描述
     */
    private String describe;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
