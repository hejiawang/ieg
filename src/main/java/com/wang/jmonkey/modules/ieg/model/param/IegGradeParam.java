package com.wang.jmonkey.modules.ieg.model.param;

import com.wang.jmonkey.modules.ieg.model.entity.IegGrade;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 报考指南——一分一段表param
 * @author heJiawang
 * @since 2019-04-02
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class IegGradeParam extends IegGrade {

    /**
     * 批量上传文件的路径
     */
    private String filePath;
}
