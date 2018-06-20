package com.wch.uwb.entity;

import java.sql.Date;

public class Chat {
    private  int id;
    private  int wIdFri;
    private  int wIdSec;
    private  int direction;
    private  String discuss;
    private Date time;
    public  Chat(){}

    public Chat(int wIdFri, int wIdSec, int direction, String discuss, Date time) {
        this.wIdFri = wIdFri;
        this.wIdSec = wIdSec;
        this.direction = direction;
        this.discuss = discuss;
        this.time = time;
    }

    @Override
    public String toString() {
        return "Chat{" +
                "id=" + id +
                ", wIdFri=" + wIdFri +
                ", wIdSec=" + wIdSec +
                ", direction=" + direction +
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

    public int getwIdFri() {
        return wIdFri;
    }

    public void setwIdFri(int wIdFri) {
        this.wIdFri = wIdFri;
    }

    public int getwIdSec() {
        return wIdSec;
    }

    public void setwIdSec(int wIdSec) {
        this.wIdSec = wIdSec;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
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
