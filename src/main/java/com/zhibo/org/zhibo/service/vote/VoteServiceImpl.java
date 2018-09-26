package com.zhibo.org.zhibo.service.vote;

import com.zhibo.org.zhibo.entity.Article;
import com.zhibo.org.zhibo.entity.Vote;
import com.zhibo.org.zhibo.mapper.VoteMapper;
import com.zhibo.org.zhibo.mapper.article.ArticleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 赞和踩的业务逻辑实现类
 * @author LS
 */
@Service
public class VoteServiceImpl implements VoteService {
    @Autowired
    public VoteMapper voteMapper;
    @Autowired
    public ArticleMapper articleMapper;

    @Override
    public void likeAndDislikeArticle(Vote vote) {
        Vote voteByUserId = voteMapper.findVoteByUserId(vote);
        if (null == voteByUserId){
            voteMapper.firstAppraisalArticle(vote);
            Article article = articleMapper.findLikesAndDislikesByArticleId(vote.getArticleId());
            if (-1 == vote.getStates()){
                article.setDislikes(article.getDislikes()+1);
            }else if (1 == vote.getStates()){
                article.setLikes(article.getLikes()+1);
            }
            articleMapper.setLikesAndDislikes(article);
        }else {
            if (0 == vote.getStates()){
                Article article = articleMapper.findLikesAndDislikesByArticleId(vote.getArticleId());
                if (-1 == voteByUserId.getStates()){
                    article.setDislikes(article.getDislikes()-1);
                }else if (1 == voteByUserId.getStates()){
                    article.setLikes(article.getLikes()-1);
                }
                articleMapper.setLikesAndDislikes(article);
            }else {
                voteMapper.likeAndDislikeArticle(vote);
            }
        }

    }
}
