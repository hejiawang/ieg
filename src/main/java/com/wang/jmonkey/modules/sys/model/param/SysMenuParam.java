package com.wang.jmonkey.modules.sys.model.param;

import com.wang.jmonkey.modules.sys.model.entity.SysMenu;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.beans.BeanUtils;

/**
 * @Description: 操作菜单信息的参数
 * @Auther: HeJiawang
 * @Date: 2018/12/11
 */
@Data
@Accessors(chain = true)
public class SysMenuParam extends SysMenu {

    /**
     * 父级菜单
     */
    private String parentId;

    /**
     * 转换为entity信息
     * @return 菜单信息
     */
    public SysMenu converToEntity(){
        SysMenu menu = new SysMenu();
        BeanUtils.copyProperties(this, menu);
        return menu;
    }
}
