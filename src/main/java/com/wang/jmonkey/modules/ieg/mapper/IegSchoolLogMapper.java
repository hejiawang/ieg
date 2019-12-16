package com.wang.jmonkey.modules.ieg.mapper;

import com.wang.jmonkey.modules.ieg.model.entity.IegSchoolLog;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.wang.jmonkey.modules.ieg.model.param.IegSchoolLogParam;

import java.util.List;

/**
 * <p>
 * 报考指南——顾问查看院校详细信息记录 Mapper 接口
 * </p>
 *
 * @author HeJiawang
 * @since 2019-12-16
 */
public interface IegSchoolLogMapper extends BaseMapper<IegSchoolLog> {

    List<IegSchoolLog> listPage(IegSchoolLogParam param);

    long listTotal(IegSchoolLogParam param);
}
