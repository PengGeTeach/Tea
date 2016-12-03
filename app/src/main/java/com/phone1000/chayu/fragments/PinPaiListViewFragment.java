package com.phone1000.chayu.fragments;

import android.content.Intent;
import android.os.Bundle;
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
import com.phone1000.chayu.adapters.PinPaiListViewFragmentAdapter;
import com.phone1000.chayu.event.PinPaiGridViewEvent;
import com.phone1000.chayu.modles.PinPaiListViewModle;
import com.phone1000.chayu.path.UtilPath;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;

/**
 * Created by Administrator on 2016/11/29 0029.
 */
public class PinPaiListViewFragment extends Fragment implements PullToRefreshBase.OnRefreshListener2, AdapterView.OnItemClickListener {

    public static final String TAG = PinPaiListViewFragment.class.getSimpleName();

    private View layout;
    private TextView mPinpaitextview;
    private PullToRefreshListView mPingpaiListView;
    private String brandid = null;
    private PinPaiListViewFragmentAdapter adapter;
    private String order = null;
    private String review_year = null;
    private int p =1;
    private int pagenum;
    private List<PinPaiListViewModle.DataBean.ListBean> data;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();

        brandid = bundle.getString("brandid");

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        layout = inflater.inflate(R.layout.pinpailistviewfragment,container,false);
        return layout;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initView();

        setupView();


    }

    private void setupView() {

        RequestParams params = new RequestParams(UtilPath.PINPAI_LISTVIEW);
        params.addParameter("review_year",review_year);
        params.addParameter("order",order);
        params.addParameter("brandid",brandid);
        params.addParameter("version",5);
        params.addParameter("imei","4731955d1f681a93");
        params.addParameter("source","3");
        params.addParameter("versionCode","2.2.4");
        params.addParameter("pageSize","10");
        params.addParameter("agent","5");
        params.addParameter("p",p);

        x.http().post(params, new Callback.CommonCallback<String>() {

            @Override
            public void onSuccess(String result) {
                Log.e(TAG, "onSuccess:+ PinPaiListViewFragment"+result );
                mPingpaiListView.onRefreshComplete();

                Gson gson = new Gson();

                PinPaiListViewModle pinPaiListViewModle = gson.fromJson(result, PinPaiListViewModle.class);

                mPinpaitextview.setText(pinPaiListViewModle.getData().getCount());
                pagenum = Integer.valueOf(pinPaiListViewModle.getData().getCount());

                data = pinPaiListViewModle.getData().getList();

                if (p>1){
                    List<PinPaiListViewModle.DataBean.ListBean> list = pinPaiListViewModle.getData().getList();

                    adapter.addRes(list);
                    return;
                }
                data = pinPaiListViewModle.getData().getList();
                adapter.updateRes(data);

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

        mPinpaitextview = (TextView) layout.findViewById(R.id.pinpai_textview);
        mPingpaiListView = ((PullToRefreshListView) layout.findViewById(R.id.pinpai_listview));
        mPingpaiListView.setMode(PullToRefreshBase.Mode.PULL_FROM_END);
        mPingpaiListView.setOnRefreshListener(this);
        ListView refreshableView = mPingpaiListView.getRefreshableView();
        refreshableView.setOnItemClickListener(this);
        adapter = new PinPaiListViewFragmentAdapter(getActivity(),null, R.layout.item_tealistconlist);
        mPingpaiListView.setAdapter(adapter);
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
    @Subscribe(sticky = true,threadMode = ThreadMode.MAIN)
    public void onEvent(PinPaiGridViewEvent event){
        mPingpaiListView.setMode(PullToRefreshBase.Mode.PULL_FROM_END);
        p = 1;
        switch (event.getWhat()){

            case 197:
                order = null;
                review_year = null;
                Log.e(TAG, "onEvent: "+"我在这里接收到数据了" );
                PinPaiGridViewEvent stickyEvent = EventBus.getDefault().getStickyEvent(PinPaiGridViewEvent.class);
                brandid = stickyEvent.getId();

                break;

            case 195:
                order = null;
                review_year = null;
                PinPaiGridViewEvent stickyEvent1 = EventBus.getDefault().getStickyEvent(PinPaiGridViewEvent.class);
                order = stickyEvent1.getOrder();

                break;

            case 194:
                order = null;
                review_year = null;
                PinPaiGridViewEvent stickyEvent2 = EventBus.getDefault().getStickyEvent(PinPaiGridViewEvent.class);
                review_year = stickyEvent2.getReview_year();

                break;

        }

        setupView();

    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {

    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {
        p++;
        if (p>(pagenum/10)+1){
            mPingpaiListView.setMode(PullToRefreshBase.Mode.DISABLED);
        }
        setupView();

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        Log.e(TAG, "onItemClick: "+i +"size"+data.size());
        PinPaiListViewModle.DataBean.ListBean itemAtPosition = (PinPaiListViewModle.DataBean.ListBean) adapterView.getItemAtPosition(i);
        Intent intent = new Intent(getActivity(), DetailsInFormation.class);
        intent.putExtra("path","http://chaping.chayu.com/tea/"+itemAtPosition.getId());
        startActivity(intent);

    }
}
