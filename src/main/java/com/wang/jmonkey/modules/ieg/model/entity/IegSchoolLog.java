package com.wang.jmonkey.modules.ieg.model.entity;

import com.wang.jmonkey.common.model.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 报考指南——顾问查看院校详细信息记录
 * </p>
 *
 * @author HeJiawang
 * @since 2019-12-16
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class IegSchoolLog extends BaseEntity<IegSchoolLog> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private String id;
    /**
     * 学校名称
     */
    private String schoolName;
    /**
     * 顾问名称
     */
    private String userName;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
