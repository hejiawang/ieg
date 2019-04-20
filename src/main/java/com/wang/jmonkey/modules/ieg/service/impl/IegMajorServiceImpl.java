package com.wang.jmonkey.modules.ieg.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.wang.jmonkey.common.utils.TreeUtil;
import com.wang.jmonkey.modules.ieg.model.dto.IegMajorDto;
import com.wang.jmonkey.modules.ieg.model.dto.IegMajorTreeDto;
import com.wang.jmonkey.modules.ieg.model.entity.IegMajor;
import com.wang.jmonkey.modules.ieg.mapper.IegMajorMapper;
import com.wang.jmonkey.modules.ieg.model.enums.IegMajorLevelTypeEnums;
import com.wang.jmonkey.modules.ieg.service.IIegMajorService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.xiaoleilu.hutool.collection.CollectionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 报考指南——专业定义 服务实现类
 * </p>
 *
 * @author heJiawang
 * @since 2019-03-24
 */
@Service
public class IegMajorServiceImpl extends ServiceImpl<IegMajorMapper, IegMajor> implements IIegMajorService {

    /**
     * mapper
     */
    @Autowired
    private IegMajorMapper mapper;

    /**
     * 获取树形数据
     * @param major 专业信息
     * @return 专业树
     */
    @Override
    public List<IegMajorTreeDto> tree(IegMajor major) {
        return TreeUtil.bulid( mapper.tree(major), null );
    }

    /**
     * 获取专业信息
     * @param id 专业id
     * @return 专业信息
     */
    @Override
    public IegMajorDto selectDtoById(Serializable id) {
        IegMajorDto majorDto = IegMajorDto.converFromEntity( super.selectById(id) );

        // 如果是第二级（学科），将学科的所属门类查出来
        if (majorDto.getLevelType() == IegMajorLevelTypeEnums.Two) {
            majorDto.setOneName(
                super.selectById(majorDto.getParentId()).getName()
            );
        }

        // 如果是第三级（专业），将专业的所属学科，所属门类查出来
        if (majorDto.getLevelType() == IegMajorLevelTypeEnums.Three) {
            IegMajor twoMajor = super.selectById(majorDto.getParentId());
            IegMajor oneMajor = super.selectById(twoMajor.getId());

            majorDto.setTwoName(twoMajor.getName()).setOneName(oneMajor.getName());
        }

        return majorDto;
    }

    /**
     * 删除专业信息，以及子专业信息
     * @param id 专业id
     * @return
     */
    @Override
    public boolean deleteById(Serializable id) {
        // 删除子节点部门信息
        EntityWrapper<IegMajor> wrapper = new EntityWrapper<>();
        wrapper.setEntity(new IegMajor().setParentId(String.valueOf(id)));
        List<IegMajor> majorChildrens = this.selectList(wrapper);
        if(CollectionUtil.isNotEmpty(majorChildrens)){
            majorChildrens.forEach( iegMajor -> this.deleteById(iegMajor.getId()) );
        }

        // 删除自身信息
        return super.deleteById(id);
    }
}
