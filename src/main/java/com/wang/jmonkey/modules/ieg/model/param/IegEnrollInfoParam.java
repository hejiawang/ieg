package com.wang.jmonkey.modules.ieg.model.param;

import com.wang.jmonkey.modules.ieg.model.entity.IegEnrollInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 报考指南——投档分数线param
 * @author heJiawang
 * @since 2019-04-02
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class IegEnrollInfoParam extends IegEnrollInfo {

    /**
     * 导入文件路径
     */
    private String filePath;
}
