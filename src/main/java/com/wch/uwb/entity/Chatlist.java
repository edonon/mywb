package com.wch.uwb.entity;

public class Chatlist {
    private int id;
    private int uid;
    private int seuid;

    public Chatlist(){}

    public Chatlist(int uid, int seuid) {
        this.uid = uid;
        this.seuid = seuid;
    }

    @Override
    public String toString() {
        return "Chatlist{" +
                "id=" + id +
                ", uid=" + uid +
                ", seuid=" + seuid +
                '}';
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

    public int getSeuid() {
        return seuid;
    }

    public void setSeuid(int seuid) {
        this.seuid = seuid;
    }
}
