package com.wang.jmonkey.modules.ieg.model.dto;

import com.wang.jmonkey.modules.ieg.model.entity.IegMajor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.beans.BeanUtils;

/**
 * 报考指南——专业Dto信息
 * @author heJiawang
 * @since 2019-03-24
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class IegMajorDto extends IegMajor {

    /**
     * 专业所属门类名称
     */
    private String oneName;

    /**
     * 专业所属学科名称
     */
    private String twoName;

    public static IegMajorDto converFromEntity(IegMajor major) {
        IegMajorDto majorDto = new IegMajorDto();
        BeanUtils.copyProperties(major, majorDto);

        return majorDto;
    }
}
