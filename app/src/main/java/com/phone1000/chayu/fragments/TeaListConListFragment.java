package com.phone1000.chayu.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.phone1000.chayu.DetailsInFormation;
import com.phone1000.chayu.R;
import com.phone1000.chayu.adapters.TeaListConAdapter;
import com.phone1000.chayu.event.TeaListEvent;
import com.phone1000.chayu.modles.TeaListModel;
import com.phone1000.chayu.path.UtilPath;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;

/**
 * Created by Administrator on 2016/11/27 0027.
 */
public class TeaListConListFragment extends Fragment implements AdapterView.OnItemClickListener,PullToRefreshBase.OnRefreshListener {
    public static final String TAG = TeaListConListFragment.class.getSimpleName();
    private View layout;
    private PullToRefreshListView mPulltoRefreshListView;
    private TeaListConAdapter adapter;
    private int p=1;
    private TextView mCount;
//    private List<TeaListModel.DataBean.ListBean> data;
    private ListView mListView;
    private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case 0x10010:
                    mPulltoRefreshListView.onRefreshComplete();
                    break;
            }

        }
    };
    private String bid;
    private String sid;
    private String order;
    private String review_year;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        layout = inflater.inflate(R.layout.fragment_tea_con_list, container, false);
        return layout;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initView();

    }

    private void initView() {
//        data=new ArrayList<>();
        mPulltoRefreshListView = (PullToRefreshListView) layout.findViewById(R.id.fragment_tea_con_list);
        mPulltoRefreshListView.setMode(PullToRefreshBase.Mode.PULL_FROM_END);
        mPulltoRefreshListView.setOnRefreshListener(this);
        mListView = mPulltoRefreshListView.getRefreshableView();
        adapter = new TeaListConAdapter(getContext(),null);
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(this);
        mCount = (TextView) layout.findViewById(R.id.tea_list_count);

    }

    @Override
    public void onResume() {
        super.onResume();
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }
    @Subscribe(sticky = true,threadMode = ThreadMode.MAIN)
    public void onEvent(TeaListEvent event){
        if (event.getWHAT()==0x10086) {
            bid = event.getBid();
            sid = event.getSid();
            order = event.getOrder();
            Log.e("CanShu", "结果: "+order );
            review_year = event.getReview_year();
            p=1;
            Log.e("CanShu", "onEvent: TealistConListFragment Event  bid:--------->"+bid+"+sid:-----------"+sid+"order:---------"+order+"review_year:---------"+review_year );
            setUpListFromNet(bid,sid,order,review_year);
        }

    }
    //--------------- 下载 ----------------

    private void setUpListFromNet(String bid,String sid,String order,String review_year){

        RequestParams params = new RequestParams(UtilPath.TEALISTS);
        params.addParameter("imei","91f64b1a7dbe8b9e");
        params.addParameter("agent","5");
        params.addParameter("version","5");
        params.addParameter("source","3");
        params.addParameter("versionCode","2.2.4");
        params.addParameter("pageSize","10");
        params.addParameter("bid",bid);
        params.addParameter("sid",sid);
        params.addParameter("p",p+"");
        params.addParameter("order",order);
        params.addParameter("review_year",review_year);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e(TAG, "onSuccess: "+result );
                Gson gson = new Gson();
                TeaListModel teaListModel = gson.fromJson(result, TeaListModel.class);
                List<TeaListModel.DataBean.ListBean> list = teaListModel.getData().getList();
                if (p==1) {
                    adapter.updateRes(list);

                }else {
                    adapter.addRes(list);
                }

                String count = teaListModel.getData().getCount();
                mCount.setText(count);

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e(TAG, "onError: "+ex.getMessage() );
                Log.e(TAG, "onError: "+ex.getCause() );

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {
                Log.e(TAG, "onFinished: " );

            }
        });


    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        if (adapter.getItem(position-1) != null) {
            TeaListModel.DataBean.ListBean item =  adapter.getItem(position-1);
            String id1 = item.getId();
            String url = UtilPath.TEA_XIANGQING + id1;
            Intent intent = new Intent(getActivity(), DetailsInFormation.class);
            intent.putExtra("path",url);
            getActivity().startActivity(intent);

        }


    }

    @Override
    public void onRefresh(PullToRefreshBase refreshView) {

        p++;
        setUpListFromNet(bid,sid,order,review_year);
        mHandler.sendEmptyMessageDelayed(0x10010,1000);


    }
}
