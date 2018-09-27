package com.zhibo.org.zhibo.controller;

import com.zhibo.org.zhibo.entity.Vote;
import com.zhibo.org.zhibo.service.vote.VoteService;
import com.zhibo.org.zhibo.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
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
        Vote vote1 = voteService.findVoteByUserId(vote);
        int stateValue = vote1.getStates();
        Map<String,Integer> state = new HashMap<>(1);
        state.put("newState",stateValue);
        if (1 == vote.getStates()){
            map = ResponseUtil.loadResponseWithData("1","点赞成功!!",state);
        }else if(0 == vote.getStates()){
            map = ResponseUtil.loadResponseWithData("1","取消成功!!",state);
        }else if (-1 == vote.getStates()){
            map = ResponseUtil.loadResponseWithData("1","差评成功!!",state);

        }else {
            map = ResponseUtil.loadResponseWithData("-1","操作失败！！",state);

        }
        return map;
    }

}
