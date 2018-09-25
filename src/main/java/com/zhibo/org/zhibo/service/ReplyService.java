package com.zhibo.org.zhibo.service;

import com.zhibo.org.zhibo.entity.Article;
import com.zhibo.org.zhibo.entity.Reply;
import com.zhibo.org.zhibo.mapper.ReplyMapper;
import com.zhibo.org.zhibo.mapper.UserMapper;
import com.zhibo.org.zhibo.mapper.article.ArticleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReplyService {
    @Autowired
    private ReplyMapper replyMapper;
    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private UserMapper userMapper;

    public List<Reply> getRepliesByArticleId(String articleId, @Nullable Integer start, @Nullable Integer limit){
        start = start == null || start <= 0 ? 0 : start;
        limit = limit == null || limit <= 0 ? 5 : limit;
        System.out.println(start+":"+limit);
        List<Reply> replies = replyMapper.getRepliesByArticleId(articleId, start, limit);
        return replies;
    }

    public Reply getReplyById(String id){
        Reply reply = replyMapper.getReplyById(id);
        return reply;
    }

    public Boolean createReply(Reply reply) {
        System.out.println(reply);
        return replyMapper.createReply(reply);
    }

    public Boolean deleteReplyById(String id) {
        return replyMapper.deleteReplyById(id);
    }

    public Boolean updateReplyById(String id, Reply reply) {
        System.out.println(reply);
        return replyMapper.updateReplyById(id,reply);
    }
}
