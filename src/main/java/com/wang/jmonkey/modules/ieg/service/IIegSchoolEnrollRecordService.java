package com.wang.jmonkey.modules.ieg.service;

import com.wang.jmonkey.modules.ieg.model.entity.IegSchoolEnrollRecord;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 报考指南——学校历年录取信息 服务类
 * </p>
 *
 * @author HeJiawang
 * @since 2019-03-29
 */
public interface IIegSchoolEnrollRecordService extends IService<IegSchoolEnrollRecord> {

    /**
     * 获取院校历年录取信息
     * @param enrollRecord 录取信息
     * @return IegSchoolEnrollRecord list
     */
    List<IegSchoolEnrollRecord> selectBySchool(IegSchoolEnrollRecord enrollRecord);
}
