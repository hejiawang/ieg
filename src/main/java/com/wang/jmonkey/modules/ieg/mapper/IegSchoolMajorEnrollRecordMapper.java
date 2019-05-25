package com.wang.jmonkey.modules.ieg.mapper;

import com.wang.jmonkey.modules.ieg.model.entity.IegSchoolMajorEnrollRecord;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 * 报考指南——学校专业历年录取信息 Mapper 接口
 * </p>
 *
 * @author HeJiawang
 * @since 2019-04-01
 */
public interface IegSchoolMajorEnrollRecordMapper extends BaseMapper<IegSchoolMajorEnrollRecord> {

    int modifyState(IegSchoolMajorEnrollRecord entity);
}
