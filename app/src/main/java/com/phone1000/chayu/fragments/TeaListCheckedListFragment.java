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
import com.phone1000.chayu.adapters.TeaList1CheckedAdapter;
import com.phone1000.chayu.adapters.TeaList2CheckedAdapter;
import com.phone1000.chayu.modles.TeaListsSearchModel;
import com.phone1000.chayu.path.UtilPath;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;

/**
 * Created by Administrator on 2016/11/27 0027.
 */
public class TeaListCheckedListFragment extends Fragment implements TeaList1CheckedAdapter.OnListItemClickListener,TeaList2CheckedAdapter.OnList2ItemClickListener, AdapterView.OnItemClickListener {
    public static final String TAG = TeaListCheckedListFragment.class.getSimpleName();
    private View layout;
    private ListView mListView1;
    private ListView mListView2;
    private TeaList1CheckedAdapter adapter;
    private TeaList2CheckedAdapter adapter1;

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
        mListView2 = (ListView) layout.findViewById(R.id.fragment_tea_checked_list2);


        mListView1.setOnItemClickListener(this);
        mListView2.setOnItemClickListener(this);

        adapter = new TeaList1CheckedAdapter(getContext(),null);
        mListView1.setAdapter(adapter);


        adapter1 = new TeaList2CheckedAdapter(getContext(),null);
        mListView2.setAdapter(adapter1);


        setupListFromNet();
    }

    private void setupListFromNet() {
        RequestParams params = new RequestParams(UtilPath.TEASEARCHLISTPATH);
        params.addParameter("type","category");
        params.addParameter("imei","91f64b1a7dbe8b9e");
        params.addParameter("version","5");
        params.addParameter("agent","5");
        params.addParameter("source","3");
        params.addParameter("versionCode","2.2.4");
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e(TAG, "onSuccess: " );
                Gson gson = new Gson();
                TeaListsSearchModel model = gson.fromJson(result, TeaListsSearchModel.class);
                List<TeaListsSearchModel.DataBean.CategoryListBean> category_list = model.getData().getCategory_list();
                adapter.updateRes(category_list);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e(TAG, "onError: "+ex.getMessage() );
                Log.e(TAG, "onError: "+ex.getCause() );

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {
                Log.e(TAG, "onFinished: " );

            }
        });
    }

    @Override
    public void getItemPosition(List<TeaListsSearchModel.DataBean.CateChilde> list) {

        if (list != null) {
            adapter1.updateRes(list);
        }


    }

    @Override
    public void getItemBean(TeaListsSearchModel.DataBean.CateChilde childeBean) {

        if (childeBean != null) {

        }

    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (view.getId()) {
            case R.id.fragment_tea_checked_list1:

                break;

            case R.id.fragment_tea_checked_list2:

                break;

        }
    }
}
