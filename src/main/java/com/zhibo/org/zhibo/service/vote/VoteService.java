package com.zhibo.org.zhibo.service.vote;

import com.zhibo.org.zhibo.entity.Vote;

import java.util.List;

/**
 * 赞和踩的功能业务
 * @author LS
 */
public interface VoteService {

    /**
     * 点赞和差评操作 业务逻辑
     * @param vote 点赞或差评实体对象
     */
    void likeAndDislikeArticle(Vote vote);

    /**
     * 通过用户ID查询该用户的点赞或差评状态
     * @param vote 点赞或差评的实体对象
     * @return 点赞或差评的实体对象
     */
    Vote findVoteByUserId(Vote vote);

    /**
     * 查询该用户所有的点赞
     * @param voterId 用户唯一标识ID
     * @return vote的List集合
     */
    List<Vote> findListVoteByUserId(String voterId);
}
