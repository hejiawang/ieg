package com.wang.jmonkey.common.http.config;

import com.wang.jmonkey.common.constant.SecurityConstants;
import com.wang.jmonkey.common.model.vo.RoleVo;
import com.wang.jmonkey.common.model.vo.UserVo;
import com.wang.jmonkey.common.utils.UserUtils;
import com.wang.jmonkey.modules.sys.service.ISysUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @Description: 将token转换为UserVo
 * @Auther: HeJiawang
 * @Date: 2019/1/10
 */
@Slf4j
@Configuration
public class TokenArgumentResolverConfig implements HandlerMethodArgumentResolver {

    /**
     * cacheManager
     */
    private CacheManager cacheManager;

    /**
     * userService
     */
    private ISysUserService userService;

    /**
     * TokenArgumentResolverConfig
     * @param cacheManager cacheManager
     * @param userService userService
     */
    public TokenArgumentResolverConfig(CacheManager cacheManager, ISysUserService userService) {
        this.cacheManager = cacheManager;
        this.userService = userService;
    }

    /**
     * api接口层，参数中有UserVo类的，填充UserVo类信息
     * @param methodParameter methodParameter
     * @return 是否转换
     */
    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.getParameterType().equals(UserVo.class);
    }

    /**
     * 转换参数
     * @param methodParameter methodParameter
     * @param modelAndViewContainer modelAndViewContainer
     * @param nativeWebRequest nativeWebRequest
     * @param webDataBinderFactory webDataBinderFactory
     * @return UserVo
     */
    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer,
                                  NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory){
        HttpServletRequest request = nativeWebRequest.getNativeRequest(HttpServletRequest.class);
        String token = UserUtils.getToken(request);
        if (StringUtils.isBlank(token)) return null;

        // 从缓存中获取UserVo
        Optional<UserVo> optional = Optional.ofNullable(
                cacheManager.getCache(SecurityConstants.TOKEN_USER_DETAIL).get(token, UserVo.class)
        );
        if(optional.isPresent()) return optional.get(); // 如果缓存中有，直接返回缓存中的UserVo信息
        else return optional.orElseGet(() -> generatorByToken(request, token)); // 缓存中没有信息，获取信息并填到缓存中
    }

    /**
     * 获取uservo信息并填到缓存中
     * @param request request
     * @param token token
     * @return uservo
     */
    private UserVo generatorByToken(HttpServletRequest request, String token) {
        String username = UserUtils.getUserName(request);
        String userId = userService.selectByUsername(username).getId();

        // TODO 可能会需要role id
        List<String> roles = UserUtils.getRole(request);
        List<RoleVo> roleList = new ArrayList<>();
        roles.forEach(role -> roleList.add( new RoleVo().setCode(role) ) );

        // 构建UserVo信息并加入缓存中
        UserVo userVo = new UserVo().setId(userId).setUsername(username).setRoleList(roleList);
        cacheManager.getCache(SecurityConstants.TOKEN_USER_DETAIL).put(token, userVo);
        return userVo;
    }

}
