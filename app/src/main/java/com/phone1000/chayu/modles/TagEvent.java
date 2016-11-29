package com.phone1000.chayu.modles;

/**
 * Created by Administrator on 2016/11/29 0029.
 */
public class TagEvent {

    private final int WHAT;
    private int tag;

    public int getWHAT() {
        return WHAT;
    }

    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }

    public TagEvent(int WHAT) {
        this.WHAT=WHAT;
    }
}
