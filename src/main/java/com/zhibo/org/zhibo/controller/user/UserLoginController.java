package com.zhibo.org.zhibo.controller.user;

import com.zhibo.org.zhibo.entity.user.UserBean;
import com.zhibo.org.zhibo.service.user.UserService;
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

        return null;
    }


}
