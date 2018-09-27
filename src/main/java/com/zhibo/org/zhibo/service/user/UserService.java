package com.zhibo.org.zhibo.service.user;

import com.zhibo.org.zhibo.entity.User;
import com.zhibo.org.zhibo.entity.user.UserBean;

public interface UserService {
    /**
     * 根据用户的唯一标识id查询用户
     * @param id 用户的唯一标识Id
     * @return 查询到的用户 查到：返回User 没查到：返回null;
     */
    User getUserById(int id);

    /**
     * 根据唯一标识Account查询用户
     * @param Account 唯一Account
     * @return 查询到的用户 查到：返回UserBean对象 没查到：返回null;
     */
    User getUserByAccount(String Account);


    /**
     * 用户注册业务
     * @param userBean 用户实体对象
     * @return 成功返回true 失败返回false
     */
    boolean userRegister(User userBean);

    /**
     * 邮件激活账号业务
     * @param code 激活码
     * @return 成功激活返回true,激活失败返回false
     */
    boolean mailActivation(String code);

    /**
     * 更新用户信息
     * @param user 更新用户的实体
     * @return 更新成功后的用户实体
     */
    User updateUser(User user);

    /**
     * 判断账号是否已经有人注册
     * @param user 用户对象实体
     * @return 账号已被注册，返回true; 账号未被注册，返回false;
     */
    boolean judgmentExistUser(User user);


}
