package com.wang.jmonkey.modules.ieg.service.impl;

import com.wang.jmonkey.modules.ieg.model.entity.IegSchoolMajorFeatures;
import com.wang.jmonkey.modules.ieg.mapper.IegSchoolMajorFeaturesMapper;
import com.wang.jmonkey.modules.ieg.service.IIegSchoolMajorFeaturesService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 报考指南——学校专业特性 服务实现类
 * </p>
 *
 * @author HeJiawang
 * @since 2019-03-31
 */
@Service
public class IegSchoolMajorFeaturesServiceImpl extends ServiceImpl<IegSchoolMajorFeaturesMapper, IegSchoolMajorFeatures> implements IIegSchoolMajorFeaturesService {

    /**
     * mapper
     */
    @Autowired
    private IegSchoolMajorFeaturesMapper mapper;

    /**
     * meger 院校专业特征信息
     * @param schoolMajorId 院校专业id
     * @param features 特征信息
     * @return true
     */
    @Override
    public boolean merge(String schoolMajorId, List<String> features) {
        boolean result = mapper.deleteBySchoolMajorId(schoolMajorId) >= 0;

        features.forEach(feature -> {
            IegSchoolMajorFeatures schoolMajorFeatures = new IegSchoolMajorFeatures()
                    .setSchoolMajorId(schoolMajorId).setType(feature);
            super.insert(schoolMajorFeatures);
        });

        return result;
    }
}
