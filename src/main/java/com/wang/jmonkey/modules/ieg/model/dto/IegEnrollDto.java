package com.wang.jmonkey.modules.ieg.model.dto;

import com.wang.jmonkey.modules.ieg.model.entity.IegEnroll;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 报考指南——招生录取投档线详细信息
 * </p>
 *
 * @author HeJiawang
 * @since 2019-12-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class IegEnrollDto extends IegEnroll {

    /**
     * 人数
     */
    private Integer number;

    /**
     * 最低分数
     */
    private Double scoreMin;

    /**
     * 最高分数
     */
    private Double scoreMax;
}
