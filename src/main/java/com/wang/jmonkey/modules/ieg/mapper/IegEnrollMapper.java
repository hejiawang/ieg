package com.wang.jmonkey.modules.ieg.mapper;

import com.wang.jmonkey.modules.ieg.model.dto.IegEnrollDto;
import com.wang.jmonkey.modules.ieg.model.entity.IegEnroll;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 报考指南——招生录取投档线类型 Mapper 接口
 * </p>
 *
 * @author HeJiawang
 * @since 2019-04-04
 */
public interface IegEnrollMapper extends BaseMapper<IegEnroll> {

    /**
     * 根据院校名称，获取院校投档分数信息
     * @param schoolName 院校名称
     * @return 院校投档分数信息
     */
    List<IegEnrollDto> selectDtoBySchoolName(@Param("schoolName") String schoolName);
}
