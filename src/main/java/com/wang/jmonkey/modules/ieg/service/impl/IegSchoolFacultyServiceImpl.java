package com.wang.jmonkey.modules.ieg.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.wang.jmonkey.modules.ieg.model.entity.IegSchoolFaculty;
import com.wang.jmonkey.modules.ieg.mapper.IegSchoolFacultyMapper;
import com.wang.jmonkey.modules.ieg.service.IIegSchoolFacultyService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 报考指南——学校院系信息 服务实现类
 * </p>
 *
 * @author HeJiawang
 * @since 2019-03-29
 */
@Service
public class IegSchoolFacultyServiceImpl extends ServiceImpl<IegSchoolFacultyMapper, IegSchoolFaculty> implements IIegSchoolFacultyService {

    /**
     * 获取院校所有院系信息
     * @param faculty 查询条件
     * @return 院系信息
     */
    @Override
    public List<IegSchoolFaculty> selectBySchool(IegSchoolFaculty faculty) {
        EntityWrapper<IegSchoolFaculty> wrapper = new EntityWrapper<>();
        wrapper.setEntity(faculty);
        wrapper.orderBy( "sort" );

        return super.selectList(wrapper);
    }
}
