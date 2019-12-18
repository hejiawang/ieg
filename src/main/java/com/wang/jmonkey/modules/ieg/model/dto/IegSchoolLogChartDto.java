package com.wang.jmonkey.modules.ieg.model.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * IegSchoolLogChartDto
 * @author heJiawang
 * @since 2019-12-3
 */
@Data
@Accessors(chain = true)
public class IegSchoolLogChartDto {

    private String name;
    private Integer value;
}
