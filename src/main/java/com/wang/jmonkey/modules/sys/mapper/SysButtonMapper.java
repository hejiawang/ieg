package com.wang.jmonkey.modules.sys.mapper;

import com.wang.jmonkey.modules.sys.model.entity.SysButton;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 按钮权限表 Mapper 接口
 * </p>
 *
 * @author HeJiawang
 * @since 2018-12-11
 */
public interface SysButtonMapper extends BaseMapper<SysButton> {

    /**
     * 查询list信息
     * @param parentId 父资源id
     * @return
     */
    List<SysButton> selectListByParent(@Param("parentId") String parentId);
}
