package com.wang.jmonkey.common.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.wang.jmonkey.common.model.enums.RecordStatusEnum;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description: VO模型继承类
 * @Auther: HeJiawang
 * @Date: 2018/6/24
 */
@Data
@Accessors(chain = true)
public abstract class BaseVo implements Serializable {

    /**
     * 创建日期
     */
    protected Date createDate;

    /**
     * 创建人
     */
    protected String createBy;

    /**
     * 最后修改日期
     */
    protected Date updateDate;

    /**
     * 最后修改人
     */
    protected String updateBy;

    /**
     * 备注
     */
    protected String remark;

    /**
     * 记录状态
     */
    @JSONField(serialzeFeatures= SerializerFeature.WriteEnumUsingToString)
    protected RecordStatusEnum deleteFlag = RecordStatusEnum.Used;
}
