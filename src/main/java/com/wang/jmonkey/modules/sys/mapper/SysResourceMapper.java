package com.wang.jmonkey.modules.sys.mapper;

import com.wang.jmonkey.modules.sys.model.dto.SysResourceTreeDto;
import com.wang.jmonkey.modules.sys.model.entity.SysResource;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 系统资源表 Mapper 接口
 * </p>
 *
 * @author HeJiawang
 * @since 2018-12-11
 */
public interface SysResourceMapper extends BaseMapper<SysResource> {

    /**
     * 删除真正资源信息
     * @param tableName 资源表名
     * @param id 资源id
     * @return
     */
    Integer deleteResource(@Param("tableName") String tableName, @Param("id") String id);

    /**
     * 获取dto数据
     * @param index 级别，1只有系统信息，2系统信息加菜单信息，3系统信息菜单信息按钮信息
     * @return
     */
    List<SysResourceTreeDto> selectDtoList(@Param("index")int index);

    /**
     * 获取资源名称
     * @param id 各个资源表id
     * @param tableName 资源表名
     * @return
     */
    String findRName(@Param("id")String id, @Param("tableName")String tableName);

    /**
     * 是否需要引导页
     * @return 需要引导页系统的个数
     */
    int haveGuide();
}
