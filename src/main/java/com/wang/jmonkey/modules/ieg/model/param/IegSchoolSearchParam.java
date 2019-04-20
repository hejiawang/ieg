package com.wang.jmonkey.modules.ieg.model.param;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 报考指南——院校Param
 * @author heJiawang
 * @since 2019-03-24
 */
@Data
@Accessors(chain = true)
public class IegSchoolSearchParam {

    /**
     * 院校名称
     */
    private String name;

    /**
     * 登录人id
     */
    private String userId;
}
