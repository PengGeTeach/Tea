package com.phone1000.chayu.fragments;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.phone1000.chayu.R;
import com.phone1000.chayu.event.PinPaiGridViewEvent;
import com.phone1000.chayu.modles.PinPaiGridViewModle;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by Administrator on 2016/11/30 0030.
 */
public class PinPaiPingFenFragment extends Fragment implements View.OnClickListener {


    private View layout;
    private TextView mChayu;
    private TextView mZonghe;
    private PinPaiGridViewModle pinPaiGridViewModle;
    private  String brandid;
    private String order;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        this.pinPaiGridViewModle = bundle.getParcelable("bundle");
        brandid = bundle.getString("id");

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        layout = inflater.inflate(R.layout.pinpaipingfen,container,false);
        return layout;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initView();

    }

    private void initView() {

        mChayu = ((TextView) layout.findViewById(R.id.chayu));
        mZonghe = ((TextView) layout.findViewById(R.id.zenghe));

        mChayu.setOnClickListener(this);
        mZonghe.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        PinPaiGridViewEvent event = new PinPaiGridViewEvent(196);
        EventBus.getDefault().post(event);
        PinPaiGridViewEvent pinPaiGridViewEvent = new PinPaiGridViewEvent(195);

        switch (view.getId()){
            case R.id.chayu:
                order = "review_score";
                break;
            case R.id.zenghe:
                order = "score";
                break;
        }
        pinPaiGridViewEvent.setOrder(order);
        EventBus.getDefault().postSticky(pinPaiGridViewEvent);
    }
}
