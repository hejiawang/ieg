package com.wang.jmonkey.modules.sys.api;

import com.wang.jmonkey.common.http.abs.BaseHttp;
import com.wang.jmonkey.common.http.result.HttpResult;
import com.wang.jmonkey.modules.sys.service.ISysRoleDataService;

import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @Description: 角色数据规则表 api
 * @Auther: heJiawang
 * @Date: 2019-03-20
 */
@RestController
@RequestMapping("/sys/role/data")
public class SysRoleDataApi extends BaseHttp {

    @Resource
    private ISysRoleDataService service;

    /**
     * 获取角色授权的数据规则
     * @param roleId 角色id
     * @return Map<String, String> key: dataScopeId value: ruleId
     */
    @GetMapping(value = "/findByRole/{roleId}")
    public HttpResult<Map<String, String>> findByRole(@PathVariable String roleId ){
        return new HttpResult<>(service.findByRole(roleId));
    }

    /**
     * 为角色授权数据规则
     * @param roleId roleId
     * @param ruleIds ruleIds
     * @return Boolean
     */
    @PostMapping(value = "/auth")
    public HttpResult<Boolean> auth( String roleId, @RequestParam(value = "ruleIds[]", required=false ) List<String> ruleIds ){
        return new HttpResult<>(service.auth(roleId, ruleIds));
    }

}