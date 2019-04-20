package com.wang.jmonkey.modules.ieg.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.wang.jmonkey.modules.ieg.model.entity.IegSchoolSubmit;
import com.wang.jmonkey.modules.ieg.mapper.IegSchoolSubmitMapper;
import com.wang.jmonkey.modules.ieg.service.IIegSchoolSubmitService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 报考指南——投档单位信息 服务实现类
 * </p>
 *
 * @author HeJiawang
 * @since 2019-04-07
 */
@Service
public class IegSchoolSubmitServiceImpl extends ServiceImpl<IegSchoolSubmitMapper, IegSchoolSubmit> implements IIegSchoolSubmitService {

    /**
     * list data
     * @param submit submit
     * @return list
     */
    @Override
    public List<IegSchoolSubmit> list(IegSchoolSubmit submit) {
        EntityWrapper<IegSchoolSubmit> wrapper = new EntityWrapper<>();
        wrapper.setEntity(submit);
        wrapper.orderBy("code");

        return super.selectList(wrapper);
    }

}
