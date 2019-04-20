package com.wang.jmonkey.modules.sys.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.wang.jmonkey.common.constant.CommonConstant;
import com.wang.jmonkey.common.constant.SecurityConstants;
import com.wang.jmonkey.common.model.vo.UserVo;
import com.wang.jmonkey.common.utils.RedisUtil;
import com.wang.jmonkey.common.utils.UserUtils;
import com.wang.jmonkey.modules.sys.model.dto.SysSystemDto;
import com.wang.jmonkey.modules.sys.model.dto.SysUserDto;
import com.wang.jmonkey.modules.sys.model.dto.SysUserInfoDto;
import com.wang.jmonkey.modules.sys.model.entity.SysDept;
import com.wang.jmonkey.modules.sys.model.entity.SysRole;
import com.wang.jmonkey.modules.sys.model.entity.SysUser;
import com.wang.jmonkey.modules.sys.mapper.SysUserMapper;
import com.wang.jmonkey.modules.sys.model.param.SysUserParam;
import com.wang.jmonkey.modules.sys.service.*;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author HeJiawang
 * @since 2018-12-07
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    /**
     * 用户信息密码加密规则
     */
    private static final PasswordEncoder ENCODER = new BCryptPasswordEncoder();

    /**
     * 用户部门信息 service
     */
    @Autowired
    private ISysUserDeptService userDeptService;

    /**
     * 用户角色信息 service
     */
    @Autowired
    private ISysUserRoleService userRoleService;

    /**
     * 资源信息 service
     */
    @Autowired
    private ISysResourceService resourceService;

    /**
     * roleResourceService
     */
    @Autowired
    private ISysRoleResourceService roleResourceService;

    /**
     * 用户mapper
     */
    @Autowired
    private SysUserMapper mapper;

    /**
     * redis util
     */
    @Autowired
    private RedisUtil redisUtil;

    /**
     * 获取用户分页信息
     * @param page
     * @param param
     * @return
     */
    @Override
    public Page<SysUserDto> selectPage(Page<SysUserDto> page, SysUserParam param) {
        int current = page.getCurrent(),size = page.getSize();

        List<SysUserDto> userDtoList = mapper.selectDtoPage(param);
        int start = size * ( current - 1 ) > userDtoList.size() ? userDtoList.size() : size * ( current - 1 ),
                end = size * current > userDtoList.size() ? userDtoList.size() : size * current;

        // 无法在sql中进行分页， 如一个用户有三个角色，limit会当2条数据处理，而业务应当是一条数据处理
        Page<SysUserDto> userDtoPage = new Page<>();
        userDtoPage.setRecords(userDtoList.subList(start, end))
                .setTotal(userDtoList.size())
                .setCurrent(page.getCurrent()).setSize(page.getSize());

        return userDtoPage;
    }

    /**
     * 保存用户信息
     * @param userParam 用户param
     * @return 是否保存成功
     */
    @Transactional
    @Override
    public Boolean save(SysUserParam userParam) {
        SysUser sysUser = userParam.converToEntity();
        // 加密用户密码
        sysUser.setPassword(ENCODER.encode(userParam.getPassword()));

        return super.insert(sysUser)
                && userDeptService.mergeDepts(sysUser.getId(), userParam.getDeptIds())
                && userRoleService.mergeRoles(sysUser.getId(), userParam.getRoleIds());
    }

    /**
     * 修改用户信息
     * @param userParam 用户param
     * @return 是否修改成功
     */
    @Transactional
    @Override
    public Boolean modify(SysUserParam userParam) {
        SysUser sysUser = userParam.converToEntity();
        return super.updateById(sysUser)
                && userDeptService.mergeDepts(sysUser.getId(), userParam.getDeptIds())
                && userRoleService.mergeRoles(sysUser.getId(), userParam.getRoleIds());
    }

    /**
     * 获取用户dto信息
     * @param id 用户id
     * @return
     */
    @Override
    public SysUserDto selectDtoById(Serializable id) {
        return mapper.selectDtoById(id);
    }

    /**
     * 校验用户登录名是否重复
     * @param sysUser 用户登录名信息
     * @return
     */
    @Override
    public Boolean checkUsername(SysUser sysUser) {
        return  mapper.checkUsername(sysUser) > 0;
    }

    /**
     * 修改用户登录密码
     * @param user 用户id以及新的密码
     * @return
     */
    @Override
    public Boolean modifyPassword(SysUser user) {
        user.setPassword( ENCODER.encode(user.getPassword()) );
        return this.updateById(user);
    }

    /**
     * 删除用户信息
     * @param id 用户id
     * @return 是否删除成功
     */
    @Transactional
    @Override
    public boolean deleteById(Serializable id) {
        if (String.valueOf(id).equals(CommonConstant.SYS_ID)) return false;

        return super.deleteById(id)
                && userDeptService.deleteByUserId(id)
                && userRoleService.deleteByUserId(id);
    }

    /**
     * 根据登陆用户名获取用户登陆信息
     * @param username 登陆用户名
     * @return 用户登陆信息
     */
    @Override
    public UserVo loadUserByUsername(String username) {
        return mapper.loadUserByUsername(username);
    }

    /**
     * 根据用户登陆名称获取用户信息
     * @return userinfo
     */
    @Override
    public SysUserInfoDto getCurrentUserInfo() {
        SysUser user = this.selectByUsername(UserUtils.getUser());
        if (user == null) return null;

        List<SysRole> roleList = userRoleService.selectRoleByUserId(user.getId());
        List<SysDept> deptList = userDeptService.selectDeptByUserId(user.getId());
        List<String> permissionList = roleResourceService.selectPermissionByRoles(roleList);
        boolean isGuide = resourceService.haveGuide();
        List<SysSystemDto> systemList = resourceService.guideInfo();

        return new SysUserInfoDto()
                .setUser(user).setRoleList(roleList).setDeptList(deptList)
                .setPermissionList(permissionList).setGuide(isGuide).setSystemList(systemList);
    }

    /**
     * 根据用户名称获取用户信息
     * @param username 用户登录名称
     * @return 用户信息
     */
    @Override
    public SysUser selectByUsername(String username){
        EntityWrapper<SysUser> wrapper = new EntityWrapper<>();
        wrapper.setEntity(new SysUser().setUsername(username));

        return super.selectOne(wrapper);
    }

    /**
     * 获取所有用户信息
     * @return List<SysUser>
     */
    @Override
    public List<SysUser> selectAll() {
        EntityWrapper<SysUser> wrapper = new EntityWrapper<>();
        return super.selectList(wrapper);
    }

    /**
     * 获取登录错误次数
     * @param userName userName
     * @return 登录错误次数
     */
    @Override
    public Integer loginErrorNum(String userName) {
        String key = SecurityConstants.LOIN_CODE_PREFIX + userName;
        return redisUtil.get(key) == null ? 0 : Integer.valueOf(redisUtil.get(key).toString());
    }
}
