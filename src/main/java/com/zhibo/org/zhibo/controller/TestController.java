package com.zhibo.org.zhibo.controller;

import com.zhibo.org.zhibo.entity.User;
import com.zhibo.org.zhibo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {
    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/")
    public String getUserById(Model model){
        User user = userMapper.getUserById(1);
        model.addAttribute("user",user);
        return "/user/index";
    }
}
