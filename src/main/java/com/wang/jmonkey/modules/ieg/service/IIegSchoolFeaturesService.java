package com.wang.jmonkey.modules.ieg.service;

import com.wang.jmonkey.modules.ieg.model.entity.IegSchoolFeatures;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 报考指南——学校特性 服务类
 * </p>
 *
 * @author HeJiawang
 * @since 2019-03-27
 */
public interface IIegSchoolFeaturesService extends IService<IegSchoolFeatures> {

    /**
     * 批量插入院校特征信息
     * @param features 特征信息
     * @param schoolId 院校id
     * @return true
     */
    boolean mergeList(List<String> features, String schoolId);

    /**
     * 获取院校特征名称集合
     * @param schoolId 院校id
     * @return 特征名称集合
     */
    List<String> selectFeatureNames(String schoolId);
}
