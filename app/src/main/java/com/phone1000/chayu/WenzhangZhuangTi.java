package com.phone1000.chayu;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.phone1000.chayu.adapters.WenzhangZhunagtiAdapter;
import com.phone1000.chayu.modles.WenZhangShangLaJiaZai;
import com.phone1000.chayu.modles.WenZhangZhuangTiModle;
import com.phone1000.chayu.path.UtilPath;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by Administrator on 2016/12/2 0002.
 */
public class WenzhangZhuangTi extends AppCompatActivity implements PullToRefreshBase.OnRefreshListener2{

    private static final String TAG = WenzhangZhuangTi.class.getSimpleName();
    private PullToRefreshListView pulltoreftrsh;
    private PullToRefreshListView mRefreshview;
    private WenzhangZhunagtiAdapter adapter;
    private ListView mListView;
    private int p = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wenzhangzhuantiactivity);

        initView();

        setupView();

    }

    private void setupView() {

        final RequestParams params = new RequestParams(UtilPath.WENZHANG_ZHUANTI);
        params.addParameter("pageNo",p);
        params.addParameter("source","3");
        params.addParameter("imei","4731955d1f681a93");
        params.addParameter("versionCode","2.2.4");
        params.addParameter("pageSize","10");
        params.addParameter("agent","5");

        x.http().post(params, new Callback.CommonCallback<String>() {

            @Override
            public void onSuccess(String result) {
                Log.e(TAG, "onSuccess: "+result );
                Gson gson = new Gson();
                WenZhangZhuangTiModle wenZhangZhuangTiModle = gson.fromJson(result, WenZhangZhuangTiModle.class);
                if (p==1){
                    adapter.updateRes(wenZhangZhuangTiModle.getData());
                }else{
                    adapter.addRes(wenZhangZhuangTiModle.getData());
                }
                mRefreshview.onRefreshComplete();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e(TAG, "onError: " );
            }

            @Override
            public void onCancelled(CancelledException cex) {
                Log.e(TAG, "onCancelled: " );
            }

            @Override
            public void onFinished() {
                Log.e(TAG, "onFinished: " );
            }
        });

    }

    private void initView() {

        mRefreshview = (PullToRefreshListView) findViewById(R.id.pull_listview);

        mRefreshview.setMode(PullToRefreshBase.Mode.BOTH);
        mRefreshview.setOnRefreshListener(this);
        mListView = mRefreshview.getRefreshableView();

        adapter = new WenzhangZhunagtiAdapter(this,null, R.layout.wenzhangzhuanti);
        mListView.setAdapter(adapter);

    }


    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {
        getDataFromNet(State.DOWN);
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {
        getDataFromNet(State.UP);
    }


    enum State{
        DOWN,UP
    }

    public void getDataFromNet(State state){

        switch (state){

            case DOWN:
                p = 1;
                setupView();
                break;
            case UP:
                p++;
                setupView();
                break;

        }


    }

}
