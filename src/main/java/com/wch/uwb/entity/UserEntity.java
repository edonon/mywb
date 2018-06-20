package com.wch.uwb.entity;

import java.sql.Date;

public class UserEntity {
    private int id;
    private String login;
    private String passwd;
    private String userName;
    private int author;
    private String sex;
    private String resume;
    private String question;
    private String answer;
    private String photo;
    private Date registerTime;
    private Date free;
  /*  private String photo;
    private Date lastLoginTime;
    private String address;
    private Date brith;
    private String bolg;
    private String emotion;
    private String sexOrientation;
    private String qq;
    private String msn;
    private String mail;
    private String profession;*/

    public UserEntity() {
        super();
    }

    public UserEntity(String login, String passwd, String userName, int author, String sex, String resume, String question, String answer, String photo, Date registerTime, Date free) {
        this.login = login;
        this.passwd = passwd;
        this.userName = userName;
        this.author = author;
        this.sex = sex;
        this.resume = resume;
        this.question = question;
        this.answer = answer;
        this.photo = photo;
        this.registerTime = registerTime;
        this.free = free;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", passwd='" + passwd + '\'' +
                ", userName='" + userName + '\'' +
                ", author=" + author +
                ", sex='" + sex + '\'' +
                ", resume='" + resume + '\'' +
                ", question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                ", photo='" + photo + '\'' +
                ", registerTime=" + registerTime +
                ", free=" + free +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAuthor() {
        return author;
    }

    public void setAuthor(int author) {
        this.author = author;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public Date getFree() {
        return free;
    }

    public void setFree(Date free) {
        this.free = free;
    }
}