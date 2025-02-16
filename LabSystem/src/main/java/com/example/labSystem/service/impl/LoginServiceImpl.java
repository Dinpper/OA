package com.example.labSystem.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.example.labSystem.common.BusinessException;
import com.example.labSystem.domain.Menus;
import com.example.labSystem.domain.Role;
import com.example.labSystem.dto.CommonRequestQto;
import com.example.labSystem.dto.LoginDto;
import com.example.labSystem.dto.PermissionsInfoDto;
import com.example.labSystem.dto.UserDto;
import com.example.labSystem.mappers.UsersMapper;
import com.example.labSystem.service.LoginService;
import com.example.labSystem.service.MenuService;
import com.example.labSystem.service.PermissionService;
import com.example.labSystem.service.RoleService;
import com.example.labSystem.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UsersMapper usersMapper;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private MenuService menuService;

    @Override
    public LoginDto loginIn(CommonRequestQto qto) {
        LoginDto dto = new LoginDto();
        String userName = usersMapper.queryUserNameByAccount(qto.getAccount());
        dto.setUserName(userName);
        return dto;
    }

    @Override
    public Boolean authenticate(String account, String password) {
        String md5Password = usersMapper.queryPassword(account);
        if (!Objects.equals(MD5Util.md5(password), md5Password)) {
            throw new BusinessException(399, "账号或命密码错误");
        }
        return true;
    }

    @Override
    public PermissionsInfoDto info(String account) {
        PermissionsInfoDto dto = new PermissionsInfoDto();
        //查询用户角色
        Role role = roleService.queryUserRole(account);

        UserDto user = new UserDto();
        user.setAccount(account);
        user.setRole(role.getRoleName());
        dto.setUser(user);

        //查询权限列表
        List<String> permissions = permissionService.queryPermissionsByRole(role.getRoleId());
        dto.setPermissions(permissions);

        //查询可访问的菜单
        List<Menus> menus = menuService.queryMenusByRole(role.getRoleId());
        dto.setMenus(menus);

        return dto;
    }

}
