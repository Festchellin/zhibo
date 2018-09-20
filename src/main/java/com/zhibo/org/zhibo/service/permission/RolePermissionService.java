package com.zhibo.org.zhibo.service.permission;

public interface RolePermissionService {
    /**
     * 通过用户名查询该用户的角色
     * @param account 用户名
     * @return 用户角色
     */
    public String getRoleByAccount(String account);

    /**
     * 根据角色查询该角色拥有的权限
     * @param role 角色名
     * @return 拥有的权限
     */
    public String getPermissionByRole(String role);

}
