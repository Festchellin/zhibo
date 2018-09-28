package com.zhibo.org.zhibo.controller;

import com.zhibo.org.zhibo.entity.Vote;
import com.zhibo.org.zhibo.service.vote.VoteService;
import com.zhibo.org.zhibo.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
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
        Map<String,Object> state = new HashMap<>(1);
        state.put("newState",vote1);
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

    @PostMapping("/{voterId}")
    public Object findListVoteByUserId(@PathVariable String voterId){
        Map map;
        List<Vote> listVoteByUserId = voteService.findListVoteByUserId(voterId);
        map = ResponseUtil.loadResponseWithData("1","查询成功！！",listVoteByUserId);
        return map;
    }
}
