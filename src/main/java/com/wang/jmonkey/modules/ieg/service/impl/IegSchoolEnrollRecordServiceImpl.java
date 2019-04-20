package com.wang.jmonkey.modules.ieg.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.wang.jmonkey.modules.ieg.model.entity.IegSchoolEnrollRecord;
import com.wang.jmonkey.modules.ieg.mapper.IegSchoolEnrollRecordMapper;
import com.wang.jmonkey.modules.ieg.service.IIegSchoolEnrollRecordService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 报考指南——学校历年录取信息 服务实现类
 * </p>
 *
 * @author HeJiawang
 * @since 2019-03-29
 */
@Service
public class IegSchoolEnrollRecordServiceImpl extends ServiceImpl<IegSchoolEnrollRecordMapper, IegSchoolEnrollRecord> implements IIegSchoolEnrollRecordService {

    /**
     * 获取院校历年录取信息
     * @param enrollRecord 录取信息
     * @return IegSchoolEnrollRecord list
     */
    @Override
    public List<IegSchoolEnrollRecord> selectBySchool(IegSchoolEnrollRecord enrollRecord) {
        EntityWrapper<IegSchoolEnrollRecord> wrapper = new EntityWrapper<>();
        wrapper.setEntity(enrollRecord);
        wrapper.orderBy("year", false);

        return super.selectList(wrapper);
    }
}
