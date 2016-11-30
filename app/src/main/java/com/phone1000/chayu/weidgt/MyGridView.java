package com.phone1000.chayu.weidgt;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

import com.phone1000.chayu.adapters.PinPaiFragmentAdapter;


/**
 * Created by Administrator on 2016/11/28 0028.
 */
public class MyGridView extends GridView{


    public MyGridView(Context context) {
        this(context,null);
    }

    public MyGridView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int measureHeight = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec,measureHeight);
    }


}
