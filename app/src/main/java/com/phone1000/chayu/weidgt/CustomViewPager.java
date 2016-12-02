package com.phone1000.chayu.weidgt;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import java.lang.reflect.Field;


/**
 * Created by Administrator on 2016/11/28 0028.
 */
public class CustomViewPager extends NoPreloadViewPager {
    private static final String TAG = CustomViewPager.class.getSimpleName();
    private int current;
    private int height = 0;

    private boolean scrollble = true;

    public CustomViewPager(Context context) {
        this(context,null);
    }

    public CustomViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        Class<? extends CustomViewPager> cls = getClass();
        try {
            Field field = cls.getField("DEFAULT_OFFSCREEN_PAGES");
            field.setAccessible(true);
            field.set(this,0);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            Log.e(TAG, "CustomViewPager: NoSuchFieldException " );
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            Log.e(TAG, "CustomViewPager: IllegalAccessException" );
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (getChildCount() > current) {
            View child = getChildAt(current);
            child.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
            int h = child.getMeasuredHeight();
            height = h;
        }

        heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    public void resetHeight(int current) {
        this.current = current;
        if (getChildCount() > current) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) getLayoutParams();
            if (layoutParams == null) {
                layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, height);
            } else {
                layoutParams.height = height;
            }
            setLayoutParams(layoutParams);
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (!scrollble) {
            return true;
        }
        return super.onTouchEvent(ev);
    }


    public boolean isScrollble() {
        return scrollble;
    }

    public void setScrollble(boolean scrollble) {
        this.scrollble = scrollble;
    }



}


