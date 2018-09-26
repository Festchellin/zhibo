package com.zhibo.org.zhibo.service.vote;

import com.zhibo.org.zhibo.entity.Article;
import com.zhibo.org.zhibo.entity.Vote;
import com.zhibo.org.zhibo.mapper.VoteMapper;
import com.zhibo.org.zhibo.mapper.article.ArticleMapper;
import com.zhibo.org.zhibo.util.StringGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

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
        //获取当前系统时间
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        vote.setVoteTime(format.format(new Date()));

        //通过用户ID查找该用户是否对该文章进行评价
        Vote voteByUserId = voteMapper.findVoteByUserId(vote);
        if (null == voteByUserId){
            //没找到，则该用户没有进行评价，在评价表中新添加一个记录
            //生成UUID，作为该条记录的唯一标识ID
            vote.setId(StringGenerator.UUIDGenerator());
            voteMapper.firstAppraisalArticle(vote);
            //通过文章的唯一标识ID查询该文章的点赞和差评总数
            Article article = articleMapper.findLikesAndDislikesByArticleId(vote.getArticleId());
            //如果要进行点赞，则将该文章的点赞总数加一；如果要进行差评，则将该文章的差评总数加一
            if (-1 == vote.getStates()){
                article.setDislikes(article.getDislikes()+1);
            }else if (1 == vote.getStates()){
                article.setLikes(article.getLikes()+1);
            }
            //将新的点赞或差评总数进行更新
            articleMapper.setLikesAndDislikes(article);
        }else {
            //找到vote记录，说明该用户已经进行过评价
            //将查询到的评价唯一标识ID设置给要修改的评价
            vote.setId(voteByUserId.getId());

            if (0 == vote.getStates() && 0 != voteByUserId.getStates()){
                //用户进行取消操作
                //查询该文章的点赞和差评总数
                Article article = articleMapper.findLikesAndDislikesByArticleId(vote.getArticleId());
                //如果当前状态为差评，则差评总数减一；如果当前状态为点赞，则点赞总数减一；
                if (-1 == voteByUserId.getStates()){
                    article.setDislikes(article.getDislikes()-1);

                }else if (1 == voteByUserId.getStates()){
                    article.setLikes(article.getLikes()-1);
                }
                //将新的点赞或差评总数进行更新
                articleMapper.setLikesAndDislikes(article);
                //将评价状态改为无评价状态
                voteByUserId.setStates(0);
                voteMapper.cancleVote(voteByUserId);
            }else {
                //用户直接从点赞状态改为差评状态，或者直接从差评状态改为点赞状态
                if (0 != voteByUserId.getStates()){
                    //如果想要改变的状态和已评价的状态一致，则不进行修改
                    if (voteByUserId.getStates().equals(vote.getStates())){
                        return;
                    }
                    //如果当前状态为已评价状态，则一方加一，另一方减一
                    voteMapper.likeAndDislikeArticle(vote);
                }else {
                    //如果当前状态为未评价状态，则只需要一方加一
                    voteMapper.changToLikesOrDisLikes(vote);
                }
            }
        }

    }

    @Override
    public Vote findVoteByUserId(Vote vote) {
        return voteMapper.findVoteByUserId(vote);
    }
}
