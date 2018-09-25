package com.zhibo.org.zhibo.controller;

import com.zhibo.org.zhibo.entity.Article;
import com.zhibo.org.zhibo.entity.Reply;
import com.zhibo.org.zhibo.entity.User;
import com.zhibo.org.zhibo.mapper.ReplyMapper;
import com.zhibo.org.zhibo.mapper.UserMapper;
import com.zhibo.org.zhibo.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TestController {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ReplyService replyService;

    @RequestMapping("/")
    public String getUserById(Model model){
        return "/index";
    }
    @RequestMapping("/user/loginView")
    public String regist(Model model){
        return "/user/login";
    }

    @RequestMapping("/uploadImg")
    public String upload() {
        return "/test/uploadImg";
    }

    @RequestMapping("/user/center")
    public String center() {
        return "/user/center";
    }

    @RequestMapping("/test/article")
    public String redirectArticleAddPage() {
        return "/article/add";
    }

    @PostMapping("/article")
    @ResponseBody
    public Object addArticle(Article article) {
        System.out.println(article);
        return "OK";
    }

    @RequestMapping("/uploadFile")
    public String uploadFile(){
        return "/test/testfileUpload";
    }

    @RequestMapping("/403")
    public String noPermission(){
        return "/error/403";
    }
    @RequestMapping("/reply/article/{id}")
    @ResponseBody
    public Object testReply(@PathVariable String id, @Nullable Integer page, @Nullable Integer size){
        List<Reply> repliesByArticleId = replyService.getRepliesByArticleId(id,page,size);
        for (Reply reply : repliesByArticleId) {
            System.out.println(reply);
        }
        return repliesByArticleId;
    }

}
