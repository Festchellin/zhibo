package com.zhibo.org.zhibo.service.impl.user;

import com.zhibo.org.zhibo.entity.User;
import com.zhibo.org.zhibo.entity.user.UserBean;
import com.zhibo.org.zhibo.mapper.user.UserDao;
import com.zhibo.org.zhibo.service.user.UserService;
import com.zhibo.org.zhibo.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author LS
 * @date 2018/9/17
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    public UserDao userDao;


    @Override
    public UserBean getUserById(int id) {
        UserBean user = userDao.getUserById(id);
        return user;
    }

    @Override
    public UserBean getUserByAccount(String account) {
        UserBean user = userDao.getUserByAccount(account);
        return user;
    }

    @Override
    public boolean userRegister(UserBean userBean) {
        if (null == userBean || null == userBean.getPassword() || userBean.getPassword().equals("")){
            return false;
        }
        String md5PassWord = MD5Util.getMD5Str(userBean.getPassword());
        UserBean user = this.getUserByAccount(userBean.getAccount());
        if (null == user){
            userBean.setPassword(md5PassWord+userBean.getAccount());
            userDao.addUser(userBean);
            return true;
        }else {
            return false;
        }

    }

    @Override
    public boolean mailActivation(String code) {
        if (null == code || code.equals("")){
            return false;
        }
        UserBean user = userDao.getUserByCode(code);
        user.setState(1);
        userDao.updateUserState(user);
        return true;
    }
}
