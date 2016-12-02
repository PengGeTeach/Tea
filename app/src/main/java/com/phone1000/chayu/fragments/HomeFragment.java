package com.phone1000.chayu.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
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
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.phone1000.chayu.DetailsInFormation;
import com.phone1000.chayu.Interface.FragmentChangeInterface;
import com.phone1000.chayu.R;
import com.phone1000.chayu.activity.TeaListActivity;
import com.phone1000.chayu.adapters.HomePageQuanZiAdapter;
import com.phone1000.chayu.adapters.HomePageShiJiAdapter;
import com.phone1000.chayu.adapters.HomePageWenZhangAdapter;
import com.phone1000.chayu.adapters.TeaCommImageAdapter;
import com.phone1000.chayu.modles.ArticleBean;
import com.phone1000.chayu.modles.GroupBean;
import com.phone1000.chayu.modles.HomeDataBean;
import com.phone1000.chayu.modles.HomePageModel;
import com.phone1000.chayu.modles.ShiJIBean;
import com.phone1000.chayu.modles.SlideBean;
import com.phone1000.chayu.modles.TeaCateBean;
import com.phone1000.chayu.event.TeaListEvent;
import com.phone1000.chayu.path.UtilPath;

import org.greenrobot.eventbus.EventBus;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/27 0027.
 */
public class HomeFragment extends Fragment implements ViewPager.OnPageChangeListener, View.OnClickListener, PullToRefreshBase.OnRefreshListener {
    public static final String TAG = HomeFragment.class.getSimpleName();
    private View layout;
    private ViewPager mViewPager;
    private TeaCommImageAdapter mVPAdapter;

    private LinearLayout mPoint;
    private TextView mVPType;
    private TextView mVPTitle;
    private List<SlideBean> slide;
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
    private PullToRefreshScrollView mPulltoRefresh;
    private ScrollView mScroll;

    private boolean isHSCrollFirstLoading = true;
    private boolean isVPFirstLoading = true;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case 100:
                    mPulltoRefresh.onRefreshComplete();
                    break;
                case 0x100:
// 得到mVp当前页面的索引
                    int currentItem = mViewPager.getCurrentItem();
// 要显示的下一个页面的索引
                    currentItem++;
// 设置ViewPager显示的页面
                    mViewPager.setCurrentItem(currentItem % slide.size());
                    break;
            }


        }
    };
    private TextView mShijiMore;
    private TextView mQuanziMore;
    private TextView mWenZhangMore;

    private FragmentChangeInterface fragmentChangelistener;

    public void setFragmentChangelistener(FragmentChangeInterface fragmentChangelistener) {
        this.fragmentChangelistener = fragmentChangelistener;
    }

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


        mPulltoRefresh = (PullToRefreshScrollView) layout.findViewById(R.id.fragment_homepage_scroll);
        mPulltoRefresh.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
        mPulltoRefresh.setOnRefreshListener(this);
        mScroll = mPulltoRefresh.getRefreshableView();

        slide = new ArrayList<>();

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

        mSJRecycler.setFocusable(false);
        mQZRecycler.setFocusable(false);
        mWZRecycler.setFocusable(false);

        setUpViewFromNet();
        mShijiMore = (TextView) layout.findViewById(R.id.fragment_homepage_shiji_more);
        mQuanziMore = (TextView) layout.findViewById(R.id.fragment_homepage_quanzi_more);
        mWenZhangMore = (TextView) layout.findViewById(R.id.fragment_homepage_wenzhang_more);
        mShijiMore.setOnClickListener(this);
        mQuanziMore.setOnClickListener(this);
        mWenZhangMore.setOnClickListener(this);

    }

    //----------------------------------- 网络下载 -----------------------
    private void setUpViewFromNet() {
        RequestParams requestParams = new RequestParams(UtilPath.HomePagerUrl);
        requestParams.addParameter("imei", "91f64b1a7dbe8b9e");
        requestParams.addParameter("agent", "5");
        requestParams.addParameter("version", "5");
        requestParams.addParameter("source", "3");
        requestParams.addParameter("versionCode", "2.2.4");
        x.http().post(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e(TAG, "onSuccess: " + result);

                Gson gson = new Gson();


                HomePageModel homePageModel = gson.fromJson(result, HomePageModel.class);
                HomeDataBean data = homePageModel.getData();

                HomeFragment.this.slide.clear();

                List<SlideBean> slide = data.getSlide();
                HomeFragment.this.slide.addAll(slide);
                setUpViewPager();

                List<TeaCateBean> teaCate = data.getTeaCate();
                setUpChaping(teaCate);

                List<ShiJIBean> shiji = data.getShiji();
                mShijiadapter.updataRes(shiji);

                List<GroupBean> group = data.getGroup();
                mQZAdapter.updataRes(group);

                List<ArticleBean> article = data.getArticle();
                mWZAdapter.updataRes(article);
                mPulltoRefresh.onRefreshComplete();

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

    private void setUpChaping(List<TeaCateBean> teaCate) {

        if (isHSCrollFirstLoading) {


            for (int i = 0; i < teaCate.size(); i++) {
                LinearLayout linearLayout = new LinearLayout(getContext());
                linearLayout.setTag(teaCate.get(i));
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

                isHSCrollFirstLoading = false;
            }


        }


    }

    //-------------------水平滚动监听--------------
    private void setHScrollListener() {
        int count = mHscrollCon.getChildCount();

        for (int i = 0; i < count; i++) {
            LinearLayout childAt = (LinearLayout) mHscrollCon.getChildAt(i);
            childAt.setOnClickListener(this);
        }

    }


    //------------------------ ViewPager 的刷新 --------------------

    private void setUpViewPager() {
        List<ImageView> data = new ArrayList<>();
        if (isVPFirstLoading) {

            for (int i = 0; i < slide.size(); i++) {
                ImageView imageView = new ImageView(getContext());
                imageView.setTag(i);
                imageView.setOnClickListener(this);
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
                isVPFirstLoading = false;
            }
            mVPTitle.setText(slide.get(0).getTitle());
            mVPType.setText(slide.get(0).getTags());
            mPoint.getChildAt(0).setBackgroundResource(R.mipmap.dian_checked);

        } else if (!isVPFirstLoading) {
            for (int i = 0; i < slide.size(); i++) {
                ImageView imageView = new ImageView(getContext());
                imageView.setTag(i);
                imageView.setOnClickListener(this);
                x.image().bind(imageView, slide.get(i).getThumb());
                data.add(imageView);

            }

        }


        // 设置是否进行自动轮播
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
// try {
// Thread.sleep(3000);
// } catch (InterruptedException e) {
// e.printStackTrace();
// }
// SystemClock:系统时钟的睡眠方法,不会抛异常.
                    SystemClock.sleep(3000);
                    mHandler.sendEmptyMessage(0x100);
                }
            }
        }).start();


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


        Log.e(TAG, "onPageSelected: --------------------" + position);


    }

    @Override
    public void onPageScrollStateChanged(int state) {

        Log.e(TAG, "onPageScrollStateChanged: ----------------" + state);

    }

    @Override
    public void onClick(View v) {

        if (v instanceof LinearLayout) {
            LinearLayout linearLayout = (LinearLayout) v;
            TeaCateBean bean = (TeaCateBean) linearLayout.getTag();
            String bid = bean.getBid();
            Log.e(TAG, "onClick: " + bid);

            TeaListEvent tagEvent = new TeaListEvent(0x110);
            tagEvent.setBid(bid);
            tagEvent.setTeaName(bean.getName());
            EventBus.getDefault().postSticky(tagEvent);
            Intent intent = new Intent(getContext(), TeaListActivity.class);
            startActivity(intent);
        } else if (v instanceof ImageView) {
            ImageView image = (ImageView) v;
            int position = (int) image.getTag();
            String url = slide.get(position).getUrl();
            Log.e(TAG, "onClick: -----Image--------" + position);
            Intent intent = new Intent(getActivity(), DetailsInFormation.class);

            intent.putExtra("path", url);
            startActivity(intent);

        }else if (v instanceof TextView){
            switch (v.getId()) {
                case R.id.fragment_homepage_shiji_more:
                    fragmentChangelistener.shijiMoreClick();
                    break;
                case R.id.fragment_homepage_quanzi_more:
                    fragmentChangelistener.quanziMoreClick();
                    break;
                case R.id.fragment_homepage_wenzhang_more:
                    fragmentChangelistener.wenzhangMoreClick();
                    break;

            }
        }


    }

    @Override
    public void onRefresh(PullToRefreshBase refreshView) {

        setUpViewFromNet();
        mHandler.sendEmptyMessageDelayed(100, 1000);


    }
}
