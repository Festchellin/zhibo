package com.zhibo.org.zhibo.entity;

import java.io.Serializable;
import java.util.List;

public class SysRole implements Serializable {
    // 编号
    private Integer id;
    // 角色标识程序中判断使用,如"admin",这个是唯一的:
    private String role;
    // 角色描述,UI界面显示使用
    private String description;
    // 是否可用,如果不可用将不会添加给用户
    private Boolean available = Boolean.FALSE;

    //角色 -- 权限关系：多对多关系;
    private List<SysPermission> permissions;

    // 用户 - 角色关系定义;
    private List<User> userInfos;// 一个角色对应多个用户

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public List<SysPermission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<SysPermission> permissions) {
        this.permissions = permissions;
    }

    public List<User> getUserInfos() {
        return userInfos;
    }

    public void setUserInfos(List<User> userInfos) {
        this.userInfos = userInfos;
    }
}
