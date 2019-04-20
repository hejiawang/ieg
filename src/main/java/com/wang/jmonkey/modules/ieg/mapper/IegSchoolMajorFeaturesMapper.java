package com.wang.jmonkey.modules.ieg.mapper;

import com.wang.jmonkey.modules.ieg.model.entity.IegSchoolMajorFeatures;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 报考指南——学校专业特性 Mapper 接口
 * </p>
 *
 * @author HeJiawang
 * @since 2019-03-31
 */
public interface IegSchoolMajorFeaturesMapper extends BaseMapper<IegSchoolMajorFeatures> {

    /**
     * 删除院校专业特征信息
     * @param schoolMajorId 院校专业id
     * @return int
     */
    int deleteBySchoolMajorId(@Param("schoolMajorId") String schoolMajorId);
}
