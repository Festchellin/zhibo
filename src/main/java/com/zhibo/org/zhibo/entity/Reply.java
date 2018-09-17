package com.zhibo.org.zhibo.entity;

import com.zhibo.org.zhibo.util.StringGenerator;

import java.util.List;

/**
 * @author dream
 * @date 2018/09/17
 * 回复实体类
 */
public class Reply implements Voteable {
    private String id = StringGenerator.UUIDGenerator();
    private String content;//回复内容
    private Integer index;//楼层 需先获取当前文章评论的最后总数 再加一
    private Integer likes;//赞   统计 voteList中 state为0的个数
    private Integer dislikse;//踩 统计 voteList中 state为0的个数
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

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public Integer getDislikse() {
        return dislikse;
    }

    public void setDislikse(Integer dislikse) {
        this.dislikse = dislikse;
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
                ", index=" + index +
                ", likes=" + likes +
                ", dislikse=" + dislikse +
                ", voteList=" + voteList +
                ", fromUser=" + fromUser +
                ", toUser=" + toUser +
                '}';
    }
}
