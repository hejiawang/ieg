package com.wang.jmonkey.modules.sys.api;

import com.wang.jmonkey.common.http.abs.BaseHttp;
import com.wang.jmonkey.common.http.result.HttpResult;
import com.wang.jmonkey.modules.sys.service.ISysRoleResourceService;

import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description: 角色资源表 api
 * @Auther: HeJiawang
 * @Date: 2019-01-04
 */
@RestController
@RequestMapping("/sys/role/resource")
public class SysRoleResourceApi extends BaseHttp {

    @Resource
    private ISysRoleResourceService service;

    /**
     * 获取角色以授权的资源id
     * @param roleId 角色id
     * @return
     */
    @GetMapping(value = "/findRidByRole/{roleId}")
    public HttpResult<List<String>> findRidByRole(@PathVariable String roleId ){
        return new HttpResult<>(service.findRidByRole(roleId));
    }

    /**
     * 为角色授权
     * @param roleId 角色id
     * @param rIds 资源id集合
     * @return
     */
    @PostMapping(value = "/auth")
    public HttpResult<Boolean> auth(String roleId, @RequestParam(value = "rIds[]", required=false )List<String> rIds){
        return new HttpResult<>(service.auth(roleId, rIds));
    }

}