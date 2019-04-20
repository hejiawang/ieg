package com.wang.jmonkey.modules.sys.mapper;

import com.wang.jmonkey.modules.sys.model.dto.SysDictDto;
import com.wang.jmonkey.modules.sys.model.dto.SysDictTreeDto;
import com.wang.jmonkey.modules.sys.model.entity.SysDict;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 字典值 Mapper 接口
 * </p>
 *
 * @author HeJiawang
 * @since 2018-12-17
 */
public interface SysDictMapper extends BaseMapper<SysDict> {

    /**
     * 获取树形表格数据
     * @return
     */
    List<SysDictTreeDto> tree();

    /**
     * 校验字典键值是否存在
     * @param sysDict
     * @return
     */
    int checkValue(SysDict sysDict);

    /**
     * 获取字典dto信息
     * @param id 字典id
     * @return
     */
    SysDictDto selectDtoById(@Param("id") Serializable id);

    /**
     * 为字典组件赋值
     * 根据父value获取子字典信息
     * @param parentValue 父字典value
     * @return
     */
    List<SysDict> findChildren(@Param("parentValue") String parentValue);
}
