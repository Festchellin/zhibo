package com.zhibo.org.zhibo.controller;

import com.zhibo.org.zhibo.entity.Vote;
import com.zhibo.org.zhibo.service.vote.VoteService;
import com.zhibo.org.zhibo.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author LS
 */
@RestController
@RequestMapping("/vote")
public class VoteController {
    @Autowired
    public VoteService voteService;

    @PutMapping
    public Object likeAndDislikeAction(Vote vote){
        Map map;
        voteService.likeAndDislikeArticle(vote);
        map = ResponseUtil.loadResponseWithoutData("1","操作成功！！");
        return map;
    }

}
