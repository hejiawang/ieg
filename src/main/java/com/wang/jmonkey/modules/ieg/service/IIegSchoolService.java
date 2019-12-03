package com.wang.jmonkey.modules.ieg.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.wang.jmonkey.modules.ieg.model.dto.IegSchoolDto;
import com.wang.jmonkey.modules.ieg.model.dto.IegSchoolInfoDto;
import com.wang.jmonkey.modules.ieg.model.dto.IegSchoolPageDto;
import com.wang.jmonkey.modules.ieg.model.entity.IegSchool;
import com.baomidou.mybatisplus.service.IService;
import com.wang.jmonkey.modules.ieg.model.param.IegSchoolParam;
import com.wang.jmonkey.modules.ieg.model.param.IegSchoolSearchParam;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 报考指南——学校基本信息 服务类
 * </p>
 *
 * @author HeJiawang
 * @since 2019-03-26
 */
public interface IIegSchoolService extends IService<IegSchool> {

    /**
     * 新建院校信息
     * @param schoolParam 院校信息
     * @return true
     */
    Boolean save(IegSchoolParam schoolParam);

    /**
     * 修改实体信息
     * @param schoolParam 实体信息
     * @return Boolean
     */
    Boolean modify(IegSchoolParam schoolParam);

    /**
     * 查找实体信息
     * @param id 实体ID
     * @return IegSchoolDto
     */
    IegSchoolDto findDtoById(Serializable id);

    /**
     * 分页查询信息
     * @param page page
     * @param param 查询参数
     * @return
     */
    Page<IegSchoolPageDto> pageList(Page<IegSchoolPageDto> page, IegSchoolSearchParam param);

    /**
     * listAll
     * @param param param
     * @return List
     */
    List<IegSchool> listAll(IegSchoolSearchParam param);

    /**
     * findInfoDtoById
     * @param id schoolId
     * @return IegSchoolInfoDto
     */
    IegSchoolInfoDto findInfoDtoById(String id);
}
