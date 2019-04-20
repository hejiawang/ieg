package com.wang.jmonkey.common.model.vo;

import com.wang.jmonkey.common.model.BaseVo;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Description: 角色vo
 * @Auther: HeJiawang
 * @Date: 2018/6/24
 */
@Data
@Accessors(chain = true)
public class RoleVo extends BaseVo {

    /**
     * id
     */
    private String id;

    /**
     * 角色名
     */
    private String name;

    /**
     * 角色编号
     */
    private String code;
}
