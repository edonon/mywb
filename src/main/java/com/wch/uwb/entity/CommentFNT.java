package com.wch.uwb.entity;

import java.sql.Date;

public class CommentFNT {
    private int id;
    private int wId;
    private int uId;
    private String userName;
    private String userNamePhoto;
    private String discuss;
    private Date time;

    public CommentFNT(CommentEntity commentEntity, UserEntity userEntity) {
        this.id = commentEntity.getId();
        this.wId = commentEntity.getwId();
        this.uId = commentEntity.getuId();
        this.userName = userEntity.getUserName();
        this.userNamePhoto = userEntity.getPhoto();
        this.discuss = commentEntity.getDiscuss();
        this.time = commentEntity.getTime();
    }
    public CommentFNT(int wId, int uId, String userName, String userNamePhoto, String discuss, Date time) {
        this.wId = wId;
        this.uId = uId;
        this.userName = userName;
        this.userNamePhoto = userNamePhoto;
        this.discuss = discuss;
        this.time = time;
    }

    @Override
    public String toString() {
        return "CommentFNT{" +
                "id=" + id +
                ", wId=" + wId +
                ", uId=" + uId +
                ", userName='" + userName + '\'' +
                ", userNamePhoto='" + userNamePhoto + '\'' +
                ", discuss='" + discuss + '\'' +
                ", time=" + time +
                '}';
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getwId() {
        return wId;
    }

    public void setwId(int wId) {
        this.wId = wId;
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

    public String getDiscuss() {
        return discuss;
    }

    public void setDiscuss(String discuss) {
        this.discuss = discuss;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
