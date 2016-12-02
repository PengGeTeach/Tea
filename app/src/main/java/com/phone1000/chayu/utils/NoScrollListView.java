package com.phone1000.chayu.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by Administrator on 2016/12/2 0002.
 */
public class NoScrollListView extends ListView {

    public NoScrollListView(Context context) {
        this(context,null);
    }

    public NoScrollListView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public NoScrollListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandHeight = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandHeight);
    }
}
