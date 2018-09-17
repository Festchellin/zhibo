package com.zhibo.org.zhibo.controller.user;

import com.zhibo.org.zhibo.entity.user.UserBean;
import com.zhibo.org.zhibo.service.user.UserService;
import com.zhibo.org.zhibo.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author ls
 */
@Controller
public class UserLoginController {
    @Autowired
     public UserService userService;

    @RequestMapping("/userLogin")
    public ModelAndView userLogin(UserBean userBean){
        UserBean user = userService.getUserByAccount(userBean.getAccount());

        ModelAndView modelAndView = new ModelAndView();
        //对参数中的密码进行加密、加盐
        String md5Pass = MD5Util.getMD5Str(userBean.getPassword()) + userBean.getAccount();

        if (null == user || !md5Pass.equals(user.getPassword())){
            modelAndView.addObject("status","账号或者密码错误！");

        }else {
            modelAndView.addObject("status","登录成功");
        }
        modelAndView.setViewName("index.html");
        return modelAndView;
    }


}
