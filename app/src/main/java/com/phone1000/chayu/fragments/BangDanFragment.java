package com.phone1000.chayu.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.phone1000.chayu.R;
import com.phone1000.chayu.adapters.BangDanAdapter;
import com.phone1000.chayu.modles.BangDanModle;
import com.phone1000.chayu.path.UtilPath;
import com.phone1000.chayu.utils.ListViewUtils;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by Administrator on 2016/11/29 0029.
 */
public class BangDanFragment extends Fragment{

    private static final String TAG = BangDanFragment.class.getSimpleName();
    private View layout;
    private LinearLayout mLinearLayout;
    private ListView m_listView;

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

        BangDanAdapter bangDanAdapter = new BangDanAdapter(getActivity(), bangDanModle.getData().getTopList(),R.layout.bandgan_listview_item);
        m_listView.setAdapter(bangDanAdapter);

        ListViewUtils.setListViewHeightBasedOnChildren(m_listView);
    }

    private void setupWithBnagdan(BangDanModle bangDanModle) {

        for (int i = 0; i < bangDanModle.getData().getTopArr().size(); i++) {
            LinearLayout childLinear = (LinearLayout) mLinearLayout.getChildAt(i);
            Log.e(TAG, "setupWithBnagdan: 获取子类控件个数"+childLinear.getChildCount() );
            ImageView imageView = (ImageView) childLinear.getChildAt(0);
            x.image().bind(imageView,bangDanModle.getData().getTopArr().get(i).getThumb());
            TextView textView = (TextView) childLinear.getChildAt(1);
            textView.setText(bangDanModle.getData().getTopArr().get(i).getTitle());
            TextView textView1 = (TextView) childLinear.getChildAt(2);
            textView1.setText(bangDanModle.getData().getTopArr().get(i).getTitles());
        }
        
    }

    private void initView() {
        mLinearLayout = ((LinearLayout) layout.findViewById(R.id.bangdan_ll));

        m_listView = ((ListView) layout.findViewById(R.id.bangdan_lv));
    }
}
