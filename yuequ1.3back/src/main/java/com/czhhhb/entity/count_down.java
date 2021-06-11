package com.czhhhb.entity;

import javax.xml.crypto.Data;

public class count_down {
    private String USER_ID;
    private String EVENT_ID;
    private String EVENT_NAME;
    private String END_TIME;

    public count_down() {
    }

    public String getUSER_ID() {
        return USER_ID;
    }

    public void setUSER_ID(String USER_ID) {
        this.USER_ID = USER_ID;
    }

    public String getEVENT_ID() {
        return EVENT_ID;
    }

    public void setEVENT_ID(String EVENT_ID) {
        this.EVENT_ID = EVENT_ID;
    }

    public String getEVENT_NAME() {
        return EVENT_NAME;
    }

    public void setEVENT_NAME(String EVENT_NAME) {
        this.EVENT_NAME = EVENT_NAME;
    }

    public String getEND_TIME() {
        return END_TIME;
    }

    public void setEND_TIME(String END_TIME) {
        this.END_TIME = END_TIME;
    }

    @Override
    public String toString() {
        return "count_down{" +
                "USER_ID='" + USER_ID + '\'' +
                ", EVENT_ID='" + EVENT_ID + '\'' +
                ", EVENT_NAME='" + EVENT_NAME + '\'' +
                ", END_TIME='" + END_TIME + '\'' +
                '}';
    }
}

