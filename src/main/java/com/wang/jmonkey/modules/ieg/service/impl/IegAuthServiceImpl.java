package com.wang.jmonkey.modules.ieg.service.impl;

import com.wang.jmonkey.modules.ieg.model.entity.IegAuth;
import com.wang.jmonkey.modules.ieg.mapper.IegAuthMapper;
import com.wang.jmonkey.modules.ieg.service.IIegAuthService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.xiaoleilu.hutool.collection.CollectionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 报考指南——顾问维护院校信息的权限 服务实现类
 * </p>
 *
 * @author HeJiawang
 * @since 2019-04-10
 */
@Service
public class IegAuthServiceImpl extends ServiceImpl<IegAuthMapper, IegAuth> implements IIegAuthService {

    /**
     * mapper
     */
    @Autowired
    private IegAuthMapper mapper;

    /**
     * 获取授权信息
     * @param userId
     * @return
     */
    @Override
    public List<String> findShcoolIdByUser(String userId) {
        return mapper.findShcoolIdByUser(userId);
    }

    /**
     * 授权
     * @param userId
     * @param schoolIds
     * @return
     */
    @Override
    public Boolean auth(String userId, List<String> schoolIds) {
        mapper.deleteByUser(userId);

        if(CollectionUtil.isNotEmpty(schoolIds)){
            schoolIds.forEach( sId ->
                    super.insert(
                            new IegAuth().setUserId(userId).setSchoolId(sId)
                    )
            );
        }

        return true;
    }
}
