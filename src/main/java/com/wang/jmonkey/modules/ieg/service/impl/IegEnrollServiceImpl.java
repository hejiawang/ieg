package com.wang.jmonkey.modules.ieg.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.wang.jmonkey.modules.ieg.model.entity.IegEnroll;
import com.wang.jmonkey.modules.ieg.mapper.IegEnrollMapper;
import com.wang.jmonkey.modules.ieg.service.IIegEnrollService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 报考指南——招生录取投档线类型 服务实现类
 * </p>
 *
 * @author HeJiawang
 * @since 2019-04-04
 */
@Service
public class IegEnrollServiceImpl extends ServiceImpl<IegEnrollMapper, IegEnroll> implements IIegEnrollService {

    /**
     * 分页查询信息
     * @param page page
     * @param entity 实体信息
     * @return
     */
    @Override
    public Page<IegEnroll> listPage(Page<IegEnroll> page, IegEnroll entity) {
        EntityWrapper<IegEnroll> wrapper = new EntityWrapper<>();
        wrapper.setEntity(entity);
        wrapper.orderBy("year", false);
        wrapper.orderBy("create_date", false);

        return super.selectPage(page, wrapper);
    }

    /**
     * 校验信息是否存在
     * @param entity entity
     * @return Boolean
     */
    @Override
    public Boolean checkExist(IegEnroll entity) {
        EntityWrapper<IegEnroll> wrapper = new EntityWrapper<>();
        wrapper.setEntity(
                new IegEnroll()
                    .setYear(entity.getYear())
                    .setCourseType(entity.getCourseType())
                    .setDegreeType(entity.getDegreeType())
                    .setEnrollType(entity.getEnrollType())
        );

        return super.selectOne(wrapper) != null;
    }
}
