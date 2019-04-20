package com.wang.jmonkey.modules.ieg.mapper;

import com.wang.jmonkey.modules.ieg.model.entity.IegAuth;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 报考指南——顾问维护院校信息的权限 Mapper 接口
 * </p>
 *
 * @author HeJiawang
 * @since 2019-04-10
 */
public interface IegAuthMapper extends BaseMapper<IegAuth> {

    List<String> findShcoolIdByUser(@Param("userId") String userId);

    int deleteByUser(@Param("userId") String userId);
}
