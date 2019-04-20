package com.wang.jmonkey.modules.ieg.service;

import com.wang.jmonkey.modules.ieg.model.dto.IegSchoolMajorDto;
import com.wang.jmonkey.modules.ieg.model.entity.IegMajor;
import com.wang.jmonkey.modules.ieg.model.entity.IegSchoolMajor;
import com.baomidou.mybatisplus.service.IService;
import com.wang.jmonkey.modules.ieg.model.param.IegSchoolMajorParam;

import java.util.List;

/**
 * <p>
 * 报考指南——学校历年录取信息 服务类
 * </p>
 *
 * @author HeJiawang
 * @since 2019-03-31
 */
public interface IIegSchoolMajorService extends IService<IegSchoolMajor> {

    /**
     * 保存院校专业信息
     * @param param 专业信息
     * @return true
     */
    Boolean save(IegSchoolMajorParam param);

    /**
     * 修改院校专业信息
     * @param param 专业信息
     * @return true
     */
    Boolean modify(IegSchoolMajorParam param);

    /**
     * 院校专业list
     * @param param param
     * @return IegSchoolMajorDto
     */
    List<IegSchoolMajorDto> list(IegSchoolMajorParam param);
}
