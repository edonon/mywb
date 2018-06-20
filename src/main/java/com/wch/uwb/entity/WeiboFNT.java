package com.wch.uwb.entity;

import java.sql.Date;

public class WeiboFNT {
    private int id;
    private int uId;
    private String userName;
    private String userNamePhoto;
    private String content;
    private Date releaseTime;
    private Date lastEditTime;
    private int good;
    private String photo;

    public WeiboFNT(UserEntity userEntity, WeiboEntity weiboEntity){
        this.id = weiboEntity.getId();
        this.uId = weiboEntity.getuId();
        this.userName = userEntity.getUserName();
        this.userNamePhoto = userEntity.getPhoto();
        this.content = weiboEntity.getContent();
        this.releaseTime = weiboEntity.getReleaseTime();
        this.lastEditTime = weiboEntity.getLastEditTime();
        this.good = weiboEntity.getGood();
        this.photo = weiboEntity.getPhoto();
    }
    public WeiboFNT(int id, int uId, String userName, String userNamePhoto, String content, Date releaseTime, Date lastEditTime, int good, String photo) {
        this.id = id;
        this.uId = uId;
        this.userName = userName;
        this.userNamePhoto = userNamePhoto;
        this.content = content;
        this.releaseTime = releaseTime;
        this.lastEditTime = lastEditTime;
        this.good = good;
        this.photo = photo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getuId() {
        return uId;
    }

    public void setuId(int uId) {
        this.uId = uId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserNamePhoto() {
        return userNamePhoto;
    }

    public void setUserNamePhoto(String userNamePhoto) {
        this.userNamePhoto = userNamePhoto;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(Date releaseTime) {
        this.releaseTime = releaseTime;
    }

    public Date getLastEditTime() {
        return lastEditTime;
    }

    public void setLastEditTime(Date lastEditTime) {
        this.lastEditTime = lastEditTime;
    }

    public int getGood() {
        return good;
    }

    public void setGood(int good) {
        this.good = good;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "WeiboFNT{" +
                "id=" + id +
                ", uId=" + uId +
                ", userName='" + userName + '\'' +
                ", userNamePhoto='" + userNamePhoto + '\'' +
                ", content='" + content + '\'' +
                ", releaseTime=" + releaseTime +
                ", lastEditTime=" + lastEditTime +
                ", good=" + good +
                ", photo='" + photo + '\'' +
                '}';
    }
}
