package com.wang.jmonkey.modules.ieg.service;

import com.wang.jmonkey.modules.ieg.model.entity.IegSchoolMajorProblem;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 报考指南——学校专业问题汇总 服务类
 * </p>
 *
 * @author HeJiawang
 * @since 2019-03-31
 */
public interface IIegSchoolMajorProblemService extends IService<IegSchoolMajorProblem> {

    /**
     * 查询信息
     * @param problem problem
     * @return List<IegSchoolMajorProblem>
     */
    List<IegSchoolMajorProblem> selectBySchool(IegSchoolMajorProblem problem);
}
