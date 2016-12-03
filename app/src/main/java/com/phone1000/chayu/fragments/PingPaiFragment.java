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
import android.widget.ExpandableListView;

import com.phone1000.chayu.R;
import com.phone1000.chayu.activity.TeaListActivity;
import com.phone1000.chayu.adapters.PinPaiFragmentAdapter;
import com.phone1000.chayu.event.Chapinevent;
import com.phone1000.chayu.modles.TeaComm;
import com.phone1000.chayu.event.TeaListEvent;
import com.phone1000.chayu.utils.ListViewUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

/**
 * Created by Administrator on 2016/11/28 0028.
 */
public class PingPaiFragment extends Fragment implements ExpandableListView.OnGroupClickListener {

    public static final String TAG = PingPaiFragment.class.getSimpleName();
    private View layout;
    private ExpandableListView mExListView;
    private TeaComm data;
    private PinPaiFragmentAdapter adapter;
    List<TeaComm.DataBean.CategoryListBean> category_list;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        layout = inflater.inflate(R.layout.pinpaifragment,container,false);
        return layout;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initView();
    }

    private void initView() {

        mExListView = ((ExpandableListView) layout.findViewById(R.id.expanded_lv));
        mExListView.setFocusable(false);
        mExListView.setOnGroupClickListener(this);

    }

    @Override
    public void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(sticky = true ,threadMode = ThreadMode.MAIN)
    public void onEvent(Chapinevent event){
        //TeaComm teaComm = event.getTeaComm();
        //List<TeaComm.DataBean.CategoryListBean> category_list = teaComm.getData().getCategory_list();
        Log.e(TAG, "onEvent: 我到了这里" );
        category_list = event.getTeaComm();
        adapter = new PinPaiFragmentAdapter(getActivity(), category_list);
        mExListView.setGroupIndicator(null);
        mExListView.setAdapter(adapter);

        ListViewUtils.setListViewHeightBasedOnChildren(mExListView);
    }

    @Override
    public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long l) {

        Log.e(TAG, "onGroupClick:父布局点击的条目"+i );

        if (i==0){
            TeaListEvent event = new TeaListEvent(0x110);
            event.setBid(category_list.get(i).getBid()+"");
            event.setTeaName(category_list.get(i).getName());
            EventBus.getDefault().postSticky(event);
            Intent intent = new Intent(getActivity(), TeaListActivity.class);
            startActivity(intent);
        }

        return false;
    }




}
