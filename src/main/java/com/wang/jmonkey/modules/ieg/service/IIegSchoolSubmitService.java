package com.wang.jmonkey.modules.ieg.service;

import com.wang.jmonkey.modules.ieg.model.entity.IegSchoolSubmit;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 报考指南——投档单位信息 服务类
 * </p>
 *
 * @author HeJiawang
 * @since 2019-04-07
 */
public interface IIegSchoolSubmitService extends IService<IegSchoolSubmit> {

    /**
     * list data
     * @param submit submit
     * @return list
     */
    List<IegSchoolSubmit> list(IegSchoolSubmit submit);

}
