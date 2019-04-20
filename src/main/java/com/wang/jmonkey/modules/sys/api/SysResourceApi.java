package com.wang.jmonkey.modules.sys.api;

import com.wang.jmonkey.common.http.abs.BaseHttp;
import com.wang.jmonkey.common.http.result.HttpResult;
import com.wang.jmonkey.modules.sys.model.dto.SysResourceTreeDto;
import com.wang.jmonkey.modules.sys.service.ISysResourceService;

import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description: 系统资源表 api
 * @Auther: HeJiawang
 * @Date: 2018-12-11
 */
@RestController
@RequestMapping("/sys/resource")
public class SysResourceApi extends BaseHttp {

    @Resource
    private ISysResourceService service;

    /**
     * 系统，菜单，构成的树形结果数据
     * @return
     */
    @GetMapping(value = "/smtree")
    public HttpResult<List<SysResourceTreeDto>> smTree() {
        return new HttpResult<>( service.smTree() );
    }

    /**
     * 系统，菜单，按钮，构成的树形结果数据
     * @return
     */
    @GetMapping(value = "/smbtree")
    public HttpResult<List<SysResourceTreeDto>> smbTree(){
        return new HttpResult<>( service.smbTree() );
    }

    /**
     * 根据sys_resource表的id获取资源名称
     * @param rId sys_resource表的id
     * @return
     */
    @GetMapping(value = "/findNameByRid")
    public HttpResult<String> findNameByRid(String rId){
        return new HttpResult<>(service.findNameByRid(rId));
    }

}