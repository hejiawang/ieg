package com.wang.jmonkey.modules.ieg.mapper;

import com.wang.jmonkey.modules.ieg.model.entity.IegGrade;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.wang.jmonkey.modules.ieg.model.param.IegGradeParam;

/**
 * <p>
 * 报考指南——一分一段表 Mapper 接口
 * </p>
 *
 * @author HeJiawang
 * @since 2019-04-02
 */
public interface IegGradeMapper extends BaseMapper<IegGrade> {

    /**
     * 批量删除
     * @param entity 删除条件
     * @return true
     */
    int delByYearAndType(IegGrade entity);

    /**
     * 校验是否存在
     * @param param param
     * @return true 已存在
     */
    int checkExist(IegGradeParam param);

    /**
     * 校验通过
     * @param param param
     * @return int
     */
    int checkYes(IegGradeParam param);
}
