package com.phone1000.chayu.weidgt;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.phone1000.chayu.R;

/**
 * Created by Administrator on 2016/11/29 0029.
 */
public class ShareTopBar extends LinearLayout{
    private ImageView mBack;
    private ImageView mTopShare;
    private ImageView mQQ;
    private ImageView mWeixin;
    private ImageView mPengyouquan;

    public ShareTopBar(Context context) {
        this(context,null);
    }

    public ShareTopBar(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ShareTopBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        View inflate = LayoutInflater.from(context).inflate(R.layout.sharetopbar, this, true);

        mBack = ((ImageView) findViewById(R.id.back));
        mTopShare = ((ImageView) findViewById(R.id.top_share));
        mQQ = ((ImageView) findViewById(R.id.share_qq));
        mWeixin = ((ImageView) findViewById(R.id.share_weixin));
        mPengyouquan = ((ImageView) findViewById(R.id.share_pengyouquan));
    }

    public void shareTopshare(OnClickListener listener){

        mBack.setOnClickListener(listener);
        mTopShare.setOnClickListener(listener);
        mQQ.setOnClickListener(listener);
        mWeixin.setOnClickListener(listener);
        mPengyouquan.setOnClickListener(listener);

    }

}
