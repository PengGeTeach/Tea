package com.phone1000.chayu;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.phone1000.chayu.event.Chapinevent;
import com.phone1000.chayu.modles.TeaComm;
import com.phone1000.chayu.path.UtilPath;

import org.greenrobot.eventbus.EventBus;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by Administrator on 2016/12/2 0002.
 */
public class WenzhangZuiXinReMen extends AppCompatActivity implements PullToRefreshBase.OnRefreshListener2{

    private String order;
    private PullToRefreshListView pullToRefreshListView;
    private PullToRefreshListView mRefreshListView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wenzhangzuixinremen);
        Intent intent = getIntent();
        order = intent.getStringExtra("order");

        initView();

        setupView();

    }

    private void setupView() {

        final RequestParams params = new RequestParams(UtilPath.WENZHANG_REMEN);
        params.addParameter("order","created");
        params.addParameter("pageNo","1");
        params.addParameter("source","3");
        params.addParameter("version","5");
        params.addParameter("imei","4731955d1f681a93");
        params.addParameter("versionCode","2.2.4");
        params.addParameter("pageSize","10");
        params.addParameter("agent","5");
        params.addParameter("type","all");

        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {


            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });


    }

    private void initView() {

        mRefreshListView = ((PullToRefreshListView) findViewById(R.id.pull_listview));
        mRefreshListView.setMode(PullToRefreshBase.Mode.PULL_FROM_END);
        mRefreshListView.setOnRefreshListener(this);

    }


    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {

    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {

    }
}
