package com.zhibo.org.zhibo.service.permission;

import com.zhibo.org.zhibo.mapper.permission.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户权限处理业务逻辑
 * @author LS
 * @date 2018/9/20
 */
@Service
public class RolePermissionServiceImpl implements RolePermissionService{

    @Autowired
    public RoleMapper roleMapper;

    @Override
    public String getRoleByAccount(String account) {
        String role = roleMapper.getRoleByAccount(account);
        return role;
    }

    @Override
    public String getPermissionByRole(String role) {
        String permission = roleMapper.getPermissionByRole(role);
        return permission;
    }
}
