package com.wang.jmonkey.modules.sys.model.entity;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.wang.jmonkey.common.model.BaseEntity;

import com.wang.jmonkey.common.model.enums.SexEnum;
import com.wang.jmonkey.common.utils.poi.annotation.ExcelField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author HeJiawang
 * @since 2018-12-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class SysUser extends BaseEntity<SysUser> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.UUID)
    private String id;
    /**
     * 用户名
     */
    @ExcelField(title="用户名", align=2)
    private String username;
    /**
     * 登陆密码
     */
    private String password;
    /**
     * 手机号码
     */
    @ExcelField(title="手机号码", align=2)
    private String phone;
    /**
     * 真实姓名
     */
    @ExcelField(title="真实姓名", align=2)
    private String realName;
    /**
     * 出生日期
     */
    @ExcelField(title="出生日期", align=2)
    private Date birthday;
    /**
     * 性别 Man男 Woman女 Other其他
     */
    @ExcelField(title="性别", align=2)
    @JSONField(serialzeFeatures= SerializerFeature.WriteEnumUsingToString)
    private SexEnum sex;
    /**
     * 用户头像
     */
    private String photo;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
