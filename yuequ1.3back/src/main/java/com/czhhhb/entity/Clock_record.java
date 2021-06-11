package com.czhhhb.entity;

import javax.xml.crypto.Data;

public class Clock_record {
    private String USER_ID;
    private int clock_id;
    private int CLOCK_TIME;
    private String CLOCK_BEGIN_TIME;
    private String CLOCK_OVER_TIME;
    private int CLOCK_STATE;//1为正在打卡 0为打卡结束

    public Clock_record() {
    }

    @Override
    public String toString() {
        return "Clock_record{" +
                "USER_ID='" + USER_ID + '\'' +
                ", clock_id=" + clock_id +
                ", CLOCK_TIME=" + CLOCK_TIME +
                ", CLOCK_BEGIN_TIME='" + CLOCK_BEGIN_TIME + '\'' +
                ", CLOCK_OVER_TIME='" + CLOCK_OVER_TIME + '\'' +
                ", CLOCK_STATE=" + CLOCK_STATE +
                '}';
    }

    public Clock_record(String USER_ID, int clock_id, int CLOCK_TIME, String CLOCK_BEGIN_TIME, String CLOCK_OVER_TIME, int CLOCK_STATE) {
        this.USER_ID = USER_ID;
        this.clock_id = clock_id;
        this.CLOCK_TIME = CLOCK_TIME;
        this.CLOCK_BEGIN_TIME = CLOCK_BEGIN_TIME;
        this.CLOCK_OVER_TIME = CLOCK_OVER_TIME;
        this.CLOCK_STATE = CLOCK_STATE;
    }

    public String getUSER_ID() {
        return USER_ID;
    }

    public void setUSER_ID(String USER_ID) {
        this.USER_ID = USER_ID;
    }

    public int getClock_id() {
        return clock_id;
    }

    public void setClock_id(int clock_id) {
        this.clock_id = clock_id;
    }

    public int getCLOCK_TIME() {
        return CLOCK_TIME;
    }

    public void setCLOCK_TIME(int CLOCK_TIME) {
        this.CLOCK_TIME = CLOCK_TIME;
    }

    public String getCLOCK_BEGIN_TIME() {
        return CLOCK_BEGIN_TIME;
    }

    public void setCLOCK_BEGIN_TIME(String CLOCK_BEGIN_TIME) {
        this.CLOCK_BEGIN_TIME = CLOCK_BEGIN_TIME;
    }

    public String getCLOCK_OVER_TIME() {
        return CLOCK_OVER_TIME;
    }

    public void setCLOCK_OVER_TIME(String CLOCK_OVER_TIME) {
        this.CLOCK_OVER_TIME = CLOCK_OVER_TIME;
    }

    public int getCLOCK_STATE() {
        return CLOCK_STATE;
    }

    public void setCLOCK_STATE(int CLOCK_STATE) {
        this.CLOCK_STATE = CLOCK_STATE;
    }
}
