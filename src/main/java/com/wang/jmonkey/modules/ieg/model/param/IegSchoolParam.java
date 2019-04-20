package com.wang.jmonkey.modules.ieg.model.param;

import com.wang.jmonkey.modules.ieg.model.entity.IegSchool;
import com.wang.jmonkey.modules.ieg.model.entity.IegSchoolDetail;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.beans.BeanUtils;

import java.util.List;

/**
 * 报考指南——院校Param
 * @author heJiawang
 * @since 2019-03-24
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class IegSchoolParam extends IegSchool {

    /**
     * 院校特性编码
     */
    public List<String> features;

    /**
     * 院校详细信息
     */
    private IegSchoolDetail detail;

    /**
     * 转成院校基本信息
     * @return
     */
    public IegSchool converToEntity() {
        IegSchool school = new IegSchool();
        BeanUtils.copyProperties(this, school);

        return school;
    }

}
