package com.wang.jmonkey.modules.ieg.mapper;

import com.wang.jmonkey.modules.ieg.model.dto.IegSchoolDto;
import com.wang.jmonkey.modules.ieg.model.dto.IegSchoolPageDto;
import com.wang.jmonkey.modules.ieg.model.entity.IegSchool;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.wang.jmonkey.modules.ieg.model.param.IegSchoolSearchParam;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 报考指南——学校基本信息 Mapper 接口
 * </p>
 *
 * @author HeJiawang
 * @since 2019-03-26
 */
public interface IegSchoolMapper extends BaseMapper<IegSchool> {

    /**
     * 查找实体信息
     * @param id 实体ID
     * @return IegSchoolDto
     */
    IegSchoolDto findDtoById(@Param("id") Serializable id);

    /**
     * 获取分页数据
     * @param param param
     * @param limitStart limitStart
     * @param size size
     * @return IegSchoolPageDto
     */
    List<IegSchoolPageDto> pageList(@Param("param")IegSchoolSearchParam param,
                                    @Param("limitStart")int limitStart, @Param("size")int size);

    /**
     * 获取分页总条数
     * @param param param
     * @return long
     */
    long pageCount(IegSchoolSearchParam param);
}
