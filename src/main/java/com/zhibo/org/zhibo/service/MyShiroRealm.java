package com.zhibo.org.zhibo.service;

import com.zhibo.org.zhibo.entity.User;
import com.zhibo.org.zhibo.service.permission.RolePermissionService;
import com.zhibo.org.zhibo.service.user.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

/**
 * 自定义ShiroRealm对象
 * @author LS
 * @date 2018/9/20
 */
public class MyShiroRealm extends AuthorizingRealm {
    @Autowired
    public RolePermissionService rolePermissionService;
    @Autowired
    public UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("————权限认证————");
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //获得该用户角色
        String role = rolePermissionService.getRoleByAccount(username);
        Set<String> set = new HashSet<>();
        //需要将 role 封装到 Set 作为 info.setRoles() 的参数
        set.add(role);
        //设置该用户拥有的角色
        info.setRoles(set);
        return info;


    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //获取用户的输入的账号.
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        System.out.println(authenticationToken.getCredentials());
        System.out.println("用户输入的账号："+token.getUsername());
        //通过username从数据库中查找 User对象，如果找到，没找到.
        //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
        User user = userService.getUserByAccount(token.getUsername());
        if(user == null){
            throw new AccountException("用户名或密码错误！！");
        }
        if (!user.getPassword().equals(new String((char[])token.getCredentials()))){
            throw new AccountException("用户名或密码错误！！");
        }

        return new SimpleAuthenticationInfo(token.getPrincipal(), user.getPassword(), getName());
    }
}
