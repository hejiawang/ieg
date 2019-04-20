package com.wang.jmonkey.modules.ieg.service.impl;

import com.wang.jmonkey.modules.ieg.model.entity.IegSchoolFeatures;
import com.wang.jmonkey.modules.ieg.mapper.IegSchoolFeaturesMapper;
import com.wang.jmonkey.modules.ieg.service.IIegSchoolFeaturesService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.xiaoleilu.hutool.collection.CollectionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 报考指南——学校特性 服务实现类
 * </p>
 *
 * @author HeJiawang
 * @since 2019-03-27
 */
@Service
public class IegSchoolFeaturesServiceImpl extends ServiceImpl<IegSchoolFeaturesMapper, IegSchoolFeatures> implements IIegSchoolFeaturesService {

    /**
     * mapper
     */
    @Autowired
    private IegSchoolFeaturesMapper mapper;

    /**
     * 批量插入院校特征信息
     * @param features 特征信息
     * @param schoolId 院校id
     * @return true
     */
    @Transactional
    @Override
    public boolean mergeList(List<String> features, String schoolId) {
        boolean result = mapper.deleteBySchool(schoolId) >= 0;

        if (CollectionUtil.isNotEmpty(features)) {
            features.forEach(feature -> {
                IegSchoolFeatures schoolFeatures = new IegSchoolFeatures()
                        .setType(feature).setSchoolId(schoolId);
                super.insert(schoolFeatures);
            });
        }

        return result;
    }

    /**
     * 获取院校特征名称集合
     * @param schoolId 院校id
     * @return 特征名称集合
     */
    @Override
    public List<String> selectFeatureNames(String schoolId) {
        return mapper.selectFeatureNames(schoolId);
    }
}
