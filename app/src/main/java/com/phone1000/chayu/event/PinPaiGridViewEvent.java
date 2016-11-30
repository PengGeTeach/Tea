package com.phone1000.chayu.event;

/**
 * Created by Administrator on 2016/11/30 0030.
 */
public class PinPaiGridViewEvent {

    private String name;
    private final int what;
    private String id;
    private String order;
    private String review_year;

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

    public PinPaiGridViewEvent(int what) {
        this.what = what;
    }

    public int getWhat() {
        return what;
    }

    public String getId() {

        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
