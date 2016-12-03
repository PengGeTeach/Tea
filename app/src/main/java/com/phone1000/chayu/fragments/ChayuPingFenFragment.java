package com.phone1000.chayu.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.phone1000.chayu.R;
import com.phone1000.chayu.adapters.ChaYuPingFenBangFragmentAdapter;
import com.phone1000.chayu.event.BangDanEvent;
import com.phone1000.chayu.modles.BangDanDetaileModle;
import com.phone1000.chayu.path.UtilPath;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by Administrator on 2016/11/30 0030.
 */
public class ChayuPingFenFragment extends Fragment implements PullToRefreshBase.OnRefreshListener2{

    public static final String TAG = ChayuPingFenFragment.class.getSimpleName();
    private View layout;
    private PullToRefreshListView mRefresh;
    private String order = null;
    private String year = null;
    private String bid = null;
    private BangDanDetaileModle bangDanDetaileModle;
    private int p = 1;
    private ChaYuPingFenBangFragmentAdapter adapter;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        order = bundle.getString("bang");
        year = bundle.getString("year");
        bid = bundle.getString("bid");

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        layout = inflater.inflate(R.layout.chayupinfenfragment,container,false);
        return layout;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();

        setupView();

    }

    private void setupView() {

        RequestParams params = new RequestParams(UtilPath.CHA_YU_PING_FEN_BANG);
        params.addParameter("order",order);
        params.addParameter("year",year);
        params.addParameter("bid",bid);
        params.addParameter("source","3");
        params.addParameter("version","5");
        params.addParameter("imei","4731955d1f681a93");
        params.addParameter("pagSize","20");
        params.addParameter("versionCode","2.2.4");
        params.addParameter("agent","5");
        params.addParameter("p",p);
        x.http().post(params, new Callback.CommonCallback<String>() {

            @Override
            public void onSuccess(String result) {
                Log.e(TAG, "onSuccess: "+result );
                mRefresh.onRefreshComplete();
                Gson gson = new Gson();
                if (p>1){
                    Log.e(TAG, "onSuccess:我在这里 " );
                    BangDanDetaileModle bangDanDetaileModle1 = gson.fromJson(result, BangDanDetaileModle.class);
                    adapter.addRes(bangDanDetaileModle1.getData());
                    return;
                }
                bangDanDetaileModle = gson.fromJson(result, BangDanDetaileModle.class);
                adapter.updateRes(bangDanDetaileModle.getData());


            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e(TAG, "onError: "+ex.getMessage() );
                Log.e(TAG, "onError: "+ex.getCause() );
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

        mRefresh = ((PullToRefreshListView) layout.findViewById(R.id.pull_listview));
        mRefresh.setMode(PullToRefreshBase.Mode.PULL_FROM_END);

        mRefresh.setOnRefreshListener(this);
        adapter = new ChaYuPingFenBangFragmentAdapter(getActivity(),null, R.layout.bangdan_listview_child_item);
        mRefresh.setAdapter(adapter);
    }

    @Subscribe(sticky = true,threadMode = ThreadMode.MAIN)
    public void onEvent(BangDanEvent event){
        p = 1;
        switch (event.getWHAT()){
            case 200:
                BangDanEvent stickyEvent = EventBus.getDefault().getStickyEvent(BangDanEvent.class);
                order = stickyEvent.getOrider();
                bid = stickyEvent.getBid();
                year = stickyEvent.getYear();
                Log.e(TAG, "onEvent: +粘性事件传递的数据"+bid );
                bid = stickyEvent.getBid();
                year = event.getYear();
                setupView();
                break;
        }

    }

    @Override
    public void onStart() {
        super.onStart();
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {

    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {
        p++;
        setupView();
    }
}
