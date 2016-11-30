package com.phone1000.chayu.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.phone1000.chayu.R;
import com.phone1000.chayu.adapters.PinJianNianDuAdapter;
import com.phone1000.chayu.event.PinPaiGridViewEvent;
import com.phone1000.chayu.modles.PinPaiGridViewModle;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by Administrator on 2016/11/30 0030.
 */
public class PinJianNiandu extends Fragment implements AdapterView.OnItemClickListener {

    private static final String TAG = PinJianNiandu.class.getSimpleName();
    private View layout;
    private PullToRefreshListView pullToRefreshListView;
    private String brandid;
    private PinPaiGridViewModle pinPaiGridViewModle;
    private PinJianNianDuAdapter adapter;
    private String review_year;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();

        brandid = bundle.getString("id");
        pinPaiGridViewModle = bundle.getParcelable("bundle");

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        layout = inflater.inflate(R.layout.pinjianniandu,container,false);
        return layout;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initView();

    }

    private void initView() {

        pullToRefreshListView = (PullToRefreshListView) layout.findViewById(R.id.pulllistview);
        pullToRefreshListView.setMode(PullToRefreshBase.Mode.PULL_FROM_END);
        pullToRefreshListView.setOnItemClickListener(this);
        adapter = new PinJianNianDuAdapter(getActivity(),pinPaiGridViewModle.getData().getPingjiannianfen(), R.layout.pinjianniandu_item);
        pullToRefreshListView.setAdapter(adapter);

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        int id = pinPaiGridViewModle.getData().getPingjiannianfen().get(i-1).getId();
        PinPaiGridViewEvent event = new PinPaiGridViewEvent(196);
        EventBus.getDefault().post(event);
        PinPaiGridViewEvent pinPaiGridViewEvent = new PinPaiGridViewEvent(194);
        review_year = id+"";
        pinPaiGridViewEvent.setReview_year(review_year);
        EventBus.getDefault().postSticky(pinPaiGridViewEvent);
    }
}
