package com.wang.jmonkey.modules.ieg.mapper;

import com.wang.jmonkey.modules.ieg.model.entity.IegSchoolDetail;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 报考指南——学校详细信息 Mapper 接口
 * </p>
 *
 * @author HeJiawang
 * @since 2019-03-27
 */
public interface IegSchoolDetailMapper extends BaseMapper<IegSchoolDetail> {

    /**
     * 根据院校id删除院校详细信息
     * @param schoolId 院校id
     * @return int
     */
    int deleteBySchool(@Param("schoolId") String schoolId);

    /**
     * selectBySchoolId
     * @param schoolId schoolId
     * @return IegSchoolDetail
     */
    IegSchoolDetail selectBySchoolId(@Param("schoolId") String schoolId);
}
