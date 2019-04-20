package com.wang.jmonkey.modules.ieg.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.wang.jmonkey.common.http.result.HttpResult;
import com.wang.jmonkey.modules.ieg.model.entity.IegGrade;
import com.baomidou.mybatisplus.service.IService;
import com.wang.jmonkey.modules.ieg.model.param.IegGradeParam;

/**
 * <p>
 * 报考指南——一分一段表 服务类
 * </p>
 *
 * @author HeJiawang
 * @since 2019-04-02
 */
public interface IIegGradeService extends IService<IegGrade> {

    /**
     * 分页查询信息
     * @param page page
     * @param entity 实体信息
     * @return
     */
    Page<IegGrade> list(Page<IegGrade> page, IegGrade entity);

    /**
     * 批量导入
     * @param param param
     * @return Boolean
     */
    Boolean importGrade(IegGradeParam param);

    /**
     * 批量校验
     * @param param param
     * @return Boolean
     */
    HttpResult<Boolean> checkGrade(IegGradeParam param);

    /**
     * 批量删除
     * @param entity 删除条件
     * @return true
     */
    Boolean delByYearAndType(IegGrade entity);

    /**
     * 校验是否存在
     * @param param param
     * @return true 已存在
     */
    Boolean checkExist(IegGradeParam param);

}
