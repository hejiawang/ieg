package com.wang.jmonkey.modules.ieg.service;

import com.wang.jmonkey.modules.ieg.model.entity.IegAuth;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 报考指南——顾问维护院校信息的权限 服务类
 * </p>
 *
 * @author HeJiawang
 * @since 2019-04-10
 */
public interface IIegAuthService extends IService<IegAuth> {

    /**
     * 获取授权信息
     * @param userId
     * @return
     */
    List<String> findShcoolIdByUser(String userId);

    /**
     * 授权
     * @param userId
     * @param schoolIds
     * @return
     */
    Boolean auth(String userId, List<String> schoolIds);
}
