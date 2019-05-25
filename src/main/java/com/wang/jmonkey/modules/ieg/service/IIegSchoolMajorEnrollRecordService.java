package com.wang.jmonkey.modules.ieg.service;

import com.wang.jmonkey.modules.ieg.model.entity.IegSchoolMajorEnrollRecord;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 报考指南——学校专业历年录取信息 服务类
 * </p>
 *
 * @author HeJiawang
 * @since 2019-04-01
 */
public interface IIegSchoolMajorEnrollRecordService extends IService<IegSchoolMajorEnrollRecord> {

    /**
     * 分页查询信息
     * @param entity 实体信息
     * @return
     */
    List<IegSchoolMajorEnrollRecord> list(IegSchoolMajorEnrollRecord entity);

    /**
     * 校验信息是否正确
     * @param entity 实体信息
     * @return true 正确
     */
    Boolean checkInfo(IegSchoolMajorEnrollRecord entity);
}
