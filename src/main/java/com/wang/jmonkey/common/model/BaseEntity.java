package com.wang.jmonkey.common.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.wang.jmonkey.common.model.enums.RecordStatusEnum;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

/**
 * @Description: 基础实体
 * @Auther: HeJiawang
 * @Date: 2018/4/13
 */
@Data
@Accessors(chain = true)
public abstract class BaseEntity<T extends BaseEntity> extends Model<T> {

    /**
     * 创建日期
     */
    protected Date createDate;

    /**
     * 创建人
     */
    @TableField(fill = FieldFill.INSERT)
    protected String createBy;

    /**
     * 最后修改日期
     */
    protected Date updateDate;

    /**
     * 最后修改人
     */
    @TableField(fill = FieldFill.UPDATE)
    protected String updateBy;

    /**
     * 备注
     */
    @Length(max = 100, message = "备注长度不能超过100")
    protected String remark;

    /**
     * 记录状态
     */
    @TableLogic
    @JSONField(serialzeFeatures= SerializerFeature.WriteEnumUsingToString)
    protected RecordStatusEnum deleteFlag;
}
