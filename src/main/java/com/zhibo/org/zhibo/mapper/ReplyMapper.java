package com.zhibo.org.zhibo.mapper;

import com.zhibo.org.zhibo.entity.Reply;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
public interface ReplyMapper {
    public List<Reply> getRepliesByArticleId(String article_id,Integer start,Integer limit);

    public Reply getReplyById(String id);
    public Boolean deleteReplyById(String id);
    public Boolean updateReplyById(String id, Reply reply);
    public Boolean createReply(Reply reply);
}
