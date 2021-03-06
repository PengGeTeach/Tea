package com.phone1000.chayu;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.phone1000.chayu.adapters.WenZhangZuiXinReMenAdapter;
import com.phone1000.chayu.event.Chapinevent;
import com.phone1000.chayu.modles.ListBean;
import com.phone1000.chayu.modles.TeaComm;
import com.phone1000.chayu.modles.WenZhangModle;
import com.phone1000.chayu.modles.WenZhangShangLaJiaZai;
import com.phone1000.chayu.path.UtilPath;

import org.greenrobot.eventbus.EventBus;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by Administrator on 2016/12/2 0002.
 */
public class WenzhangZuiXinReMen extends AppCompatActivity implements PullToRefreshBase.OnRefreshListener2, AdapterView.OnItemClickListener {

    private static final String TAG = WenzhangZuiXinReMen.class.getSimpleName();
    private String order;
    private PullToRefreshListView pullToRefreshListView;
    private PullToRefreshListView mRefreshListView;
    private ListView mListView;
    private WenZhangZuiXinReMenAdapter adapter;
    private int p = 1;

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
        params.addParameter("order",order);
        params.addParameter("pageNo",p);
        params.addParameter("source","3");
        params.addParameter("version","5");
        params.addParameter("imei","4731955d1f681a93");
        params.addParameter("versionCode","2.2.4");
        params.addParameter("pageSize","10");
        params.addParameter("agent","5");
        params.addParameter("type","all");

        x.http().post(params, new Callback.CommonCallback<String>() {
            private WenZhangShangLaJiaZai wenZhangShangLaJiaZai;

            @Override
            public void onSuccess(String result) {
                Log.e(TAG, "onSuccess: "+result );
                Gson gson = new Gson();
                wenZhangShangLaJiaZai = gson.fromJson(result, WenZhangShangLaJiaZai.class);
               if (p==1){
                    adapter.updateRes(wenZhangShangLaJiaZai.getData());
               }else{
                    adapter.addRes(wenZhangShangLaJiaZai.getData());
                }
                mRefreshListView.onRefreshComplete();
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

        mRefreshListView = ((PullToRefreshListView) findViewById(R.id.pull_listview));
        mRefreshListView.setMode(PullToRefreshBase.Mode.PULL_FROM_END);
        mListView = mRefreshListView.getRefreshableView();
        adapter = new WenZhangZuiXinReMenAdapter(this,null, R.layout.wenzhang_item1);
        mListView.setAdapter(adapter);
        mRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);
        mRefreshListView.setOnRefreshListener(this);
        mListView.setOnItemClickListener(this);

    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {
        getDataFromNet(State.DOWN);
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {
        getDataFromNet(State.UP);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        Log.e(TAG, "onItemClick: 点解位置"+i );
        ListBean itemAtPosition = (ListBean) adapterView.getItemAtPosition(i);
        Intent intent = new Intent(this,DetailsInFormation.class);
        intent.putExtra("path","http://m.chayu.com/article/"+ itemAtPosition.getId());
        startActivity(intent);
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
