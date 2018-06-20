package com.wch.uwb.entity;

import java.sql.Date;

public class WeiboEntity {
    private int id;
    private int uId;
    private String content;
    private Date releaseTime;
    private Date lastEditTime;
    private int good;
    private String photo;

    public WeiboEntity() {
        super();
    }

    public WeiboEntity(int uId, String content, Date releaseTime, Date lastEditTime, int good, String photo) {
        this.uId = uId;
        this.content = content;
        this.releaseTime = releaseTime;
        this.lastEditTime = lastEditTime;
        this.good = good;
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "WeiboEntity{" +
                "id=" + id +
                ", uId=" + uId +
                ", content='" + content + '\'' +
                ", releaseTime=" + releaseTime +
                ", lastEditTime=" + lastEditTime +
                ", good=" + good +
                ", photo='" + photo + '\'' +
                '}';
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
}
