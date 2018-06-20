package com.wch.uwb.entity;

import java.sql.Date;

public class FanEntity {
    private int id;
    private int uId;
    private int fanId;
    private Date time;

    public  FanEntity(){}
    public FanEntity(int uId, int fanId, Date time) {
        this.uId = uId;
        this.fanId = fanId;
        this.time = time;
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

    public int getFanId() {
        return fanId;
    }

    public void setFanId(int fanId) {
        this.fanId = fanId;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "FanEntity{" +
                "id=" + id +
                ", uId=" + uId +
                ", fanId=" + fanId +
                ", time=" + time +
                '}';
    }
}
