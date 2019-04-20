package com.wang.jmonkey.modules.ieg.api;

import com.wang.jmonkey.common.http.abs.BaseHttp;
import com.wang.jmonkey.common.http.result.HttpResult;
import com.wang.jmonkey.modules.ieg.service.IIegAuthService;

import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description: 报考指南——顾问维护院校信息的权限 api
 * @Auther: HeJiawang
 * @Date: 2019-04-10
 */
@RestController
@RequestMapping("/ieg/auth")
public class IegAuthApi extends BaseHttp {

    @Resource
    private IIegAuthService service;

    /**
     * 获取授权信息
     * @param userId
     * @return
     */
    @GetMapping(value = "/findShcoolIdByUser/{userId}")
    public HttpResult<List<String>> findShcoolIdByUser(@PathVariable String userId ){
        return new HttpResult<>(service.findShcoolIdByUser(userId));
    }

    /**
     * 授权
     * @param userId
     * @param schoolIds
     * @return
     */
    @PostMapping(value = "/auth")
    public HttpResult<Boolean> auth(String userId,
                                    @RequestParam(value = "schoolIds[]", required=false )List<String> schoolIds){
        return new HttpResult<>(service.auth(userId, schoolIds));
    }

}