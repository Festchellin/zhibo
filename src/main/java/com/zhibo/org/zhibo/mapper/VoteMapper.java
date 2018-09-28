package com.zhibo.org.zhibo.mapper;

import com.zhibo.org.zhibo.entity.Vote;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 点赞和踩功能的数据库操作
 * @author LS
 */
@Mapper
@Repository
public interface VoteMapper {

    /**
     * 点赞和差评操作 数据库操作
     * @param vote 点赞或差评实体对象
     */
    void likeAndDislikeArticle(Vote vote);

    /**
     * 第一次差评或点赞操作 数据库操作
     * @param vote 差评实体对象
     */
    void firstAppraisalArticle(Vote vote);

    /**
     * 根据用户的唯一标识ID查找该用户是否有点赞或差评操作
     * @param vote 点赞或差评操作实体对象
     * @return 点赞或差评操作实体对象
     */
    Vote findVoteByUserId(Vote vote);

    /**
     * 取消点赞或者差评
     * @param vote 点赞或者差评的实体对象
     */
    void cancleVote(Vote vote);

    /**
     * 从无评价状态改变到点赞或者差评状态
     * @param vote 点赞或差评实体对象
     */
    void changToLikesOrDisLikes(Vote vote);

    /**
     * 查询该用户所有的点赞
     * @param voterId 用户唯一标识ID
     * @return vote的List集合
     */
    List<Vote> findListVoteByUserId(String voterId);

}
