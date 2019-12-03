package com.wang.jmonkey.modules.ieg.service;

import com.wang.jmonkey.modules.ieg.model.entity.IegSchoolDetail;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 报考指南——学校详细信息 服务类
 * </p>
 *
 * @author HeJiawang
 * @since 2019-03-27
 */
public interface IIegSchoolDetailService extends IService<IegSchoolDetail> {

    /**
     * 院校详细信息 merge
     * @param schoolDetail schoolDetail
     * @return true
     */
    boolean merge(IegSchoolDetail schoolDetail);

    /**
     * selectBySchoolId
     * @param schoolId schoolId
     * @return IegSchoolDetail
     */
    IegSchoolDetail selectBySchoolId(String schoolId);
}
