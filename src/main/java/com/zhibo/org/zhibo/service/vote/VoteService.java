package com.zhibo.org.zhibo.service.vote;

import com.zhibo.org.zhibo.entity.Vote;

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
}
