package com.zhibo.org.zhibo.mapper;

import com.zhibo.org.zhibo.entity.Reply;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author dream
 * @date 2018/09/25
 */
@Mapper
@Repository
public interface ReplyMapper {
    /**
     *根据文章id获取其回复
     * @param article_id 文章id
     * @param start 分页开始
     * @param limit 每页大小
     * @return  该文章回复按分页的list
     */
    public List<Reply> getRepliesByArticleId(String article_id,Integer start,Integer limit);

    /**
     * 根据id获取回复
     * @param id 回复id
     * @return  回复实体类
     */
    public Reply getReplyById(String id);

    /**
     * 根据id删除回复
     * @param id 回复的id
     * @return  是否删除成功（true/false）
     */
    public Boolean deleteReplyById(String id);

    /**
     * 根据id更新回复信息
     * @param reply 要更新的内容
     * @return  是否更新成功（true/false）
     */
    public Boolean updateReplyById( Reply reply);

    /**
     * 创建新的回复
     * @param reply 待创建的回复
     * @return  是否更新成功（true/false）
     */
    public Boolean createReply(Reply reply);

    /**
     * 获取当前文章最大回复楼层数
     * @param articleId 待查询的文章Id
     * @return
     */
    public Integer getCurrentArticleMaxFloor(String articleId);
}
