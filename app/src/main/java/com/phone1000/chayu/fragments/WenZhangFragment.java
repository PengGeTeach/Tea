package com.phone1000.chayu.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.phone1000.chayu.DetailsInFormation;
import com.phone1000.chayu.R;
import com.phone1000.chayu.WenzhangZhuangTi;
import com.phone1000.chayu.WenzhangZuiXinReMen;
import com.phone1000.chayu.adapters.TeaCommImageAdapter;
import com.phone1000.chayu.adapters.WenzhangAdapter;
import com.phone1000.chayu.modles.ListBean;
import com.phone1000.chayu.modles.WenZhangModle;
import com.phone1000.chayu.modles.WenZhangShangLaJiaZai;
import com.phone1000.chayu.path.UtilPath;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/27 0027.
 */
public class WenZhangFragment extends Fragment implements View.OnClickListener, ViewPager.OnPageChangeListener,PullToRefreshBase.OnRefreshListener2, AdapterView.OnItemClickListener {
    public static final String TAG = WenZhangFragment.class.getSimpleName();
    private View layout;
    private PullToRefreshListView mRefreshListView;
    private View mHeaderView;
    private WenZhangModle wenZhangModle;
    private ViewPager mHeaderViewPageer;
    private TextView mHeaderFenlei;
    private TextView mHeaderText;
    private LinearLayout mLinear;
    private TeaCommImageAdapter wenzhangViewAdapter;
    private ListView mListView;
    private int privex = 0;
    private WenzhangAdapter adapter;
    private int p = 1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        layout = inflater.inflate(R.layout.wenzhangfragment, container, false);
        return layout;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initView();
        setupView();
    }

    private void initView() {

        mRefreshListView = (PullToRefreshListView) layout.findViewById(R.id.wenzhang_listview);

        mHeaderView = LayoutInflater.from(getActivity()).inflate(R.layout.wenzhang_header,null);

        mHeaderViewPageer = ((ViewPager) mHeaderView.findViewById(R.id.wenzhang_title_viewpage));
        mHeaderFenlei = ((TextView) mHeaderView.findViewById(R.id.wenzhang_fenlei));
        mHeaderText = ((TextView) mHeaderView.findViewById(R.id.wenzhang_text));
        mLinear = ((LinearLayout) mHeaderView.findViewById(R.id.ll_dot));
        mHeaderViewPageer.setOnPageChangeListener(this);


        LinearLayout ll1 = (LinearLayout) mHeaderView.findViewById(R.id.ll1);
        LinearLayout ll2 = (LinearLayout) mHeaderView.findViewById(R.id.ll2);
        LinearLayout ll3 = (LinearLayout) mHeaderView.findViewById(R.id.ll3);

        ll1.setOnClickListener(this);
        ll2.setOnClickListener(this);
        ll3.setOnClickListener(this);



        mListView = mRefreshListView.getRefreshableView();

        mRefreshListView.setOnRefreshListener(this);
        mRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);

        mListView.setOnItemClickListener(this);

        mListView.addHeaderView(mHeaderView);

        wenzhangViewAdapter = new TeaCommImageAdapter(null);
        mHeaderViewPageer.setAdapter(wenzhangViewAdapter);

        adapter = new WenzhangAdapter(getActivity(),null, R.layout.wenzhang_item1,R.layout.wenzhang_item2,R.layout.wanzhang_item3);
        mListView.setAdapter(adapter);

    }

    private void setupView() {

        RequestParams params = new RequestParams(UtilPath.WENZHANG);
        params.addParameter("source","3");
        params.addParameter("version","5");
        params.addParameter("imei","4731955d1f681a93");
        params.addParameter("versionCode","2.2.4");
        params.addParameter("agent","5");
        x.http().post(params, new Callback.CommonCallback<String>() {

            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                wenZhangModle = gson.fromJson(result, WenZhangModle.class);
                mRefreshListView.onRefreshComplete();
                setViewHeader();
                setupListView(wenZhangModle);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e(TAG, "onError: "+ex.getCause());
                Log.e(TAG, "onError: "+ex.getMessage());
            }

            @Override
            public void onCancelled(CancelledException cex) {
                Log.e(TAG, "onCancelled: ");
            }

            @Override
            public void onFinished() {
                Log.e(TAG, "onFinished: " );
            }
        });


    }

    private void setupListView(WenZhangModle wenZhangModle) {

        List<ListBean> list = wenZhangModle.getData().getList();

        adapter.updateRes(list);

    }

    private void setViewHeader() {
        List<ImageView> data = new ArrayList<>();

        for (int i = 0; i <wenZhangModle.getData().getBanner().size() ; i++) {
            ImageView imageView = new ImageView(getActivity());
            imageView.setTag(wenZhangModle.getData().getBanner().get(i).getUrl());
            x.image().bind(imageView,wenZhangModle.getData().getBanner().get(i).getThumb());
            data.add(imageView);
            View view = new View(getActivity());
            DisplayMetrics metrics = new DisplayMetrics();
            getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
            int width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,6,metrics);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(width,width);
            layoutParams.setMargins(10,0,0,0);
            view.setLayoutParams(layoutParams);
            view.setBackgroundResource(R.mipmap.dian_uncheck);
            mLinear.addView(view);
            imageView.setOnClickListener(this);
            //imageView.setTag(wenZhangModle.getData().getBanner().get(i).getThumb());
        }

        mHeaderFenlei.setText(wenZhangModle.getData().getBanner().get(0).getTags());
        mHeaderText.setText(wenZhangModle.getData().getBanner().get(0).getTitle());
        mLinear.getChildAt(0).setBackgroundResource(R.mipmap.dian_checked);
        wenzhangViewAdapter.updataRes(data);
    }


    @Override
    public void onClick(View view) {

        if (view instanceof ImageView){
            Intent intent = new Intent(getActivity(), DetailsInFormation.class);
            String path = (String) view.getTag();
            Log.e(TAG, "onClick: "+path );
            intent.putExtra("path",path);
            startActivity(intent);
        }else if (view instanceof LinearLayout){

            switch (view.getId()) {

                case R.id.ll1:
                    Intent intent = new Intent(getActivity(),WenzhangZuiXinReMen.class);
                    intent.putExtra("order","created");
                    startActivity(intent);
                    break;
                case R.id.ll2:
                    Intent intent1 = new Intent(getActivity(),WenzhangZuiXinReMen.class);
                    intent1.putExtra("order","clicks");
                    startActivity(intent1);
                    break;
                case R.id.ll3:
                    Intent intent2 = new Intent(getActivity(), WenzhangZhuangTi.class);
                    startActivity(intent2);
                    break;
            }
        }


    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        Log.e(TAG, "onPageScrolled: "+position );

        mHeaderFenlei.setText(wenZhangModle.getData().getBanner().get(position).getTags());
        mHeaderText.setText(wenZhangModle.getData().getBanner().get(position).getTitle());
        mLinear.getChildAt(privex).setBackgroundResource(R.mipmap.dian_uncheck);
        mLinear.getChildAt(position).setBackgroundResource(R.mipmap.dian_checked);
        privex = position;

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {

        getDataFromNet(State.DOWN);

    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {
        getDataFromNet(State.UP);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(getActivity(),DetailsInFormation.class);
        Log.e(TAG, "onItemClick: 点击位置"+i );
        ListBean itemAtPosition = (ListBean) adapterView.getItemAtPosition(i);
        if (i==2){
            intent.putExtra("path", itemAtPosition.getSource().getUrl());
        }else{
            intent.putExtra("path","http://m.chayu.com/article/"+ itemAtPosition.getId());
        }
        startActivity(intent);
    }

    enum State{

        DOWN,UP;

    }

    private void getDataFromNet(State state) {

            switch (state){
                case DOWN:

                    setupView();

                    break;

                case UP:
                    p++;
                    setupUPView();

                    break;

            }

    }

    private void setupUPView() {


        RequestParams params = new RequestParams(UtilPath.WENZHANG_UP);
        params.addParameter("pageNo",p);
        params.addParameter("source",3);
        params.addParameter("version","5");
        params.addParameter("imei","4731955d1f681a93");
        params.addParameter("versionCode","2.2.4");
        params.addParameter("pageSize","10");
        params.addParameter("agent","5");
        x.http().post(params, new Callback.CommonCallback<String>() {


            @Override
            public void onSuccess(String result) {
                Log.e(TAG, "onSuccess:在这里 "+result );
                Gson gson = new Gson();
                WenZhangShangLaJiaZai wenZhangShangLaJiaZai = gson.fromJson(result, WenZhangShangLaJiaZai.class);

                List<ListBean> data = wenZhangShangLaJiaZai.getData();

                for (int i = 0; i < data.size(); i++) {
                   data.get(i).setShow_type(1);
                }
                adapter.addRes(data);
                mRefreshListView.onRefreshComplete();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e(TAG, "onError: "+ex.getCause());
                Log.e(TAG, "onError: "+ex.getMessage());
            }

            @Override
            public void onCancelled(CancelledException cex) {
                Log.e(TAG, "onCancelled: ");
            }

            @Override
            public void onFinished() {
                Log.e(TAG, "onFinished: " );
            }
        });

    }


}
