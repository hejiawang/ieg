package com.wang.jmonkey.modules.ieg.service.impl;

import com.wang.jmonkey.modules.ieg.model.entity.IegSchoolDetail;
import com.wang.jmonkey.modules.ieg.mapper.IegSchoolDetailMapper;
import com.wang.jmonkey.modules.ieg.service.IIegSchoolDetailService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 报考指南——学校详细信息 服务实现类
 * </p>
 *
 * @author HeJiawang
 * @since 2019-03-27
 */
@Service
public class IegSchoolDetailServiceImpl extends ServiceImpl<IegSchoolDetailMapper, IegSchoolDetail> implements IIegSchoolDetailService {

    /**
     * mapper
     */
    @Autowired
    private IegSchoolDetailMapper mapper;

    /**
     * 院校详细信息 merge
     * @param schoolDetail schoolDetail
     * @return true
     */
    @Override
    public boolean merge(IegSchoolDetail schoolDetail) {
        return mapper.deleteBySchool(schoolDetail.getSchoolId()) >= 0
                && super.insert(schoolDetail);
    }
}
