package com.wang.jmonkey.modules.ieg.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.wang.jmonkey.modules.ieg.model.entity.IegSchoolMajorEnrollRecord;
import com.wang.jmonkey.modules.ieg.mapper.IegSchoolMajorEnrollRecordMapper;
import com.wang.jmonkey.modules.ieg.service.IIegSchoolMajorEnrollRecordService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 报考指南——学校专业历年录取信息 服务实现类
 * </p>
 *
 * @author HeJiawang
 * @since 2019-04-01
 */
@Service
public class IegSchoolMajorEnrollRecordServiceImpl extends ServiceImpl<IegSchoolMajorEnrollRecordMapper, IegSchoolMajorEnrollRecord> implements IIegSchoolMajorEnrollRecordService {

    /**
     * mapper
     */
    @Autowired
    private IegSchoolMajorEnrollRecordMapper mapper;

    /**
     * 分页查询信息
     * @param entity 实体信息
     * @return
     */
    @Override
    public List<IegSchoolMajorEnrollRecord> list( IegSchoolMajorEnrollRecord entity) {
        EntityWrapper<IegSchoolMajorEnrollRecord> wrapper = new EntityWrapper<>();
        wrapper.setEntity(entity);
        wrapper.orderBy("year");

        return super.selectList(wrapper);
    }

    /**
     * 校验信息是否正确
     * @param entity 实体信息
     * @return true 正确
     */
    @Override
    public Boolean checkInfo(IegSchoolMajorEnrollRecord entity) {
        IegSchoolMajorEnrollRecord old = super.selectById(entity.getId());
        Boolean isSuccess =  Double.compare(entity.getScoreMin(), old.getScoreMin()) == 0 ;

        if (isSuccess) mapper.modifyState(entity);

        return isSuccess;
    }
}
