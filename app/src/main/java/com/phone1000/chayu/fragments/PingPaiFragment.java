package com.phone1000.chayu.fragments;

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

/**
 * Created by Administrator on 2016/11/28 0028.
 */
public class PingPaiFragment extends Fragment implements AdapterView.OnItemClickListener {

    public static final String TAG = PingPaiFragment.class.getSimpleName();
    private View layout;
    private ExpandableListView mExListView;
    private TeaComm data;
    private PinPaiFragmentAdapter adapter;


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
        mExListView.setOnItemClickListener(this);

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

    @Subscribe
    public void onEvent(Chapinevent event){
        TeaComm teaComm = event.getTeaComm();

        adapter = new PinPaiFragmentAdapter(getActivity(),teaComm.getData().getCategory_list());
        mExListView.setGroupIndicator(null);
        mExListView.setAdapter(adapter);

        ListViewUtils.setListViewHeightBasedOnChildren(mExListView);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Log.e(TAG, "onItemClick: "+"我点击了这" +i);
        if("全部".equals(data.getData().getCategory_list().get(i).getName())) {
            Log.e(TAG, "onItemClick: "+"我点击了这里" );
            TeaListEvent event = new TeaListEvent(0x110);
            //event.setSid(data.get(tag).getName());
            event.setBid(data.getData().getCategory_list().get(i).getBid()+"");
            EventBus.getDefault().postSticky(event);
            Intent intent = new Intent(getActivity(), TeaListActivity.class);
            startActivity(intent);
        }
    }
}
