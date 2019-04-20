package com.wang.jmonkey.modules.ieg.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.wang.jmonkey.modules.ieg.model.entity.IegSchoolProblem;
import com.wang.jmonkey.modules.ieg.mapper.IegSchoolProblemMapper;
import com.wang.jmonkey.modules.ieg.service.IIegSchoolProblemService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 报考指南——考生对学校的常见问题以及回答 服务实现类
 * </p>
 *
 * @author HeJiawang
 * @since 2019-03-29
 */
@Service
public class IegSchoolProblemServiceImpl extends ServiceImpl<IegSchoolProblemMapper, IegSchoolProblem> implements IIegSchoolProblemService {

    /**
     * 查询信息
     * @param problem problem
     * @return List<IegSchoolProblem>
     */
    @Override
    public List<IegSchoolProblem> selectBySchool(IegSchoolProblem problem) {
        EntityWrapper<IegSchoolProblem> wrapper = new EntityWrapper<>();
        wrapper.setEntity(problem);
        wrapper.orderBy( "create_date", false );

        return super.selectList(wrapper);
    }
}
