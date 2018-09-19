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
        return "/index";
    }
    @RequestMapping("/user/login")
    public String regist(Model model){
        return "/user/login";
    }

    @RequestMapping("/uploadImg")
    public String upload() {
        return "/test/uploadImg";
    }

    @RequestMapping("/uploadFile")
    public String uploadFile(){
        return "/test/testfileUpload";
    }
}
