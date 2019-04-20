package com.wang.jmonkey.common.oauth.service.impl;

import com.wang.jmonkey.common.constant.SecurityConstants;
import com.wang.jmonkey.common.oauth.service.PermissionService;
import com.wang.jmonkey.modules.sys.model.dto.SysRoleDataRuleDto;
import com.wang.jmonkey.modules.sys.model.entity.SysButton;
import com.wang.jmonkey.modules.sys.service.ISysRoleDataService;
import com.wang.jmonkey.modules.sys.service.ISysRoleResourceService;
import com.xiaoleilu.hutool.collection.CollUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;

/**
 * @Description: 权限 service实现
 * @Auther: HeJiawang
 * @Date: 2018/6/24
 */
@Service("permissionService")
public class PermissionServiceImpl implements PermissionService {

    @Value("${jmonkey.baseUrl}")
    private String BASE_URL;

    @Autowired
    private ISysRoleResourceService roleResourceService;

    /**
     * roleDataService
     */
    @Autowired
    private ISysRoleDataService roleDataService;

    /**
     * 路径比对工具类
     */
    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    /**
     * 判断登录者是否拥有访问路径的权限
     * @param request HttpServletRequest 请求路径信息
     * @param authentication 认证信息
     * @return true/false
     */
    @Override
    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        Object principal = authentication.getPrincipal();
        List<SimpleGrantedAuthority> grantedAuthorityList = (List<SimpleGrantedAuthority>) authentication.getAuthorities();
        boolean hasPermission = false;

        if (principal != null) {
            if (CollectionUtils.isEmpty(grantedAuthorityList)) return hasPermission;

            // 判断是否有访问权限
            Set<SysButton> urls = permissionInfo(grantedAuthorityList);
            for( SysButton button : urls ){
                if (StringUtils.isNotEmpty(button.getUrl())
                        && antPathMatcher.match(BASE_URL + button.getUrl(), request.getRequestURI())
                        && request.getMethod().equalsIgnoreCase(button.getMethod().getValue())) {

                    hasPermission = true;
                    break;
                }
            }

            // 获取路径的数据规则
            if (hasPermission) {
                Set<SysRoleDataRuleDto> dataRules = dataInfo(grantedAuthorityList);
                for (SysRoleDataRuleDto dataRule : dataRules) {
                    if ( antPathMatcher.match(BASE_URL + dataRule.getUrl(), request.getRequestURI()) )
                        request.setAttribute("dataScope", dataRule.getSqlSegment());
                }
            }
        }

        return hasPermission;
    }

    /**
     * 获取授权的访问信息
     * @param grantedAuthorityList
     * @return
     */
    private Set<SysButton> permissionInfo(List<SimpleGrantedAuthority> grantedAuthorityList) {
        Set<SysButton> result = CollUtil.newHashSet();
        grantedAuthorityList.forEach( authority -> {
            if ( !StringUtils.equals(authority.getAuthority(), SecurityConstants.BASE_ROLE) ) {
                Set<SysButton> pInfo = roleResourceService.selectButtonByRole(authority.getAuthority());
                if (CollUtil.isNotEmpty(pInfo)) CollUtil.addAll(result, pInfo);
            }
        });

        return result;
    }

    /**
     * 获取授权的数据规则
     * @param grantedAuthorityList grantedAuthorityList
     * @return Set<SysRoleDataRuleDto>
     */
    private Set<SysRoleDataRuleDto> dataInfo(List<SimpleGrantedAuthority> grantedAuthorityList) {
        Set<SysRoleDataRuleDto> result = CollUtil.newHashSet();
        grantedAuthorityList.forEach( authority -> {
            if ( !StringUtils.equals(authority.getAuthority(), SecurityConstants.BASE_ROLE) ) {
                Set<SysRoleDataRuleDto> dInfo = roleDataService.selectByRole(authority.getAuthority());
                if (CollUtil.isNotEmpty(dInfo)) CollUtil.addAll(result, dInfo);
            }
        });

        return result;
    }
}
