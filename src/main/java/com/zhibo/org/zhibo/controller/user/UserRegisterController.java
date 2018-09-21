package com.zhibo.org.zhibo.controller.user;

import com.zhibo.org.zhibo.entity.User;
import com.zhibo.org.zhibo.entity.user.UserBean;
import com.zhibo.org.zhibo.service.user.UserService;
import com.zhibo.org.zhibo.util.MailUtil;
import com.zhibo.org.zhibo.util.StringGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;

/**
 * @author ls
 * @date 2018/9/17 11:00
 */
@Controller
public class UserRegisterController {

    @Autowired
    public UserService userService;


}
