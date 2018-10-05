package com.zhibo.org.zhibo.entity;

import com.zhibo.org.zhibo.util.StringGenerator;

import java.io.Serializable;
import java.util.List;

/**
 * @author dream
 * @date 2018/09/17
 * 回复实体类
 */
public class Reply implements Voteable, Serializable {
    private String id = StringGenerator.UUIDGenerator();
    private String content;//回复内容
    private Integer floor;//楼层 需先获取当前文章评论的最后总数 再加一
    private Integer likes;//赞   统计 voteList中 state为0的个数
    private Integer dislikes;//踩 统计 voteList中 state为0的个数
    private Article article;
    private List<Vote> voteList;
    private User fromUser;  //谁回复
    private User toUser;    //谁被回复

    //getter & setter


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public Integer getDislikes() {
        return dislikes;
    }

    public void setDislikes(Integer dislikse) {
        this.dislikes = dislikse;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public List<Vote> getVoteList() {
        return voteList;
    }

    public void setVoteList(List<Vote> voteList) {
        this.voteList = voteList;
    }

    public User getFromUser() {
        return fromUser;
    }

    public void setFromUser(User fromUser) {
        this.fromUser = fromUser;
    }

    public User getToUser() {
        return toUser;
    }

    public void setToUser(User toUser) {
        this.toUser = toUser;
    }

    @Override
    public String toString() {
        return "Reply{" +
                "id='" + id + '\'' +
                ", content='" + content + '\'' +
                ", floor=" + floor +
                ", likes=" + likes +
                ", dislikse=" + dislikes +
                ", article=" + article +
                ", voteList=" + voteList +
                ", fromUser=" + fromUser +
                ", toUser=" + toUser +
                '}';
    }
}
