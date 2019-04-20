package com.wang.jmonkey.modules.sys.model.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.wang.jmonkey.common.model.enums.YesOrNoEnum;
import com.wang.jmonkey.modules.sys.model.entity.SysMenu;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class SysMenuDto extends SysMenu {

    /**
     * 上级资源id
     */
    private String parentId;

    /**
     * 资源id
     */
    private String rId;

    /**
     * 对当前登陆用户是否授权
     */
    @JSONField(serialzeFeatures = SerializerFeature.WriteEnumUsingToString)
    private YesOrNoEnum isAuth = YesOrNoEnum.No;
}
