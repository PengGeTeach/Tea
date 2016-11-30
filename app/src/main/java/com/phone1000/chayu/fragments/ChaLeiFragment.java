package com.phone1000.chayu.fragments;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.google.gson.Gson;
import com.phone1000.chayu.PinPaiActivity;
import com.phone1000.chayu.R;
import com.phone1000.chayu.adapters.GridViewAdapter;
import com.phone1000.chayu.adapters.PinPaiGridViewAdapter;
import com.phone1000.chayu.adapters.PinPaiGridViewAdapter2;
import com.phone1000.chayu.adapters.RemenPinPaiAdapter;
import com.phone1000.chayu.modles.ChaLeiModle;
import com.phone1000.chayu.path.UtilPath;
import com.phone1000.chayu.weidgt.MyGridView;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by Administrator on 2016/11/28 0028.
 */
public class ChaLeiFragment extends Fragment implements PinPaiGridViewAdapter.Pingpaiitem ,PinPaiGridViewAdapter2.SendPinpaiPositiob, AdapterView.OnItemClickListener {

    private static final String TAG = ChaLeiFragment.class.getSimpleName();
    private View layout;
    private MyGridView mGridViewRemen;
    private MyGridView mGridviewpinpai;
    private ChaLeiModle chaLeiModle;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        layout = inflater.inflate(R.layout.cahleifragment,container,false);
        return layout;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initView();

        setupView();

    }

    private void setupView() {

        RequestParams params = new RequestParams(UtilPath.PIN_PAI);
        params.addParameter("source","3");
        params.addParameter("version","5");
        params.addParameter("imei","4731955d1f681a93");
        params.addParameter("versionCode","2.2.4");
        params.addParameter("agent","5");
        params.addParameter("type","brand");
        x.http().post(params, new Callback.CommonCallback<String>() {
            private PinPaiGridViewAdapter pinPaiGridViewAdapter;
            private RemenPinPaiAdapter remenpinpaiadapter;

            @Override
            public void onSuccess(String result) {
                Log.e(TAG, "onSuccess:888 "+result );
                Gson gson = new Gson();
                chaLeiModle = gson.fromJson(result, ChaLeiModle.class);

                remenpinpaiadapter = new RemenPinPaiAdapter(getActivity(),chaLeiModle.getData().getHot_list(),R.layout.chalei_remen_item);
                mGridViewRemen.setSelector(new ColorDrawable(Color.TRANSPARENT));
                mGridViewRemen.setAdapter(remenpinpaiadapter);

                pinPaiGridViewAdapter = new PinPaiGridViewAdapter(getActivity(),chaLeiModle.getData().getBrand_list(), R.layout.gridview_item,ChaLeiFragment.this);
                mGridviewpinpai.setSelector(new ColorDrawable(Color.TRANSPARENT));
                mGridviewpinpai.setNumColumns(5);
                mGridviewpinpai.setAdapter(pinPaiGridViewAdapter);

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e(TAG, "onError: "+ex.getMessage() );
                Log.e(TAG, "onError: "+ex.getCause() );
            }

            @Override
            public void onCancelled(CancelledException cex) {
                Log.e(TAG, "onCancelled: " );
            }

            @Override
            public void onFinished() {
                Log.e(TAG, "onFinished: " );
            }
        });

    }

    private void initView() {

        mGridViewRemen = (MyGridView) layout.findViewById(R.id.gridview_remenpinpai);
        mGridViewRemen.setOnItemClickListener(this);
        mGridviewpinpai = ((MyGridView) layout.findViewById(R.id.gridview_pinpai));

    }

    @Override
    public void sendpingpaiitem(int position) {

        Log.e(TAG, "sendpingpaiitem: "+chaLeiModle.getData().getBrand_list().get(position).getLetter() );

        if (position>0){

            PinPaiGridViewAdapter2 pinPaiGridViewAdapter2 = new PinPaiGridViewAdapter2(getActivity(), chaLeiModle.getData().getBrand_list().get(position).getList(),this,position, R.layout.pingpai_item2,R.layout.gridview_item);

            ChaLeiModle.DataBean.BrandListBean.BrandListChild e = new ChaLeiModle.DataBean.BrandListBean.BrandListChild();
            e.setName(chaLeiModle.getData().getBrand_list().get(position).getLetter());
            chaLeiModle.getData().getBrand_list().get(position).getList().add(0,e);

            Log.e(TAG, "sendpingpaiitem: +测试数量"+chaLeiModle.getData().getBrand_list().get(position).getList().size() );

            for (int i = 0; i <chaLeiModle.getData().getBrand_list().get(position).getList().size() ; i++) {
                if (i == 0){
                    chaLeiModle.getData().getBrand_list().get(position).getList().get(i).setType(0);
                }else{
                    chaLeiModle.getData().getBrand_list().get(position).getList().get(i).setType(1);
                }
            }

            mGridviewpinpai.setNumColumns(3);
            mGridviewpinpai.setAdapter(pinPaiGridViewAdapter2);

        }

    }

    @Override
    public void sendposition(int position) {

        if(position == 0){
            PinPaiGridViewAdapter pinPaiGridViewAdapter = new PinPaiGridViewAdapter(getActivity(), chaLeiModle.getData().getBrand_list(), R.layout.gridview_item, ChaLeiFragment.this);
            mGridviewpinpai.setNumColumns(5);
            mGridviewpinpai.setAdapter(pinPaiGridViewAdapter);
        }

        //chaLeiModle.getData().getBrand_list().get(position).getList().remove(0);

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        //chaLeiModle.getData().getHot_list().get(i)
        Log.e(TAG, "onItemClick: "+chaLeiModle.getData().getHot_list().get(i).getName()+""+ chaLeiModle.getData().getHot_list().get(i).getBrandid());
        Intent intent = new Intent(getActivity(), PinPaiActivity.class);
        intent.putExtra("name",chaLeiModle.getData().getHot_list().get(i).getName());
        intent.putExtra("brandid",chaLeiModle.getData().getHot_list().get(i).getBrandid()+"");
        startActivity(intent);
    }
}
