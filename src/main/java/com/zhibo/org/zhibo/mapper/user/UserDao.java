package com.zhibo.org.zhibo.mapper.user;


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
    UserBean getUserById(int id);

    /**
     * 添加UserBean对象到数据库
     * @param user 要添加的UserBean对象
     */
    void addUser(UserBean user);

    /**
     * 根据唯一标识Account从数据库中查询用户
     * @param account 唯一Account
     * @return 查询到的用户 查到：返回UserBean对象 没查到：返回null;
     */
    UserBean getUserByAccount(String account);
}
