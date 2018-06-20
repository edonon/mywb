package com.wch.uwb.entity;

import java.sql.Date;

public class ReportEntity {
    private int id;
    private int wId;
    private Date time;

    public ReportEntity() { }
    public ReportEntity(int wId, Date time) {
        this.wId = wId;
        this.time = time;
    }

    @Override
    public String toString() {
        return "ReportEntity{" +
                "id=" + id +
                ", wId=" + wId +
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

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
