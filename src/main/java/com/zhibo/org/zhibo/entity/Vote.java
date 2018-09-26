package com.zhibo.org.zhibo.entity;

import com.zhibo.org.zhibo.util.StringGenerator;

/**
 * @author dream
 * @date 2018/09/17
 *
 * 投票实体类（用于 赞/踩 功能）
 */
public class Vote {
    private String id;
    private String articleId;   //点赞或差评的文章ID
    private String voteTime;    //赞或踩的时间
    private Integer states;     //[-1：差评，1：赞]
    private User voter;         //投票者
    private Voteable voteObject;    //投票对象：Article or Reply

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVoteTime() {
        return voteTime;
    }

    public void setVoteTime(String voteTime) {
        this.voteTime = voteTime;
    }

    public Integer getStates() {
        return states;
    }

    public void setStates(Integer states) {
        this.states = states;
    }

    public User getVoter() {
        return voter;
    }

    public void setVoter(User voter) {
        this.voter = voter;
    }

    @Override
    public String toString() {
        return "Vote{" +
                "id=" + id +
                ", voteTime='" + voteTime + '\'' +
                ", states=" + states +
                ", voter=" + voter +
                '}';
    }
}
