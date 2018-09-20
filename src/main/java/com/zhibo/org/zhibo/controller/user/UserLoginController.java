package com.zhibo.org.zhibo.controller.user;

import com.zhibo.org.zhibo.entity.User;
import com.zhibo.org.zhibo.entity.user.UserBean;
import com.zhibo.org.zhibo.service.user.UserService;
import com.zhibo.org.zhibo.util.MD5Util;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
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
    public ModelAndView userLogin(User userBean){
        ModelAndView modelAndView = new ModelAndView();
        //对参数中的密码进行加密、加盐
        String md5Pass = MD5Util.getMD5Str(userBean.getPassword()) + userBean.getAccount();
        // 从SecurityUtils里边创建一个 subject
        Subject subject = SecurityUtils.getSubject();
        // 在认证提交前准备 token（令牌）
        UsernamePasswordToken token = new UsernamePasswordToken(userBean.getAccount(),md5Pass);
        // 执行认证登陆
        try {
            subject.login(token);
            modelAndView.addObject("status","登录成功");
            modelAndView.setViewName("/user/center");
        }catch (Exception e){
            modelAndView.addObject("status","用户名或密码错误");
            modelAndView.setViewName("/user/login");
        }

        System.out.println("token>>>>>>"+token.toString());
        System.out.println("执行登录》》》》");

//        UserBean user = userService.getUserByAccount(userBean.getAccount());
//
//        ModelAndView modelAndView = new ModelAndView();
//        //对参数中的密码进行加密、加盐
//        String md5Pass = MD5Util.getMD5Str(userBean.getPassword()) + userBean.getAccount();
//
//        if (null == user || !md5Pass.equals(user.getPassword())){
//            modelAndView.addObject("status","账号或者密码错误！");
//            modelAndView.setViewName("user/login");
//        }else {
//            modelAndView.addObject("status","登录成功");
//            modelAndView.setViewName("index");
//        }



        return modelAndView;
    }

    @RequestMapping("/logout")
    public ModelAndView logout() {
        System.out.println("开始注销》》》");
        Subject subject = SecurityUtils.getSubject();
        //注销
        subject.logout();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/user/login");
        return modelAndView;
    }


}
