package com.czhhhb.entity;

public class Tomato_clock {
    private String USER_ID;
    private String start_time;
    private String time;

    @Override
    public String toString() {
        return "Tomato_clock{" +
                "USER_ID='" + USER_ID + '\'' +
                ", start_time='" + start_time + '\'' +
                ", time=" + time +
                '}';
    }

    public Tomato_clock() {
    }

    public Tomato_clock(String USER_ID, String start_time, String time) {
        this.USER_ID = USER_ID;
        this.start_time = start_time;
        this.time = time;
    }

    public String getUSER_ID() {
        return USER_ID;
    }

    public void setUSER_ID(String USER_ID) {
        this.USER_ID = USER_ID;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
