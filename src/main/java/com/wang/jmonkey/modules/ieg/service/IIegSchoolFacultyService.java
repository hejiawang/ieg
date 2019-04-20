package com.wang.jmonkey.modules.ieg.service;

import com.wang.jmonkey.modules.ieg.model.entity.IegSchoolFaculty;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 报考指南——学校院系信息 服务类
 * </p>
 *
 * @author HeJiawang
 * @since 2019-03-29
 */
public interface IIegSchoolFacultyService extends IService<IegSchoolFaculty> {

    /**
     * 获取院校所有院系信息
     * @param faculty 查询条件
     * @return 院系信息
     */
    List<IegSchoolFaculty> selectBySchool(IegSchoolFaculty faculty);
}
