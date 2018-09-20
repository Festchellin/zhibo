package com.zhibo.org.zhibo.mapper.permission;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


/**
 * 从持久化层获取用户权限信息
 * @author LS
 * @date 2018/9/20
 */
@Mapper
@Repository
public interface RoleMapper {
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
