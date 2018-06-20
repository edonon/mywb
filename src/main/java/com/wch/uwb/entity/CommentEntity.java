package com.wch.uwb.entity;

import java.sql.Date;

public class CommentEntity {
    private int id;
    private int wId;
    private int uId;
    private String discuss;
    private Date time;
    public CommentEntity(){};
    public CommentEntity(int wId, int uId, String discuss, Date time) {
        this.wId = wId;
        this.uId = uId;
        this.discuss = discuss;
        this.time = time;
    }

    @Override
    public String toString() {
        return "CommentEntity{" +
                "id=" + id +
                ", wId=" + wId +
                ", uId=" + uId +
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
