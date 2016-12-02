package com.phone1000.chayu.fragments;

import android.graphics.Color;
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
public class TeaListCheckedListFragment extends Fragment implements AdapterView.OnItemClickListener {
    public static final String TAG = TeaListCheckedListFragment.class.getSimpleName();
    private View layout;
    private ListView mListView1;
    private ListView mListView2;
    private TeaList1CheckedAdapter adapter;
    private TeaList2CheckedAdapter adapter1;
    private List<TeaListsSearchModel.DataBean.CategoryListBean> category_list;
    private List<TeaListsSearchModel.DataBean.CateChilde> children;
    private int bid;
    private TeaChldeClickListenter itemclicklistener;
    private String parentName;

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
        mListView2 = (ListView) layout.findViewById(R.id.fragment_tea_checked_list2);


        mListView1.setOnItemClickListener(this);
        mListView2.setOnItemClickListener(this);

        adapter = new TeaList1CheckedAdapter(getContext(), null);
        mListView1.setAdapter(adapter);


        adapter1 = new TeaList2CheckedAdapter(getContext(), null);
        mListView2.setAdapter(adapter1);

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

                category_list = model.getData().getCategory_list();
                adapter.updateRes(category_list);
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
                if (category_list != null) {
                    TeaListsSearchModel.DataBean.CategoryListBean categoryListBean = category_list.get(position);
                    bid = categoryListBean.getBid();
                    parentName=categoryListBean.getName();
                    if (categoryListBean.getChildren() != null && categoryListBean.getChildren().size() != 0) {
                        children = categoryListBean.getChildren();
                        adapter1.updateRes(children);

                        view.setBackgroundColor(Color.GRAY);



                    } else {
                        TeaListEvent event = new TeaListEvent(0x110);
                        event.setBid(categoryListBean.getBid() + "");
                        EventBus.getDefault().post(event);
                        itemclicklistener.callMethod(categoryListBean.getBid() + "", null);
                        itemclicklistener.changeTeaType(parentName);

                    }


                }

                break;

            case R.id.fragment_tea_checked_list2:

                if (children != null) {

                    TeaListEvent event = new TeaListEvent(0x110);
                    event.setSid(children.get(position).getSid());
                    event.setBid(bid + "");
                    EventBus.getDefault().postSticky(event);
                    itemclicklistener.callMethod(bid + "", children.get(position).getSid());
                    itemclicklistener.changeTeaType(parentName);


                }


                break;

        }
    }


}
