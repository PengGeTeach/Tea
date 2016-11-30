package com.phone1000.chayu.fragments;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.phone1000.chayu.R;
import com.phone1000.chayu.adapters.PinPaiGridViewAdapter;
import com.phone1000.chayu.adapters.PinPaiGridViewAdapter3;
import com.phone1000.chayu.adapters.PinPaiGridViewAdapter4;
import com.phone1000.chayu.modles.ChaLeiModle;
import com.phone1000.chayu.modles.PinPaiGridViewModle;
import com.phone1000.chayu.path.UtilPath;
import com.phone1000.chayu.weidgt.MyGridView;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by Administrator on 2016/11/29 0029.
 */
public class PinPaiGridView extends Fragment implements PinPaiGridViewAdapter3.Pingpaiitem ,PinPaiGridViewAdapter4.SendPinpaiPositiob {

    public static final String TAG = PinPaiGridView.class.getSimpleName();
    private View layout;
    private MyGridView mPinPaiView;
    private PinPaiGridViewModle pinPaiGridViewModle;
    private PinPaiGridViewAdapter3 pinPaiGridViewAdapter3;
    private PinPaiGridViewAdapter4 pinPaiGridViewAdapter4;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        pinPaiGridViewModle = bundle.getParcelable("bundle");

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        layout = inflater.inflate(R.layout.pinpaigridview,container,false);
        return layout;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initView();

        setupView();

    }

    private void setupView() {

        pinPaiGridViewAdapter3 = new PinPaiGridViewAdapter3(getActivity(),pinPaiGridViewModle.getData().getBrand_list(), R.layout.gridview_item,PinPaiGridView.this);
        mPinPaiView.setSelector(new ColorDrawable(Color.TRANSPARENT));
        mPinPaiView.setNumColumns(5);
        mPinPaiView.setAdapter(pinPaiGridViewAdapter3);

    }

    private void initView() {

        mPinPaiView = ((MyGridView) layout.findViewById(R.id.pinpai_gridview));

    }

    @Override
    public void sendpingpaiitem(int position) {

        if(position>0){

            pinPaiGridViewAdapter4 = new PinPaiGridViewAdapter4(getActivity(),pinPaiGridViewModle.getData().getBrand_list().get(position).getList(),this, R.layout.pingpai_item2,R.layout.gridview_item);


            PinPaiGridViewModle.DataBean.BrandListBean.BrandListBeanChild e = new PinPaiGridViewModle.DataBean.BrandListBean.BrandListBeanChild();
            e.setName(pinPaiGridViewModle.getData().getBrand_list().get(position).getLetter());
            pinPaiGridViewModle.getData().getBrand_list().get(position).getList().add(0,e);

            Log.e(TAG, "sendpingpaiitem: +测试数量"+pinPaiGridViewModle.getData().getBrand_list().get(position).getList().size() );

            for (int i = 0; i <pinPaiGridViewModle.getData().getBrand_list().get(position).getList().size() ; i++) {
                if (i == 0){
                    pinPaiGridViewModle.getData().getBrand_list().get(position).getList().get(i).setType(0);
                }else{
                    pinPaiGridViewModle.getData().getBrand_list().get(position).getList().get(i).setType(1);
                }
            }


            mPinPaiView.setNumColumns(3);
            mPinPaiView.setAdapter(pinPaiGridViewAdapter4);

        }


    }
    @Override
    public void sendposition(int position) {

        if(position == 0){
            PinPaiGridViewAdapter3 pinPaiGridViewAdapter = new PinPaiGridViewAdapter3(getActivity(),pinPaiGridViewModle.getData().getBrand_list(), R.layout.gridview_item, this);
            mPinPaiView.setNumColumns(5);
            mPinPaiView.setAdapter(pinPaiGridViewAdapter);
        }

    }
}
