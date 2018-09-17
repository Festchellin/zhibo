package com.zhibo.org.zhibo.entity;

/**
 * @author dream
 * @date 2018/09/17
 *
 * 投票实体类（用于 赞/踩 功能）
 */
public class Vote {
    private Integer id;
    private String voteTime;    //赞或踩的时间
    private Integer states;     //[0：踩，1：赞]
    private User voter;         //投票者
    private Voteable voteObject;    //投票对象：Article or Reply
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
