package com.wang.jmonkey.modules.sys.api;

import com.wang.jmonkey.common.http.abs.BaseHttp;
import com.wang.jmonkey.common.http.result.HttpResult;
import com.wang.jmonkey.modules.sys.model.dto.SysCountDto;
import com.wang.jmonkey.modules.sys.service.ISysCountService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description: 系统设置首页统计信息 api
 * @Auther: HeJiawang
 * @Date: 2019-01-21
 */
@RestController
@RequestMapping("/sys/count")
public class SysCountApi extends BaseHttp {

    /**
     * service
     */
    @Resource
    private ISysCountService service;

    /**
     * 系统用户数量、角色数量、部门数量统计
     * @return 用户数量、角色数量、部门数量统计信息
     */
    @GetMapping(value = "/base")
    public HttpResult<List<SysCountDto>> base(){
        return new HttpResult<>(service.base());
    }

    /**
     * 用户角色分布统计
     * @return 统计信息
     */
    @GetMapping(value = "/userRole")
    public HttpResult<List<SysCountDto>> userRole(){
        return new HttpResult<>(service.userRole());
    }

    /**
     * 用户部门分布统计
     * @return 统计信息
     */
    @GetMapping(value = "/userDept")
    public HttpResult<List<SysCountDto>> userDept(){
        return new HttpResult<>(service.userDept());
    }
}
