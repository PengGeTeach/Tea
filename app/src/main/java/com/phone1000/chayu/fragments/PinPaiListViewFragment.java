package com.phone1000.chayu.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
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

/**
 * Created by Administrator on 2016/11/29 0029.
 */
public class PinPaiListViewFragment extends Fragment{

    public static final String TAG = PinPaiListViewFragment.class.getSimpleName();

    private View layout;
    private TextView mPinpaitextview;
    private PullToRefreshListView mPingpaiListView;
    private String brandid;
    private PinPaiListViewFragmentAdapter adapter;
    private String order;
    private String review_year;

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
        params.addParameter("brandid",brandid);
        params.addParameter("version",5);
        params.addParameter("imei","4731955d1f681a93");
        params.addParameter("source","3");
        params.addParameter("versionCode","2.2.4");
        params.addParameter("pageSize","10");
        params.addParameter("agent","5");
        params.addParameter("p","1");

        x.http().post(params, new Callback.CommonCallback<String>() {


            @Override
            public void onSuccess(String result) {
                Log.e(TAG, "onSuccess:+ PinPaiListViewFragment"+result );

                Gson gson = new Gson();
                PinPaiListViewModle pinPaiListViewModle = gson.fromJson(result, PinPaiListViewModle.class);

                mPinpaitextview.setText(pinPaiListViewModle.getData().getCount());

                adapter = new PinPaiListViewFragmentAdapter(getActivity(),pinPaiListViewModle.getData().getList(), R.layout.item_tealistconlist);
                mPingpaiListView.setAdapter(adapter);
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

        switch (event.getWhat()){

            case 197:
                Log.e(TAG, "onEvent: "+"我在这里接收到数据了" );
                PinPaiGridViewEvent stickyEvent = EventBus.getDefault().getStickyEvent(PinPaiGridViewEvent.class);
                brandid = stickyEvent.getId();

                setupView();

                break;

            case 195:
                PinPaiGridViewEvent stickyEvent1 = EventBus.getDefault().getStickyEvent(PinPaiGridViewEvent.class);
                order = stickyEvent1.getOrder();

                RequestParams params = new RequestParams(UtilPath.PINPAI_LISTVIEW);
                params.addParameter("order",order);
                params.addParameter("brandid",brandid);
                params.addParameter("version",5);
                params.addParameter("imei","4731955d1f681a93");
                params.addParameter("source","3");
                params.addParameter("versionCode","2.2.4");
                params.addParameter("pageSize","10");
                params.addParameter("agent","5");
                params.addParameter("p","1");

                x.http().post(params, new Callback.CommonCallback<String>() {


                    @Override
                    public void onSuccess(String result) {
                        Log.e(TAG, "onSuccess:+ PinPaiListViewFragment"+result );

                        Gson gson = new Gson();
                        PinPaiListViewModle pinPaiListViewModle = gson.fromJson(result, PinPaiListViewModle.class);

                        mPinpaitextview.setText(pinPaiListViewModle.getData().getCount());

                        adapter = new PinPaiListViewFragmentAdapter(getActivity(),pinPaiListViewModle.getData().getList(), R.layout.item_tealistconlist);
                        mPingpaiListView.setAdapter(adapter);
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

                break;

            case 194:
                PinPaiGridViewEvent stickyEvent2 = EventBus.getDefault().getStickyEvent(PinPaiGridViewEvent.class);
                review_year = stickyEvent2.getReview_year();

                RequestParams params1 = new RequestParams(UtilPath.PINPAI_LISTVIEW);
                params1.addParameter("review_year",review_year);
                params1.addParameter("brandid",brandid);
                params1.addParameter("version",5);
                params1.addParameter("imei","4731955d1f681a93");
                params1.addParameter("source","3");
                params1.addParameter("versionCode","2.2.4");
                params1.addParameter("pageSize","10");
                params1.addParameter("agent","5");
                params1.addParameter("p","1");

                x.http().post(params1, new Callback.CommonCallback<String>() {


                    @Override
                    public void onSuccess(String result) {
                        Log.e(TAG, "onSuccess:+ PinPaiListViewFragment"+result );

                        Gson gson = new Gson();
                        PinPaiListViewModle pinPaiListViewModle = gson.fromJson(result, PinPaiListViewModle.class);

                        mPinpaitextview.setText(pinPaiListViewModle.getData().getCount());

                        adapter = new PinPaiListViewFragmentAdapter(getActivity(),pinPaiListViewModle.getData().getList(), R.layout.item_tealistconlist);
                        mPingpaiListView.setAdapter(adapter);
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


                break;

            case 192:

                PinPaiGridViewEvent stickyEvent3 = EventBus.getDefault().getStickyEvent(PinPaiGridViewEvent.class);
                order = stickyEvent3.getOrder();

                RequestParams params2 = new RequestParams(UtilPath.PINPAI_LISTVIEW);
                params2.addParameter("order",order);
                params2.addParameter("brandid",brandid);
                params2.addParameter("version",5);
                params2.addParameter("imei","4731955d1f681a93");
                params2.addParameter("source","3");
                params2.addParameter("versionCode","2.2.4");
                params2.addParameter("pageSize","10");
                params2.addParameter("agent","5");
                params2.addParameter("p","1");
                x.http().post(params2, new Callback.CommonCallback<String>() {

                    @Override
                    public void onSuccess(String result) {
                        Log.e(TAG, "onSuccess:+ PinPaiListViewFragment"+result );

                        Gson gson = new Gson();
                        PinPaiListViewModle pinPaiListViewModle = gson.fromJson(result, PinPaiListViewModle.class);

                        mPinpaitextview.setText(pinPaiListViewModle.getData().getCount());

                        adapter = new PinPaiListViewFragmentAdapter(getActivity(),pinPaiListViewModle.getData().getList(), R.layout.item_tealistconlist);
                        mPingpaiListView.setAdapter(adapter);
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

                break;
        }

    }

}
