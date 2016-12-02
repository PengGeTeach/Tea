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
import com.phone1000.chayu.adapters.ChaLeiBangdanFragmentAdapter;
import com.phone1000.chayu.event.BangDanEvent;
import com.phone1000.chayu.event.BnagDanToFragmentEvent;
import com.phone1000.chayu.modles.BnagdanActivity;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by Administrator on 2016/11/30 0030.
 */
public class ChaLeiBangdanFragment extends Fragment implements AdapterView.OnItemClickListener {

    public static final String TAG = ChaLeiBangdanFragment.class.getSimpleName();
    private View layout;
    private PullToRefreshListView mRefresh;
    private BnagdanActivity bangdan;
    private ChaLeiBangdanFragmentAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        bangdan = bundle.getParcelable("bangdan");

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        layout = inflater.inflate(R.layout.chaleibangdanlistview,container,false);
        return layout;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();

        setupView();

    }

    private void setupView() {

        adapter = new ChaLeiBangdanFragmentAdapter(getActivity(),bangdan.getData().getTeaCate(), R.layout.chapingparentitem);
        mRefresh.setAdapter(adapter);

        mRefresh.setOnItemClickListener(this);

    }

    private void initView() {

        mRefresh = ((PullToRefreshListView) layout.findViewById(R.id.pull_listview));
        mRefresh.setMode(PullToRefreshBase.Mode.PULL_FROM_END);

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Log.e(TAG, "onItemClick: "+i );
        BangDanEvent bangDanEvent = new BangDanEvent(200);
        BnagDanToFragmentEvent event = new BnagDanToFragmentEvent(101);
        event.setLeibie(bangdan.getData().getTeaCate().get(i-1).getName());
        EventBus.getDefault().post(event);
        String id = bangdan.getData().getTeaCate().get(i-1).getId();
        bangDanEvent.setBid(id);
        EventBus.getDefault().postSticky(bangDanEvent);
    }
}
