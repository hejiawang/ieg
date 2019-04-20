package com.wang.jmonkey.modules.ieg.service;

import com.wang.jmonkey.modules.ieg.model.entity.IegSchoolProblem;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 报考指南——考生对学校的常见问题以及回答 服务类
 * </p>
 *
 * @author HeJiawang
 * @since 2019-03-29
 */
public interface IIegSchoolProblemService extends IService<IegSchoolProblem> {

    /**
     * 查询信息
     * @param problem problem
     * @return List<IegSchoolProblem>
     */
    List<IegSchoolProblem> selectBySchool(IegSchoolProblem problem);
}
