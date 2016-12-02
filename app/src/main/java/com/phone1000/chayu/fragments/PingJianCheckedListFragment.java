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

import com.google.gson.Gson;
import com.phone1000.chayu.R;
import com.phone1000.chayu.adapters.TeaListPingJianCheckedAdapter;
import com.phone1000.chayu.Interface.TeaChldeClickListenter;
import com.phone1000.chayu.event.TeaListEvent;
import com.phone1000.chayu.modles.TeaListsSearchModel;
import com.phone1000.chayu.path.UtilPath;

import org.greenrobot.eventbus.EventBus;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;

/**
 * Created by Administrator on 2016/11/27 0027.
 */
public class PingJianCheckedListFragment extends Fragment implements AdapterView.OnItemClickListener {
    public static final String TAG = PingJianCheckedListFragment.class.getSimpleName();
    private View layout;
    private ListView mListView1;
    private List<TeaListsSearchModel.DataBean.CateChilde> children;
    private int bid;
    private TeaChldeClickListenter itemclicklistener;
    private List<TeaListsSearchModel.DataBean.PingjiannianfenBean> pingjiannianfen;
    private TeaListPingJianCheckedAdapter adapter;

    public void setItemclicklistener(TeaChldeClickListenter itemclicklistener) {
        this.itemclicklistener = itemclicklistener;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        layout = inflater.inflate(R.layout.fragment_tea_checked_list, container, false);
        return layout;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    private void initView() {
        mListView1 = (ListView) layout.findViewById(R.id.fragment_tea_checked_list1);


        mListView1.setOnItemClickListener(this);


        adapter = new TeaListPingJianCheckedAdapter(getContext(),null);
        mListView1.setAdapter(adapter);


        setupListFromNet();


    }

    private void setupListFromNet() {
        RequestParams params = new RequestParams(UtilPath.TEASEARCHLISTPATH);
        params.addParameter("type", "category");
        params.addParameter("imei", "91f64b1a7dbe8b9e");
        params.addParameter("version", "5");
        params.addParameter("agent", "5");
        params.addParameter("source", "3");
        params.addParameter("versionCode", "2.2.4");
        x.http().post(params, new Callback.CommonCallback<String>() {




            @Override
            public void onSuccess(String result) {
                Log.e(TAG, "onSuccess: ");
                Gson gson = new Gson();
                TeaListsSearchModel model = gson.fromJson(result, TeaListsSearchModel.class);

                pingjiannianfen = model.getData().getPingjiannianfen();
                adapter.updateRes(pingjiannianfen);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e(TAG, "onError: " + ex.getMessage());
                Log.e(TAG, "onError: " + ex.getCause());

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {
                Log.e(TAG, "onFinished: ");

            }
        });
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Log.e(TAG, "onItemClick: 最外层" + position);

        switch (parent.getId()) {
            case R.id.fragment_tea_checked_list1:

                Log.e(TAG, "onItemClick: " + position);
                if (pingjiannianfen != null) {


                    TeaListEvent event = new TeaListEvent(0x110);
                    event.setReview_year(pingjiannianfen.get(position).getId()+"");
                    EventBus.getDefault().postSticky(event);
                    itemclicklistener.callMethod("1", null);
                    itemclicklistener.changePingjiantext(pingjiannianfen.get(position).getName());

                }

                break;


        }
    }


}
