package com.phone1000.chayu.event;

/**
 * Created by Administrator on 2016/11/30 0030.
 */
public class CheckBoxClickTagEvent {
    private final int WHAT;

    private int clickTag;

    public int getClickTag() {
        return clickTag;
    }

    public void setClickTag(int clickTag) {
        this.clickTag = clickTag;
    }

    public int getWHAT() {
        return WHAT;
    }



    public CheckBoxClickTagEvent(int WHAT) {
        this.WHAT=WHAT;
    }
}
