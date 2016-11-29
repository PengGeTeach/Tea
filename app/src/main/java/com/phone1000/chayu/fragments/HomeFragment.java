package com.phone1000.chayu.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.phone1000.chayu.R;
import com.phone1000.chayu.activity.TeaListActivity;
import com.phone1000.chayu.adapters.HomePageQuanZiAdapter;
import com.phone1000.chayu.adapters.HomePageShiJiAdapter;
import com.phone1000.chayu.adapters.HomePageWenZhangAdapter;
import com.phone1000.chayu.adapters.TeaCommImageAdapter;
import com.phone1000.chayu.modles.HomePageModel;
import com.phone1000.chayu.modles.TagEvent;
import com.phone1000.chayu.modles.TeaListEvent;

import org.greenrobot.eventbus.EventBus;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/27 0027.
 */
public class HomeFragment extends Fragment implements ViewPager.OnPageChangeListener, View.OnClickListener {
    public static final String TAG = ShiJiFragment.class.getSimpleName();
    private View layout;
    private ViewPager mViewPager;
    private TeaCommImageAdapter mVPAdapter;

    private final String HomePagerUrl = "http://app.chayu.com/chayu/2.0/index";
    private LinearLayout mPoint;
    private TextView mVPType;
    private TextView mVPTitle;
    private List<HomePageModel.DataBean.SlideBean> slide;
    private int proviceindex = 0;
    private HorizontalScrollView mHScroll;
    private LinearLayout mHscrollCon;

    // 屏幕宽度(以像素做为单位)
    private int screenWidth;
    // 是一个用于存放屏幕参数信息的结构体对象
    private DisplayMetrics metrics;
    private RecyclerView mSJRecycler;
    private HomePageShiJiAdapter mShijiadapter;
    private RecyclerView mQZRecycler;
    private HomePageQuanZiAdapter mQZAdapter;
    private RecyclerView mWZRecycler;
    private HomePageWenZhangAdapter mWZAdapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        layout = inflater.inflate(R.layout.fragment_homepage, container, false);
        return layout;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initView();

    }

    private void initView() {


        metrics = new DisplayMetrics();
// 通过屏幕管理者调用默认显示对象 ，把屏幕的参数信息存放到metrics对象中。屏幕状态，大小，密度等
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
// 获取了屏幕的实际像素
        screenWidth = metrics.widthPixels;

        mViewPager = (ViewPager) layout.findViewById(R.id.fragment_homepage_viewpager);
        mVPAdapter = new TeaCommImageAdapter(null);
        mViewPager.setAdapter(mVPAdapter);
        mViewPager.addOnPageChangeListener(this);

        mPoint = (LinearLayout) layout.findViewById(R.id.fragment_homepage_linear);
        mVPType = (TextView) layout.findViewById(R.id.fragment_homepage_linear_type);
        mVPTitle = (TextView) layout.findViewById(R.id.fragment_homepage_linear_title);

        mHScroll = (HorizontalScrollView) layout.findViewById(R.id.fragment_homepage_chaping_hscroll);
        mHscrollCon = (LinearLayout) layout.findViewById(R.id.fragment_homepage_chaping_hscroll_linear);

        mSJRecycler = (RecyclerView) layout.findViewById(R.id.fragment_homepage_shiji_recycler);
        mSJRecycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        mShijiadapter = new HomePageShiJiAdapter(getActivity(), null);
        mSJRecycler.setAdapter(mShijiadapter);

        mQZRecycler = (RecyclerView) layout.findViewById(R.id.fragment_homepage_quanzi_recycler);
        mQZRecycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        mQZAdapter = new HomePageQuanZiAdapter(getContext(), null);
        mQZRecycler.setAdapter(mQZAdapter);

        mWZRecycler = (RecyclerView) layout.findViewById(R.id.fragment_homepage_wenzhang_recycler);
        mWZRecycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        mWZAdapter = new HomePageWenZhangAdapter(getContext(), null);
        mWZRecycler.setAdapter(mWZAdapter);


        setUpViewFromNet();

    }

    //----------------------------------- 网络下载 -----------------------
    private void setUpViewFromNet() {
        RequestParams requestParams = new RequestParams(HomePagerUrl);
        x.http().post(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e(TAG, "onSuccess: " + result);

                Gson gson = new Gson();

                HomePageModel homePageModel = gson.fromJson(result, HomePageModel.class);
                HomePageModel.DataBean data = homePageModel.getData();

                List<HomePageModel.DataBean.SlideBean> slide = data.getSlide();
                setUpViewPager(slide);

                List<HomePageModel.DataBean.TeaCateBean> teaCate = data.getTeaCate();
                setUpChaping(teaCate);

                List<HomePageModel.DataBean.ShijiBean> shiji = data.getShiji();
                mShijiadapter.updataRes(shiji);

                List<HomePageModel.DataBean.GroupBean> group = data.getGroup();
                mQZAdapter.updataRes(group);

                List<HomePageModel.DataBean.ArticleBean> article = data.getArticle();
                mWZAdapter.updataRes(article);


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

    //----------------------- 茶评 刷新  水平滚动条-------------------------------

    private void setUpChaping(List<HomePageModel.DataBean.TeaCateBean> teaCate) {


        for (int i = 0; i < teaCate.size(); i++) {
            LinearLayout linearLayout = new LinearLayout(getContext());
            linearLayout.setTag(i);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(screenWidth / 8, ViewPager.LayoutParams.MATCH_PARENT);
            params.setMargins(30, 0, 15, 0);
            linearLayout.setLayoutParams(params);

            linearLayout.setGravity(Gravity.CENTER);
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            ImageView imageView = new ImageView(getContext());

            x.image().bind(imageView, teaCate.get(i).getIco());

            TextView textView = new TextView(getContext());
            textView.setText(teaCate.get(i).getName());
            textView.setGravity(Gravity.CENTER);

            linearLayout.addView(imageView);
            linearLayout.addView(textView);
            mHscrollCon.addView(linearLayout);

            setHScrollListener();

        }


    }

    //-------------------水平滚动监听--------------
    private void setHScrollListener() {
        int count=mHscrollCon.getChildCount();

        for (int i = 0; i < count; i++) {
            LinearLayout childAt = (LinearLayout) mHscrollCon.getChildAt(i);
            childAt.setOnClickListener(this);
        }

    }


    //------------------------ ViewPager 的刷新 --------------------

    private void setUpViewPager(List<HomePageModel.DataBean.SlideBean> slide) {
        this.slide = slide;

        List<ImageView> data = new ArrayList<>();
        for (int i = 0; i < slide.size(); i++) {
            ImageView imageView = new ImageView(getContext());
            x.image().bind(imageView, slide.get(i).getThumb());
            data.add(imageView);
            View view = new View(getContext());
            DisplayMetrics metrics = new DisplayMetrics();
            getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
            int width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 6, metrics);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(width, width);
            layoutParams.setMargins(10, 0, 0, 0);
            view.setLayoutParams(layoutParams);
            view.setBackgroundResource(R.mipmap.dian_uncheck);

            mPoint.addView(view);
        }
        mVPTitle.setText(slide.get(0).getTitle());
        mVPType.setText(slide.get(0).getTags());
        mPoint.getChildAt(0).setBackgroundResource(R.mipmap.dian_checked);
        mVPAdapter.updataRes(data);


    }


    //---------------------------ViewPager 监听
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        mVPType.setText(slide.get(position).getTags());
        mVPTitle.setText(slide.get(position).getTitle());
        mPoint.getChildAt(proviceindex).setBackgroundResource(R.mipmap.dian_uncheck);
        mPoint.getChildAt(position).setBackgroundResource(R.mipmap.dian_checked);
        proviceindex = position;

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onClick(View v) {

        if (v instanceof LinearLayout) {
            LinearLayout linearLayout = (LinearLayout) v;
            int tag = (int) linearLayout.getTag()+1;
            Log.e(TAG, "onClick: "+tag );

            TeaListEvent tagEvent = new TeaListEvent(0x100);
            tagEvent.setSid(tag+"");
            tagEvent.setBid(null);
            EventBus.getDefault().postSticky(tagEvent);
            Intent intent = new Intent(getContext(), TeaListActivity.class);
            startActivity(intent);

        }


    }
}
