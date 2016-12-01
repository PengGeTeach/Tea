package com.phone1000.chayu.modles;

/**
 * Created by Administrator on 2016/11/29 0029.
 */
public class TeaListEvent {

    private final int WHAT;
    private String bid=null;
    private String sid=null;
    private String order=null;
    private String review_year=null;
    private String  teaName;

    public String getTeaName() {
        return teaName;
    }

    public void setTeaName(String teaName) {
        this.teaName = teaName;
    }

    public String getReview_year() {
        return review_year;
    }

    public void setReview_year(String review_year) {
        this.review_year = review_year;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }
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
