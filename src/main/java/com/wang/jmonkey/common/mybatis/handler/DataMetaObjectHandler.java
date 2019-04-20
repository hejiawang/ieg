package com.wang.jmonkey.common.mybatis.handler;

import com.baomidou.mybatisplus.mapper.MetaObjectHandler;
import com.wang.jmonkey.common.utils.UserUtils;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description: mybatis 自动填充字段 TODO UserUtils.getUser() 获取不到值，？？？？？？？
 * @Auther: HeJiawang
 * @Date: 2018/7/24
 */
@Component
public class DataMetaObjectHandler extends MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        Object createBy = getFieldValByName("createBy", metaObject);
        if ( StringUtils.isEmpty(createBy) ) {
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if (null != attributes) {
                HttpServletRequest request = attributes.getRequest();
                String userName = UserUtils.getUserName(request);

                setFieldValByName("createBy", userName, metaObject);
            }
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        Object updateBy = getFieldValByName("updateBy", metaObject);
        // TODO 第二次修改时没记录updateBy, 如若放开这个if, 手动为updateBy复制就失效了
        if ( StringUtils.isEmpty(updateBy) ) {
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if (null != attributes) {
                HttpServletRequest request = attributes.getRequest();
                String userName = UserUtils.getUserName(request);

                setFieldValByName("updateBy", userName, metaObject);
            }
        }
    }
}
