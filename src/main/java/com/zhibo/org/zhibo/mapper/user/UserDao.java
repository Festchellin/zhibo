package com.zhibo.org.zhibo.mapper.user;


import com.zhibo.org.zhibo.entity.User;
import com.zhibo.org.zhibo.entity.user.UserBean;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author LS
 * @date 2018/9/17
 */
@Mapper
@Repository
public interface UserDao {
    /**
     * 根据唯一标识ID查询用户
     * @param id 唯一标识ID
     * @return UserBean实体对象
     */
    User getUserById(int id);

    /**
     * 添加UserBean对象到数据库
     * @param user 要添加的UserBean对象
     */
    void addUser(User user);

    /**
     * 根据唯一标识Account从数据库中查询用户
     * @param account 唯一Account
     * @return 查询到的用户 查到：返回UserBean对象 没查到：返回null;
     */
    User getUserByAccount(String account);

    /**
     * 通过激活码查找用户
     * @param code 激活码
     * @return UserBean实体对象
     */
    User getUserByCode(String code);

    /**
     * 根据用户修改user表中的state字段
     * @param userBean 用户实体对象
     */
    void updateUserState(User userBean);

    /**
     * 更新用户信息
     * @param user 待更新用户实体
     */
    void updateUser(User user);
}
