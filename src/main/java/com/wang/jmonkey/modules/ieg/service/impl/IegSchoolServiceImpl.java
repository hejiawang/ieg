package com.wang.jmonkey.modules.ieg.service.impl;

import com.baomidou.mybatisplus.enums.SqlLike;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.wang.jmonkey.modules.ieg.model.dto.IegSchoolDto;
import com.wang.jmonkey.modules.ieg.model.dto.IegSchoolInfoDto;
import com.wang.jmonkey.modules.ieg.model.dto.IegSchoolPageDto;
import com.wang.jmonkey.modules.ieg.model.entity.IegSchool;
import com.wang.jmonkey.modules.ieg.mapper.IegSchoolMapper;
import com.wang.jmonkey.modules.ieg.model.entity.IegSchoolDetail;
import com.wang.jmonkey.modules.ieg.model.param.IegSchoolParam;
import com.wang.jmonkey.modules.ieg.model.param.IegSchoolSearchParam;
import com.wang.jmonkey.modules.ieg.service.IIegSchoolDetailService;
import com.wang.jmonkey.modules.ieg.service.IIegSchoolFeaturesService;
import com.wang.jmonkey.modules.ieg.service.IIegSchoolService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 报考指南——学校基本信息 服务实现类
 * </p>
 *
 * @author HeJiawang
 * @since 2019-03-26
 */
@Service
public class IegSchoolServiceImpl extends ServiceImpl<IegSchoolMapper, IegSchool> implements IIegSchoolService {

    /**
     * mapper
     */
    @Autowired
    private IegSchoolMapper mapper;

    /**
     * detailService
     */
    @Autowired
    private IIegSchoolDetailService detailService;

    /**
     * featuresService
     */
    @Autowired
    private IIegSchoolFeaturesService featuresService;

    /**
     * 新建院校信息
     * @param schoolParam 院校信息
     * @return true
     */
    @Transactional
    @Override
    public Boolean save(IegSchoolParam schoolParam) {
        IegSchool school = schoolParam.converToEntity();

        return super.insert(school)
                && detailService.merge(
                    schoolParam.getDetail().setSchoolId(school.getId())
                )
                && featuresService.mergeList(schoolParam.getFeatures(), school.getId());
    }

    /**
     * 修改实体信息
     * @param schoolParam 实体信息
     * @return Boolean
     */
    @Override
    public Boolean modify(IegSchoolParam schoolParam) {
        IegSchool school = schoolParam.converToEntity();

        return super.updateById(school)
                && detailService.merge(
                    schoolParam.getDetail().setSchoolId(school.getId())
                )
                && featuresService.mergeList(schoolParam.getFeatures(), school.getId());
    }

    /**
     * 查找实体信息
     * @param id 实体ID
     * @return IegSchoolDto
     */
    @Override
    public IegSchoolDto findDtoById(Serializable id) {
        return mapper.findDtoById(id);
    }

    /**
     * 分页查询信息
     * @param page page
     * @param param 查询参数
     * @return
     */
    @Override
    public Page<IegSchoolPageDto> pageList(Page<IegSchoolPageDto> page, IegSchoolSearchParam param) {
        int limitStart = page.getSize() * ( page.getCurrent() - 1 );

        List<IegSchoolPageDto> schoolPageDtoList = mapper.pageList(param, limitStart, page.getSize());
        schoolPageDtoList.forEach( schoolPageDto ->
            schoolPageDto.setFeatureNames(featuresService.selectFeatureNames(schoolPageDto.getId()))
        );

        page.setRecords( schoolPageDtoList )
                .setTotal( mapper.pageCount(param) )
                .setCurrent( page.getCurrent() )
                .setSize( page.getSize() );
        return page;
    }

    /**
     * listAll
     * @param param param
     * @return List
     */
    @Override
    public List<IegSchool> listAll(IegSchoolSearchParam param) {
        EntityWrapper<IegSchool> wrapper = new EntityWrapper<>();
        wrapper.like("name", param.getName(), SqlLike.DEFAULT);
        wrapper.orderBy("sort");

        return super.selectList(wrapper);
    }

    /**
     * findInfoDtoById
     * @param id schoolId
     * @return IegSchoolInfoDto
     */
    @Override
    public IegSchoolInfoDto findInfoDtoById(String id) {
        return mapper.findInfoDtoById(id);
    }
}
