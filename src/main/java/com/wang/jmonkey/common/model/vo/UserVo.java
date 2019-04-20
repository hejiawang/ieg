package com.wang.jmonkey.common.model.vo;

import com.wang.jmonkey.common.model.BaseVo;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Auther: HeJiawang
 * @Date: 2018/6/24
 */
@Data
@Accessors(chain = true)
public class UserVo extends BaseVo {

    /**
     * id
     */
    private String id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 用户角色
     */
    private List<RoleVo> roleList = new ArrayList<>();

}
