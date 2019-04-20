package com.wang.jmonkey.modules.ieg.service;

import com.wang.jmonkey.modules.ieg.model.entity.IegSchoolMajorFeatures;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 报考指南——学校专业特性 服务类
 * </p>
 *
 * @author HeJiawang
 * @since 2019-03-31
 */
public interface IIegSchoolMajorFeaturesService extends IService<IegSchoolMajorFeatures> {

    /**
     * meger 院校专业特征信息
     * @param schoolMajorId 院校专业id
     * @param features 特征信息
     * @return true
     */
    boolean merge(String schoolMajorId, List<String> features);
}
