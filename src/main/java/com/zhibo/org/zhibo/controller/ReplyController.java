package com.zhibo.org.zhibo.controller;

import com.zhibo.org.zhibo.entity.Reply;
import com.zhibo.org.zhibo.service.ReplyService;
import com.zhibo.org.zhibo.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/reply")
public class ReplyController {
    @Autowired
    private ReplyService replyService;

    @GetMapping("/{id}")
    public Object getReplyById(@PathVariable String id){
        Reply reply = replyService.getReplyById(id);
        Map map;
        if (reply != null){
            map = ResponseUtil.loadResponseWithData("1", "获取回复成功",reply);
        }else {
            map = ResponseUtil.loadResponseWithoutData("-1", "获取回复失败");
        }

        return map;
    }

    @GetMapping("/article/{id}")
    public Object getRepliesByArticleId(@PathVariable String id, @Nullable Integer start,@Nullable Integer limit){
        List<Reply> replyList = replyService.getRepliesByArticleId(id, start, limit);
        Map map;
        if (replyList != null){
            map = ResponseUtil.loadResponseWithData("1", "获取文章回复成功",replyList);
        }else {
            map = ResponseUtil.loadResponseWithoutData("-1", "获取文章回复失败");
        }

        return map;
    }
    @PostMapping()
    public Object createReply(Reply reply){
        System.out.println(reply);
        Boolean result = replyService.createReply(reply);
        Map map;
        if (result){
            map = ResponseUtil.loadResponseWithoutData("1", "创建回复成功");
        }else {
            map = ResponseUtil.loadResponseWithoutData("1", "创建回复成功");
        }
        return map;
    }
    @DeleteMapping("/{id}")
    public Object deleteReplyById(@PathVariable String id){
        Boolean result = replyService.deleteReplyById(id);
        Map map;
        if (result){
             map = ResponseUtil.loadResponseWithoutData("1", "删除回复成功");
        }else {
            map = ResponseUtil.loadResponseWithoutData("-1", "删除回复失败");
        }
        return map;
    }
    @PutMapping()
    public Object updateReplyById(Reply reply){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Boolean result = replyService.updateReplyById(reply);
        Map map;
        if (result){
            map = ResponseUtil.loadResponseWithoutData("1", "修改回复成功");
        }else {
            map = ResponseUtil.loadResponseWithoutData("-1", "修改回复失败");
        }
        return map;
    }
}
