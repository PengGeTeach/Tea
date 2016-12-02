package com.phone1000.chayu.event;

/**
 * Created by Administrator on 2016/12/1 0001.
 */
public class BangDanEvent {
    private final int WHAT;
    private String year = null;
    private String bid = null;
    private String orider = null;

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public String getOrider() {
        return orider;
    }

    public void setOrider(String orider) {
        this.orider = orider;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public BangDanEvent(int what) {
        WHAT = what;
    }

    public int getWHAT() {
        return WHAT;
    }
}
