package com.zhibo.org.zhibo.controller.user;

import com.zhibo.org.zhibo.entity.user.UserBean;
import com.zhibo.org.zhibo.service.user.UserService;
import com.zhibo.org.zhibo.util.MailUtil;
import com.zhibo.org.zhibo.util.StringGenerator;
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

    /**
     * 用户注册处理方法
     * @param userBean 用于接收参数
     * @return 返回ModelAndView
     */
    @RequestMapping("/userRegister")
    public ModelAndView userRegister(UserBean userBean){
        //生成激活码
        String code = StringGenerator.UUIDGenerator();
        //将激活码存到数据库
        userBean.setCode(code);
        //进行注册业务
        boolean register = userService.userRegister(userBean);
        ModelAndView modelAndView = new ModelAndView();
        if (register){
            modelAndView.addObject("nickName",userBean.getName());

            //开启一个线程发送激活邮件给注册用户
            new Thread(new MailUtil(userBean.getEmail(),code)).start();
            modelAndView.setViewName("index.html");
        }else {
            modelAndView.addObject("status","注册失败");
            modelAndView.setViewName("user/login");
        }
        return modelAndView;
    }

    /**
     * 通过邮件激活账号
     * @param code 激活码
     * @return 返回ModelAndView
     */
    @RequestMapping("/mailActivation")
    public ModelAndView mailActivation(String code){
        ModelAndView modelAndView = new ModelAndView();
        if (null == code || code.equals("")){
            modelAndView.addObject("status","激活码出现错误！！");
            modelAndView.setViewName("index.html");
            return modelAndView;
        }
        boolean activation = userService.mailActivation(code);
        if (activation){
            //激活成功跳转至个人空间
            modelAndView.setViewName("/index");
        }else {
            //激活失败跳转至重发激活邮件页面
            modelAndView.setViewName("index.html");
        }
        return modelAndView;
    }
}
