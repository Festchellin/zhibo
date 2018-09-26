package com.zhibo.org.zhibo.mapper;

import com.zhibo.org.zhibo.entity.Vote;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

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

}
