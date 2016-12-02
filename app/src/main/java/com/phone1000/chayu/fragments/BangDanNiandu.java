package com.phone1000.chayu.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.phone1000.chayu.R;
import com.phone1000.chayu.adapters.BangDanNianduAdapter;
import com.phone1000.chayu.event.BangDanEvent;
import com.phone1000.chayu.event.BnagDanToFragmentEvent;
import com.phone1000.chayu.modles.BnagdanActivity;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by Administrator on 2016/11/30 0030.
 */
public class BangDanNiandu extends Fragment implements AdapterView.OnItemClickListener {

    private static final String TAG = BangDanNiandu.class.getSimpleName();
    private View layout;
    private ListView mBangDanListView;
    private BnagdanActivity bangdan;
    private BangDanNianduAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        bangdan = bundle.getParcelable("bangdan");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        layout = inflater.inflate(R.layout.bangdannianfen,container,false);
        return layout;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mBangDanListView = (ListView) layout.findViewById(R.id.bangdanniandu_lv);
        mBangDanListView.setOnItemClickListener(this);
        setupView();

    }

    private void setupView() {

        adapter = new BangDanNianduAdapter(getContext(),bangdan.getData().getYear(), R.layout.pinjianniandu_item);

        mBangDanListView.setAdapter(adapter);
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        Log.e(TAG, "onItemClick: "+i );

        BangDanEvent bangDanEvent = new BangDanEvent(200);
        BnagDanToFragmentEvent event = new BnagDanToFragmentEvent(102);
        event.setNianfen(bangdan.getData().getYear().get(i).getName());
        EventBus.getDefault().post(event);
        String id = bangdan.getData().getYear().get(i).getId();
        bangDanEvent.setYear(id);
        EventBus.getDefault().postSticky(bangDanEvent);

    }
}
