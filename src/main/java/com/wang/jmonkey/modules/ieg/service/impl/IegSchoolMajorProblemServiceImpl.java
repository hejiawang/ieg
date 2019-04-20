package com.wang.jmonkey.modules.ieg.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.wang.jmonkey.modules.ieg.model.entity.IegSchoolMajorProblem;
import com.wang.jmonkey.modules.ieg.mapper.IegSchoolMajorProblemMapper;
import com.wang.jmonkey.modules.ieg.service.IIegSchoolMajorProblemService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 报考指南——学校专业问题汇总 服务实现类
 * </p>
 *
 * @author HeJiawang
 * @since 2019-03-31
 */
@Service
public class IegSchoolMajorProblemServiceImpl extends ServiceImpl<IegSchoolMajorProblemMapper, IegSchoolMajorProblem> implements IIegSchoolMajorProblemService {

    /**
     * 查询信息
     * @param problem problem
     * @return List<IegSchoolMajorProblem>
     */
    @Override
    public List<IegSchoolMajorProblem> selectBySchool(IegSchoolMajorProblem problem) {
        EntityWrapper<IegSchoolMajorProblem> wrapper = new EntityWrapper<>();
        wrapper.setEntity(problem);
        wrapper.orderBy( "create_date", false );

        return super.selectList(wrapper);
    }
}
