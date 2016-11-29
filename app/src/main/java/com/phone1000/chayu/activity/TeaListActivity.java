package com.phone1000.chayu.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.phone1000.chayu.R;
import com.phone1000.chayu.modles.TagEvent;
import com.phone1000.chayu.weidgt.TopBar;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class TeaListActivity extends AppCompatActivity {

    private static final String TAG = TeaListActivity.class.getSimpleName();
    private TopBar mTopBar;
    private FrameLayout mListViewCon;
    private TextView mCount;
    private CheckBox mTea;
    private CheckBox mPingfen;
    private CheckBox mPingJian;
    private CheckBox mChaYang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tea_list);
        initView();
    }

    private void initView() {
        mTopBar = (TopBar) findViewById(R.id.tealist_activity_top_bar);
        mTopBar.logoVisible(false);


        mListViewCon = (FrameLayout) findViewById(R.id.tea_list_listview_container);
        mCount = (TextView) findViewById(R.id.tea_list_count);
        mTea = (CheckBox) findViewById(R.id.tea_list_tea);
        mPingfen = (CheckBox) findViewById(R.id.tea_list_pingfen);
        mPingJian = (CheckBox) findViewById(R.id.tea_list_pinjian);
        mChaYang = (CheckBox) findViewById(R.id.tea_list_chayang);
    }

    @Subscribe(sticky = true,threadMode = ThreadMode.MAIN)
    public void onEvent(TagEvent event){
//        Log.e(TAG, "onEvent: "+event.getTag() );
        if (event.getWHAT()==0x100) {
            int tag = event.getTag();
            Log.e(TAG, "onEvent: tag ---------------->"+tag );
        }

    }
    @Override
    protected void onResume() {
        super.onResume();
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }
}
