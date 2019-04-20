package com.wang.jmonkey.modules.sys.model.param;

import com.wang.jmonkey.modules.sys.model.entity.SysButton;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.beans.BeanUtils;

/**
 * @Description: 操作按钮信息的参数
 * @Auther: HeJiawang
 * @Date: 2018/12/11
 */
@Data
@Accessors(chain = true)
public class SysButtonParam extends SysButton {

    /**
     * 父级菜单
     */
    private String parentId;

    /**
     * 转换为entity信息
     * @return 按钮信息
     */
    public SysButton converToEntity(){
        SysButton button = new SysButton();
        BeanUtils.copyProperties(this, button);
        return button;
    }
}
