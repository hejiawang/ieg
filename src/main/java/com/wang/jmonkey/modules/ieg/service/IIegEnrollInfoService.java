package com.wang.jmonkey.modules.ieg.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.wang.jmonkey.modules.ieg.model.entity.IegEnrollInfo;
import com.baomidou.mybatisplus.service.IService;
import com.wang.jmonkey.modules.ieg.model.param.IegEnrollInfoParam;

/**
 * <p>
 * 报考指南——招生录取投档线信息 服务类
 * </p>
 *
 * @author HeJiawang
 * @since 2019-04-04
 */
public interface IIegEnrollInfoService extends IService<IegEnrollInfo> {

    /**
     * 导入投档分数线信息
     * @param param param
     * @return Boolean
     */
    Boolean importInfo(IegEnrollInfoParam param);

    /**
     * list page
     * @param page page
     * @param entity entity
     * @return Page<IegEnrollInfo>
     */
    Page<IegEnrollInfo> listPage(Page<IegEnrollInfo> page, IegEnrollInfo entity);

    /**
     * 清空投档分数线信息
     * @param enrollId enrollId
     * @return Boolean
     */
    Boolean delByEnroll(String enrollId);
}
