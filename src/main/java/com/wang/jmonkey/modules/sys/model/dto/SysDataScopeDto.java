package com.wang.jmonkey.modules.sys.model.dto;

import com.wang.jmonkey.modules.sys.model.entity.SysDataScope;
import com.wang.jmonkey.modules.sys.model.entity.SysDataScopeRule;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 数据规则 Dto
 * @author HeJiawang
 * @since 2019-03-21
 */
@Data
@Accessors(chain = true)
public class SysDataScopeDto extends SysDataScope {

    /**
     * 数据规则所属的规则定义
     */
    private List<SysDataScopeRule> ruleList;
}
