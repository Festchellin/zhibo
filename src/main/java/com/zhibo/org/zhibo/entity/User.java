package com.zhibo.org.zhibo.entity;

import java.util.List;

/**
 * @author dream
 * @date 2018/09/17
 */
public class User {
    private Integer id;
    private String name;//用户昵称
    private String account;//用户登录账号
    private String password;//用户密码
    private String qqNum;//qq登录后绑定用户账号 未实现
    private String wechatNum;//微信登录后绑定用户账号 未实现
    private String email;//用户邮箱 注册时验证 必填
    private String summary;//用户简介，个性签名
    private String avatar;//用户头像
    private String phone;// 用户注册手机号   注册时必填
    private Integer state;// 用户状态 [0 : 已删除,1 : 正常使用,2 : 已被封禁,3  : 未验证] 默认为1
    private Long bannedTime;//封禁时间 无默认值
    private List<SysRole> roleList;// 一个用户具有多个角色
    private String code;//邮件激活码


    public User() {
    }

    /**
     * @param id         自动生成
     * @param name       用户昵称
     * @param account    用户登录账号
     * @param password   用户密码
     * @param qqNum      qq登录后绑定用户账号   未实现
     * @param wechatNum  微信登录后绑定用户账号   未实现
     * @param email      用户邮箱 注册时验证 必填
     * @param summary    用户简介，个性签名
     * @param avatar     用户头像
     * @param phone      用户注册手机号   注册时必填
     * @param state      用户状态 [0 : 已删除,1 : 正常使用,2 : 已被封禁] 默认为1
     * @param bannedTime 封禁时间 无默认值
     */
    public User(Integer id, String name, String account, String password, String qqNum, String wechatNum, String email, String summary, String avatar, String phone, Integer state, Long bannedTime) {
        this.id = id;
        this.name = name;
        this.account = account;
        this.password = password;
        this.qqNum = qqNum;
        this.wechatNum = wechatNum;
        this.email = email;
        this.summary = summary;
        this.avatar = avatar;
        this.phone = phone;
        this.state = state;
        this.bannedTime = bannedTime;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<SysRole> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<SysRole> roleList) {
        this.roleList = roleList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getQqNum() {
        return qqNum;
    }

    public void setQqNum(String qqNum) {
        this.qqNum = qqNum;
    }

    public String getWechatNum() {
        return wechatNum;
    }

    public void setWechatNum(String wechatNum) {
        this.wechatNum = wechatNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Long getBannedTime() {
        return bannedTime;
    }

    public void setBannedTime(Long bannedTime) {
        this.bannedTime = bannedTime;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", qqNum='" + qqNum + '\'' +
                ", wechatNum='" + wechatNum + '\'' +
                ", email='" + email + '\'' +
                ", summary='" + summary + '\'' +
                ", avatar='" + avatar + '\'' +
                ", phone='" + phone + '\'' +
                ", state=" + state +
                ", bannedTime=" + bannedTime +
                '}';
    }
}
