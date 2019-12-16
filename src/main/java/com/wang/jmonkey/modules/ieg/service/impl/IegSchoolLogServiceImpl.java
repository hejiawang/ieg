package com.wang.jmonkey.modules.ieg.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.wang.jmonkey.modules.ieg.model.entity.IegSchoolLog;
import com.wang.jmonkey.modules.ieg.mapper.IegSchoolLogMapper;
import com.wang.jmonkey.modules.ieg.model.param.IegSchoolLogParam;
import com.wang.jmonkey.modules.ieg.service.IIegSchoolLogService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 报考指南——顾问查看院校详细信息记录 服务实现类
 * </p>
 *
 * @author HeJiawang
 * @since 2019-12-16
 */
@Service
public class IegSchoolLogServiceImpl extends ServiceImpl<IegSchoolLogMapper, IegSchoolLog> implements IIegSchoolLogService {

    /**
     * mapper
     */
    @Autowired
    private IegSchoolLogMapper mapper;

    /**
     * selectPageList
     * @param param param
     * @return IegSchoolLog
     */
    @Override
    public Page<IegSchoolLog> selectPageList(IegSchoolLogParam param) {
        Page<IegSchoolLog> pageResult = new Page<>();

        pageResult.setRecords(mapper.listPage(param))
                .setTotal(mapper.listTotal(param))
                .setCurrent(param.getCurrent())
                .setSize(param.getSize());

        return pageResult;
    }
}
