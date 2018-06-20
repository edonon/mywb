package com.wch.uwb.entity;

public class InformEntity {
    private int id;
    private int uid;
    private int commentCnt;
    private int chatCnt;

    public InformEntity(){}

    public InformEntity(int uid, int commentCnt, int chatCnt) {
        this.uid = uid;
        this.commentCnt = commentCnt;
        this.chatCnt = chatCnt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getCommentCnt() {
        return commentCnt;
    }

    public void setCommentCnt(int commentCnt) {
        this.commentCnt = commentCnt;
    }

    public int getChatCnt() {
        return chatCnt;
    }

    public void setChatCnt(int chatCnt) {
        this.chatCnt = chatCnt;
    }

    @Override
    public String toString() {
        return "InformEntity{" +
                "id=" + id +
                ", uid=" + uid +
                ", commentCnt=" + commentCnt +
                ", chatCnt=" + chatCnt +
                '}';
    }
}
