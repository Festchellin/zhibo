package com.zhibo.org.zhibo.controller.user;

import com.zhibo.org.zhibo.entity.user.UserBean;
import com.zhibo.org.zhibo.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author ls
 * @date 2018/9/17 11:00
 */
@Controller
public class UserRegisterController {

    @Autowired
    public UserService userService;

    @RequestMapping("/userRegister")
    public ModelAndView userRegister(UserBean userBean){
        boolean register = userService.userRegister(userBean);
        ModelAndView modelAndView = new ModelAndView();
        if (register){
            modelAndView.addObject("nickName",userBean.getName());
            modelAndView.setViewName("index.html");
        }else {
            modelAndView.addObject("status","注册失败");
            modelAndView.setViewName("index.html");
        }
        return modelAndView;
    }
}
