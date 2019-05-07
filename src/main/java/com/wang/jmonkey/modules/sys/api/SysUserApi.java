package com.wang.jmonkey.modules.sys.api;

import com.baomidou.mybatisplus.plugins.Page;
import com.wang.jmonkey.common.http.abs.BaseHttp;
import com.wang.jmonkey.common.http.result.HttpPageResult;
import com.wang.jmonkey.common.http.result.HttpResult;
import com.wang.jmonkey.modules.sys.model.dto.SysUserDto;
import com.wang.jmonkey.modules.sys.model.dto.SysUserInfoDto;
import com.wang.jmonkey.modules.sys.model.entity.SysUser;
import com.wang.jmonkey.modules.sys.model.param.SysUserParam;
import com.wang.jmonkey.modules.sys.service.ISysUserService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.Serializable;

/**
 * @Description: 用户表 api
 * @Auther: HeJiawang
 * @Date: 2018-12-07
 */
@Slf4j
@RestController
@RequestMapping("/sys/user")
public class SysUserApi extends BaseHttp {

    @Resource
    private ISysUserService service;

    @Value("${jmonkey.user.photo}")
    private String userPhotoPath;

    /**
     * 分页查询信息
     * @param page page
     * @param userParam 实体信息
     * @return
     */
    @GetMapping(value = "/list")
    public HttpPageResult<SysUserDto> list(Page<SysUserDto> page, SysUserParam userParam) {
        return new HttpPageResult<>( service.selectPage( page, userParam ) );
    }

    /**
     * 保存实体信息
     * @param userParam 实体信息
     * @return
     */
    @PostMapping(value = "/save")
    public HttpResult<Boolean> save( @RequestBody SysUserParam userParam ){
        return new HttpResult<>(service.save(userParam));
    }

    /**
     * 修改实体信息
     * @param userParam 实体信息
     * @return
     */
    @PutMapping(value = "/modify")
    public HttpResult<Boolean> modify( @RequestBody SysUserParam userParam ){
        return new HttpResult<>(service.modify(userParam));
    }

    /**
     * 修改用户登录密码
     * @param user 用户id以及新的密码
     * @return
     */
    @PutMapping(value = "/modifyPassword")
    public HttpResult<Boolean> modifyPassword( @RequestBody SysUser user ){
        return new HttpResult<>(service.modifyPassword(user));
    }

    /**
     * 删除实体信息
     * @param id 实体ID
     * @return
     */
    @DeleteMapping(value = "/delete/{id}")
    public HttpResult<Boolean> delete( @PathVariable Serializable id ){
        return new HttpResult<>(service.deleteById(id));
    }

    /**
     * 查找实体信息
     * @param id 实体ID
     * @return
     */
    @GetMapping(value = "/findDto/{id}")
    public HttpResult<SysUserDto> findById(@PathVariable Serializable id ){
        return new HttpResult<>(service.selectDtoById(id));
    }

    /**
     * 校验用户登录名是否重复
     * @param sysUser 用户登录名信息
     * @return
     */
    @PostMapping(value = "/checkUsername")
    public HttpResult<Boolean> checkUsername( @RequestBody SysUser sysUser){
        return new HttpResult<>(service.checkUsername(sysUser));
    }

    /**
     * 上传用户头像
     * @param uploadFile 头像文件
     * @return 用户头像访问路径
     */
    @PostMapping("/uploadPhoto")
    public HttpResult<String> uploadPhoto( @RequestParam(value = "file") MultipartFile uploadFile ){
        return super.uploadFile(uploadFile, userPhotoPath, false);
    }

    /**
     * 根据用户登陆名称获取用户信息
     * @return userinfo
     */
    @GetMapping(value = "/info")
    public HttpResult<SysUserInfoDto> getCurrentUserInfo(){
        return new HttpResult<>(service.getCurrentUserInfo());
    }

    /**
     * 获取登录错误次数
     * @param userName userName
     * @return 登录错误次数
     */
    @GetMapping(value = "/loginErrorNum")
    public HttpResult<Integer> loginErrorNum(String userName) {
        return new HttpResult<>(service.loginErrorNum(userName));
    }
}