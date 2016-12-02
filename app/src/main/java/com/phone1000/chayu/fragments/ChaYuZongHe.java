package com.phone1000.chayu.fragments;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.phone1000.chayu.R;
import com.phone1000.chayu.event.BangDanEvent;
import com.phone1000.chayu.event.BnagDanToFragmentEvent;
import com.phone1000.chayu.modles.BnagdanActivity;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by Administrator on 2016/11/30 0030.
 */
public class ChaYuZongHe extends Fragment implements RadioGroup.OnCheckedChangeListener {

    public static final String TAG = ChaYuZongHe.class.getSimpleName();
    private View layout;
    private RadioGroup mRadioGroup;
    private BnagdanActivity bangdan;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        bangdan = bundle.getParcelable("bangdan");

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        layout = inflater.inflate(R.layout.chayuzonghe,container,false);
        return layout;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initView();

    }

    private void initView() {

        mRadioGroup = ((RadioGroup) layout.findViewById(R.id.rg_group));

        mRadioGroup.setOnCheckedChangeListener(this);

        RadioButton rg1 = (RadioButton) mRadioGroup.findViewById(R.id.chayu);
        RadioButton rg2 = (RadioButton) mRadioGroup.findViewById(R.id.zonghe);
        rg1.setText(bangdan.getData().getOrder().get(0).getName());
        rg2.setText(bangdan.getData().getOrder().get(1).getName());

    }


    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        BangDanEvent bangDanEvent = new BangDanEvent(200);
        BnagDanToFragmentEvent event = new BnagDanToFragmentEvent(100);
        switch (i){
            case R.id.chayu:
                bangDanEvent.setOrider("1");
                event.setBangming("茶语评分榜");
                break;
            case R.id.zonghe:
                bangDanEvent.setOrider("2");
                event.setBangming("综合评分榜");
                break;
        }
        EventBus.getDefault().post(event);
        EventBus.getDefault().postSticky(bangDanEvent);
    }
}
