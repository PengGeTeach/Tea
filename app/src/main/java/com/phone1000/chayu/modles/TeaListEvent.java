package com.phone1000.chayu.modles;

/**
 * Created by Administrator on 2016/11/29 0029.
 */
public class TeaListEvent {

    private final int WHAT;
    private String bid;
    private String sid;

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public int getWHAT() {
        return WHAT;
    }



    public TeaListEvent(int WHAT) {
        this.WHAT=WHAT;
    }
}
