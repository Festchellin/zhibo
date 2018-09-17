package com.zhibo.org.zhibo.entity;

import com.zhibo.org.zhibo.util.StringGenerator;

import java.util.List;

/**
 * @author dream
 * @date 2018/09/17
 *
 * 文章实体类
 */
public class Article implements Voteable {
    private String id= StringGenerator.UUIDGenerator();//id
    private String title;//标题
    private String thumbnail;//文章略缩图  用于首页显示
    private String uploadTime;//上传时间
    private String lastEditTime;//最后一次修改时间
    private String content;//文章内容
    private String category;//文章分类
    private Integer likes;//赞   统计voteList中state为1的个数
    private Integer dislikes;//踩 统计voteList中state为0的个数
    private User author;//作责
    private Integer state;//文章状态    [0：已删除，1：正常状态，2：已被禁止]
    private List<Vote> voteList;

    //getter & setter

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(String uploadTime) {
        this.uploadTime = uploadTime;
    }

    public String getLastEditTime() {
        return lastEditTime;
    }

    public void setLastEditTime(String lastEditTime) {
        this.lastEditTime = lastEditTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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

    public void setDislikes(Integer dislikes) {
        this.dislikes = dislikes;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public List<Vote> getVoteList() {
        return voteList;
    }

    public void setVoteList(List<Vote> voteList) {
        this.voteList = voteList;
    }

    //toString

    @Override
    public String toString() {
        return "Article{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", thumbnail='" + thumbnail + '\'' +
                ", uploadTime='" + uploadTime + '\'' +
                ", lastEditTime='" + lastEditTime + '\'' +
                ", content='" + content + '\'' +
                ", category='" + category + '\'' +
                ", likes=" + likes +
                ", dislikes=" + dislikes +
                ", author=" + author +
                ", state=" + state +
                ", voteList=" + voteList +
                '}';
    }
}
