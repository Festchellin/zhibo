package com.zhibo.org.zhibo.controller.user;

import com.zhibo.org.zhibo.entity.User;
import com.zhibo.org.zhibo.entity.user.UserBean;
import com.zhibo.org.zhibo.service.user.UserService;
import com.zhibo.org.zhibo.util.MD5Util;
import com.zhibo.org.zhibo.util.MailUtil;
import com.zhibo.org.zhibo.util.ResponseUtil;
import com.zhibo.org.zhibo.util.StringGenerator;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ls
 * @date 2018/9/21
 */
@RestController
@RequestMapping("/user")
public class UserLoginController {
    @Autowired
     public UserService userService;

    @PostMapping("/login")
    public Object userLogin(@RequestBody User userBean,boolean rememberMe){

        Map<String,Object> responseMap = new HashMap<>(3);
        Map<String,Object> dataMap = new HashMap<>(3);
        System.out.println(rememberMe);
        //对参数中的密码进行加密、加盐
        String md5Pass = MD5Util.getMD5Str(userBean.getPassword()) + userBean.getAccount();
        // 从SecurityUtils里边创建一个 subject
        Subject subject = SecurityUtils.getSubject();
        // 在认证提交前准备 token（令牌）
        UsernamePasswordToken token = new UsernamePasswordToken(userBean.getAccount(),md5Pass,rememberMe);

        // 执行认证登陆
        try {
            subject.login(token);
            User user = userService.getUserByAccount(userBean.getAccount());
            user.setPassword("");
            user.setCode("");
            dataMap.put("user",user);
            dataMap.put("url","/index");
            dataMap.put("token",token);
            responseMap.put("error_code","1");
            responseMap.put("message","登录成功！！");
            responseMap.put("data",dataMap);
        }catch (Exception e){
            dataMap.put("url","/user/login");
            responseMap.put("error_code","-1");
            responseMap.put("message","账号或者密码错误！！");
            responseMap.put("data",dataMap);
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



        return responseMap;
    }

    @PostMapping("/logout")
    public Object logout() {
        System.out.println("开始注销》》》");
        Subject subject = SecurityUtils.getSubject();
        //注销
        subject.logout();
        HashMap<String,Object> responseMap = new HashMap<>(3);
        HashMap<String,Object> dataMap = new HashMap<>(3);
        dataMap.put("url","/user/login");
        responseMap.put("error_code","1");
        responseMap.put("message","登出成功！！");
        responseMap.put("data",dataMap);
        return responseMap;
    }


    /**
     * 用户注册处理方法
     * @param userBean 用于接收参数
     * @return 返回ModelAndView
     */
    @PostMapping("/register")
    public Object userRegister(@RequestBody User userBean){
        HashMap<String,Object> responseMap = new HashMap<>(3);
        HashMap<String,Object> dataMap = new HashMap<>(3);
        //生成激活码
        String code = StringGenerator.UUIDGenerator();
        //将激活码存到数据库
        userBean.setCode(code);
        //进行注册业务
        boolean register = userService.userRegister(userBean);
        if (register){
            dataMap.put("nickName",userBean.getName());
            //开启一个线程发送激活邮件给注册用户
            new Thread(new MailUtil(userBean.getEmail(),code)).start();
            User userByAccount = userService.getUserByAccount(userBean.getAccount());
            userByAccount.setPassword("");
            dataMap.put("user",userByAccount);
            dataMap.put("url","/index");
            responseMap.put("error_code","1");
            responseMap.put("message","注册成功！！");
            responseMap.put("data",dataMap);

        }else {
            dataMap.put("url","user/login");
            responseMap.put("error_code","-1");
            responseMap.put("message","注册失败！！");
            responseMap.put("data",dataMap);
        }
        return responseMap;
    }

    /**
     * 通过邮件激活账号
     * @param code 激活码
     * @return 返回ModelAndView
     */
    @RequestMapping("/mailActivation")
    public Object mailActivation(String code){
        HashMap<String,Object> responseMap = new HashMap<>(3);
        HashMap<String,Object> dataMap = new HashMap<>(3);
        if (null == code || code.equals("")){
            dataMap.put("url","/index");
            responseMap.put("error_code","-1");
            responseMap.put("message","激活失败！！");
            responseMap.put("data",dataMap);
            return responseMap;
        }
        boolean activation = userService.mailActivation(code);
        if (activation){
            //激活成功跳转至个人空间
            dataMap.put("url","/index");
            responseMap.put("error_code","1");
            responseMap.put("message","激活成功！！");
            responseMap.put("data",dataMap);
        }else {
            //激活失败跳转至重发激活邮件页面
            dataMap.put("url","/index");
            responseMap.put("error_code","-1");
            responseMap.put("message","激活失败！！");
            responseMap.put("data",dataMap);
        }
        return responseMap;
    }

    @PutMapping
    public Object updateUser(@RequestBody User user){
        HashMap<String,Object> responseMap = new HashMap<>(3);
        HashMap<String,Object> dataMap = new HashMap<>(3);

        User updateUser = userService.updateUser(user);

        dataMap.put("user",updateUser);

        responseMap.put("error_code","1");
        responseMap.put("message","更新成功！！");
        responseMap.put("data",dataMap);

        return responseMap;

    }

    /**
     * 判断账号是否已被注册
     * @param user 用户对象实体对象
     * @return
     */
    @PostMapping("/judgmentExistUser")
    public Object judgmentExistUser(@RequestBody User user){
        Map map;
        boolean existUser = userService.judgmentExistUser(user);
        if (existUser){
            map = ResponseUtil.loadResponseWithoutData("-1","账号已被注册！！");
        }else {
            map = ResponseUtil.loadResponseWithoutData("1","账号可以进行注册！！");
        }
        return map;
    }

    @PostMapping("/login/token")
    public Object loginByToken(@RequestBody UsernamePasswordToken token){
        Map map;
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            String username = token.getUsername();
            User userByAccount = userService.getUserByAccount(username);
            userByAccount.setPassword("");
            Map<String,Object> userMap = new HashMap<>(1);
            userMap.put("user",userByAccount);
            map = ResponseUtil.loadResponseWithData("1","登录成功",userMap);
        }catch (Exception e){
            map = ResponseUtil.loadResponseWithoutData("1","登录失败！！");
        }
        return map;
    }


}
