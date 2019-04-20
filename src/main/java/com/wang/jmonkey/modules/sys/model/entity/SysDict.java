package com.wang.jmonkey.modules.sys.model.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.wang.jmonkey.common.model.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 字典值
 * </p>
 *
 * @author HeJiawang
 * @since 2018-12-17
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class SysDict extends BaseEntity<SysDict> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.UUID)
    private String id;
    /**
     * 上级字典ID
     */
    private String parentId;
    /**
     * 字典标签
     */
    private String lable;
    /**
     * 字典键值
     */
    private String value;
    /**
     * 排序值
     */
    private Integer sort;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
