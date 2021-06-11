package com.czhhhb.entity;

import javax.xml.crypto.Data;
import java.util.Date;

public class User {
    private String USER_ID;
    private String USER_NICKNAME;
    private int USER_SEX;
    private String USER_PHONENUM;
    private Data USER_REGISTER_TIME;
    private int USER_CLOCK_NUM;
    private String USER_headimage;
//USER_ID,USER_NICKNAME,USER_SEX,USER_PHONENUM,USER_REGISTER_TIME,USER_CLOCK_NUM,USER_headimage

    @Override
    public String toString() {
        return "User{" +
                "USER_ID='" + USER_ID + '\'' +
                ", USER_NICKNAME='" + USER_NICKNAME + '\'' +
                ", USER_SEX=" + USER_SEX +
                ", USER_PHONENUM='" + USER_PHONENUM + '\'' +
                ", USER_REGISTER_TIME=" + USER_REGISTER_TIME +
                ", USER_CLOCK_NUM=" + USER_CLOCK_NUM +
                ", USER_headimage='" + USER_headimage + '\'' +
                '}';
    }

    public User() {
    }


    public User(String USER_ID, String USER_NICKNAME, int USER_SEX, String USER_PHONENUM, Data USER_REGISTER_TIME, int USER_CLOCK_NUM, String USER_headimage) {
        this.USER_ID = USER_ID;
        this.USER_NICKNAME = USER_NICKNAME;
        this.USER_SEX = USER_SEX;
        this.USER_PHONENUM = USER_PHONENUM;
        this.USER_REGISTER_TIME = USER_REGISTER_TIME;
        this.USER_CLOCK_NUM = USER_CLOCK_NUM;
        this.USER_headimage = USER_headimage;
    }

    public String getUSER_ID() {
        return USER_ID;
    }

    public void setUSER_ID(String USER_ID) {
        this.USER_ID = USER_ID;
    }

    public String getUSER_NICKNAME() {
        return USER_NICKNAME;
    }

    public void setUSER_NICKNAME(String USER_NICKNAME) {
        this.USER_NICKNAME = USER_NICKNAME;
    }

    public int getUSER_SEX() {
        return USER_SEX;
    }

    public void setUSER_SEX(int USER_SEX) {
        this.USER_SEX = USER_SEX;
    }

    public String getUSER_PHONENUM() {
        return USER_PHONENUM;
    }

    public void setUSER_PHONENUM(String USER_PHONENUM) {
        this.USER_PHONENUM = USER_PHONENUM;
    }

    public Data getUSER_REGISTER_TIME() {
        return USER_REGISTER_TIME;
    }

    public void setUSER_REGISTER_TIME(Data USER_REGISTER_TIME) {
        this.USER_REGISTER_TIME = USER_REGISTER_TIME;
    }

    public int getUSER_CLOCK_NUM() {
        return USER_CLOCK_NUM;
    }

    public void setUSER_CLOCK_NUM(int USER_CLOCK_NUM) {
        this.USER_CLOCK_NUM = USER_CLOCK_NUM;
    }

    public String getUSER_headimage() {
        return USER_headimage;
    }

    public void setUSER_headimage(String USER_headimage) {
        this.USER_headimage = USER_headimage;
    }
}
