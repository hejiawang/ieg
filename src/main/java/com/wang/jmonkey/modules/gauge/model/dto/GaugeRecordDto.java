package com.wang.jmonkey.modules.gauge.model.dto;

import com.wang.jmonkey.modules.gauge.model.entity.GaugeRecord;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Description:
 * @Auther: HeJiawang
 * @Date: 2019/10/25
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class GaugeRecordDto extends GaugeRecord {

    /**
     * 量表名称
     */
    private String gaugeName;

    /**
     * 图标路径
     */
    private String iconPath;
}
