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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.phone1000.chayu.ChayuPingFenBang;
import com.phone1000.chayu.DetailsInFormation;
import com.phone1000.chayu.R;
import com.phone1000.chayu.adapters.BangDanAdapter;
import com.phone1000.chayu.modles.BangDanModle;
import com.phone1000.chayu.path.UtilPath;
import com.phone1000.chayu.utils.ListViewUtils;
import com.phone1000.chayu.utils.NoScrollListView;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by Administrator on 2016/11/29 0029.
 */
public class BangDanFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener {

    public static final String TAG = BangDanFragment.class.getSimpleName();
    private View layout;
    private LinearLayout mLinearLayout;
    private NoScrollListView m_listView;
    private BangDanAdapter bangDanAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        layout = inflater.inflate(R.layout.bangdanfragment,container,false);
        return layout;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initView();

        setupView();
    }

    private void setupView() {

        RequestParams params = new RequestParams(UtilPath.BANG_DAN);
        params.addParameter("source","3");
        params.addParameter("version","5");
        params.addParameter("imei","4731955d1f681a93");
        params.addParameter("versionCode","2.2.4");
        params.addParameter("agent","5");
        params.addParameter("type","top");

        x.http().post(params, new Callback.CommonCallback<String>() {
            private BangDanModle bangDanModle;

            @Override
            public void onSuccess(String result) {
                Log.e(TAG, "onSuccess: 这里" +result);

                Gson gson = new Gson();
                bangDanModle = gson.fromJson(result, BangDanModle.class);
                
                setupWithBnagdan(bangDanModle);

                setupWithBnagdanListview(bangDanModle);
                
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e(TAG, "onError:出现错误 ");
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

    private void setupWithBnagdanListview(BangDanModle bangDanModle) {

        bangDanAdapter.updateRes(bangDanModle.getData().getTopList());

//        ListViewUtils.setListViewHeightBasedOnChildren(m_listView);
    }

    private void setupWithBnagdan(BangDanModle bangDanModle) {

        for (int i = 0; i < bangDanModle.getData().getTopArr().size(); i++) {
            LinearLayout childLinear = (LinearLayout) mLinearLayout.getChildAt(i);
            ImageView imageView = (ImageView) childLinear.getChildAt(0);
            x.image().bind(imageView,bangDanModle.getData().getTopArr().get(i).getThumb());
            TextView textView = (TextView) childLinear.getChildAt(1);
            textView.setText(bangDanModle.getData().getTopArr().get(i).getTitle());
            TextView textView1 = (TextView) childLinear.getChildAt(2);
            textView1.setText(bangDanModle.getData().getTopArr().get(i).getTitles());
        }

        LinearLayout layout1 = (LinearLayout) mLinearLayout.findViewById(R.id.linear_1);
        LinearLayout layout2 = (LinearLayout) mLinearLayout.findViewById(R.id.linear_2);
        LinearLayout layout3 = (LinearLayout) mLinearLayout.findViewById(R.id.linear_3);
        LinearLayout layout4 = (LinearLayout) mLinearLayout.findViewById(R.id.linear_4);
        layout1.setOnClickListener(this);
        layout2.setOnClickListener(this);
        layout3.setOnClickListener(this);
        layout4.setOnClickListener(this);

    }

    private void initView() {
        mLinearLayout = ((LinearLayout) layout.findViewById(R.id.bangdan_ll));
        m_listView = ((NoScrollListView) layout.findViewById(R.id.bangdan_lv));
        bangDanAdapter = new BangDanAdapter(getActivity(), null, R.layout.bandgan_listview_item);
        m_listView.setAdapter(bangDanAdapter);
        m_listView.setFocusable(false);

        m_listView.setOnItemClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.linear_1:
                Log.e(TAG, "onClick: 点击了这里" );
                Intent intent = new Intent(getActivity(), ChayuPingFenBang.class);
                intent.putExtra("bang","1");
                startActivity(intent);
                break;
            case R.id.linear_2:
                Intent intent1 = new Intent(getActivity(), ChayuPingFenBang.class);
                intent1.putExtra("bang","2");
                startActivity(intent1);
                break;
            case R.id.linear_3:
                Intent intent2 = new Intent(getActivity(), DetailsInFormation.class);
                intent2.putExtra("path","http://m.chayu.com/special/top2015?source=app");
                startActivity(intent2);
                break;
            case R.id.linear_4:
                Intent intent3 = new Intent(getActivity(),DetailsInFormation.class);
                intent3.putExtra("path","http://m.chayu.com/special/top2014?source=app");
                startActivity(intent3);
                break;
        }

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        Log.e(TAG, "onItemClick: 点击的条目"+i );

    }
}
