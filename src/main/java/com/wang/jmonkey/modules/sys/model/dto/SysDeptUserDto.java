package com.wang.jmonkey.modules.sys.model.dto;

import com.wang.jmonkey.modules.sys.model.entity.SysDept;
import com.wang.jmonkey.modules.sys.model.entity.SysUser;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @Description: 部门中所有用户
 * @Auther: HeJiawang
 * @Date: 2019-02-23
 */
@Data
@Accessors(chain = true)
public class SysDeptUserDto extends SysDept {

    /**
     * user
     */
    private List<SysUser> userList;
}
