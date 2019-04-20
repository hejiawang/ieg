package com.wang.jmonkey.modules.ieg.model.param;

import com.wang.jmonkey.modules.ieg.model.entity.IegSchoolMajor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.beans.BeanUtils;

import java.util.List;

/**
 * 报考指南——院校专业Param
 * @author heJiawang
 * @since 2019-03-24
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class IegSchoolMajorParam extends IegSchoolMajor {

    /**
     * 院校专业特性编码
     */
    public List<String> features;

    /**
     * 转成院校基本信息
     * @return
     */
    public IegSchoolMajor converToEntity() {
        IegSchoolMajor schoolMajor = new IegSchoolMajor();
        BeanUtils.copyProperties(this, schoolMajor);

        return schoolMajor;
    }
}
