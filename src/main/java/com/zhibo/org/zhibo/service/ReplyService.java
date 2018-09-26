package com.zhibo.org.zhibo.service;

import com.zhibo.org.zhibo.entity.Reply;
import com.zhibo.org.zhibo.mapper.ReplyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author dream
 * @date 2018/09/25
 */
@Service
public class ReplyService {
    @Autowired
    private ReplyMapper replyMapper;

    /**
     * 根据文章id获取其回复
     * @param articleId 文章id
     * @param start 分页开始
     * @param limit 每页大小
     * @return  该文章回复按分页的list
     */
    public List<Reply> getRepliesByArticleId(String articleId, @Nullable Integer start, @Nullable Integer limit){
        start = start == null || start <= 0 ? 0 : start;
        limit = limit == null || limit <= 0 ? 5 : limit;
        System.out.println(start+":"+limit);
        List<Reply> replies = replyMapper.getRepliesByArticleId(articleId, start, limit);
        return replies;
    }

    /**
     * 根据id获取回复
     * @param id 回复id
     * @return  回复实体类
     */
    public Reply getReplyById(String id){
        Reply reply = replyMapper.getReplyById(id);
        return reply;
    }

    /**
     * 创建新的回复
     * @param reply 待创建的回复
     * @return  是否更新成功（true/false）
     */
    public Boolean createReply(Reply reply) {
        Integer maxFloor = replyMapper.getCurrentArticleMaxFloor(reply.getArticle().getId() + "".trim());
        reply.setFloor(maxFloor+1);
        System.out.println(reply);
        return replyMapper.createReply(reply);
    }

    /**
     * 根据id删除回复
     * @param id 回复的id
     * @return  是否删除成功（true/false）
     */
    public Boolean deleteReplyById(String id) {
        return replyMapper.deleteReplyById(id);
    }

    /**
     * 根据id更新回复信息
     * @param reply 要更新的内容
     * @return  是否更新成功（true/false）
     */
    public Boolean updateReplyById(Reply reply) {
        System.out.println(reply);
        return replyMapper.updateReplyById(reply);
    }
}
