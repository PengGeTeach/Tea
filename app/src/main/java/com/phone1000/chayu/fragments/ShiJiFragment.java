package com.phone1000.chayu.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.phone1000.chayu.R;
import com.phone1000.chayu.adapters.ShijifragmentListAdapter;
import com.phone1000.chayu.adapters.TeaCommImageAdapter;
import com.phone1000.chayu.modles.ShiJiModel;
import com.phone1000.chayu.path.UtilPath;
import com.phone1000.chayu.utils.ListViewUtils;
import com.phone1000.chayu.weidgt.MyListView;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/27 0027.
 */
public class ShiJiFragment extends Fragment implements PullToRefreshBase.OnRefreshListener, View.OnClickListener, ViewPager.OnPageChangeListener {
    public static final String TAG = ShiJiFragment.class.getSimpleName();
    private View layout;
    private PullToRefreshScrollView mPullToRefresh;
    private TextView mDashi;
    private TextView mMingjia;
    private TextView mMingxing;
    private TextView mHeji;
    private TextView mFaxian;
    private ListView mListView;
    private ViewPager mViwPager;
    private LinearLayout mPoint;
    private boolean isVPFirstLoading = true;

    private List<ShiJiModel.DataBean.SlideArrBean> slide;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case 0x110:
                    mPullToRefresh.onRefreshComplete();
                    break;
            }
        }
    };
    private ImageView mBottomImage;
    private TeaCommImageAdapter mViewPagerAdapter;
    private int proviceindex=0;
    private ShijifragmentListAdapter adapter;
    private ImageView mTopImage;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        layout = inflater.inflate(R.layout.fragment_shi_ji, container, false);
        return layout;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initViwe();
    }

    private void initViwe() {

        slide = new ArrayList<>();



        mPullToRefresh = (PullToRefreshScrollView) layout.findViewById(R.id.fragment_shiji_scroll);
        mPullToRefresh.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
        mPullToRefresh.setOnRefreshListener(this);

        mDashi = (TextView) layout.findViewById(R.id.fragment_shiji_dashi);
        mMingjia = (TextView) layout.findViewById(R.id.fragment_shiji_mingjia);
        mMingxing = (TextView) layout.findViewById(R.id.fragment_shiji_mingxing);
        mHeji = (TextView) layout.findViewById(R.id.fragment_shiji_heji);
        mFaxian = (TextView) layout.findViewById(R.id.fragment_shiji_faxian);
        mListView = (ListView) layout.findViewById(R.id.fragment_shiji_listview);
        adapter = new ShijifragmentListAdapter(getContext(),null);
        mListView.setAdapter(adapter);

        mViwPager = (ViewPager) layout.findViewById(R.id.fragment_shiji_viewPager);
        mViewPagerAdapter = new TeaCommImageAdapter(null);
        mViwPager.setAdapter(mViewPagerAdapter);
        mViwPager.addOnPageChangeListener(this);

        mPoint = (LinearLayout) layout.findViewById(R.id.fragment_shiji_point);
        mBottomImage = (ImageView) layout.findViewById(R.id.fragment_shiji_bottemimage);
        mTopImage = (ImageView) layout.findViewById(R.id.fragment_shiji_topimage);

        getDataFromNet();

    }

    @Override
    public void onRefresh(PullToRefreshBase refreshView) {

        getDataFromNet();
        mHandler.sendEmptyMessageDelayed(0x110,1000);

    }

    private void getDataFromNet() {
        final RequestParams params = new RequestParams(UtilPath.SHIJILISTPATH);
        params.addParameter("imei", "91f64b1a7dbe8b9e");
        params.addParameter("agent", "5");
        params.addParameter("version", "5");
        params.addParameter("source", "3");
        params.addParameter("sessionid", "23mtg2d4s8u3jklmpjirib41a5");
        params.addParameter("versionCode", "2.2.4");
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e(TAG, "onSuccess: ");

                Gson gson = new Gson();
                ShiJiModel shiJiModel = gson.fromJson(result, ShiJiModel.class);
                ShiJiModel.DataBean data = shiJiModel.getData();
                x.image().bind(mTopImage, data.getTouPhotosArr().getThumb());
                String thumb = data.getFamousArr().get(0).getThumb();
                x.image().bind(mBottomImage,thumb);

                List<ShiJiModel.DataBean.SlideArrBean> slideArr = data.getSlideArr();
                ShiJiFragment.this.slide=slideArr;
                setUpViewPager();

                List<ShiJiModel.DataBean.MasterArrBean> masterArr = data.getMasterArr();

                for (int i = 0; i < masterArr.size(); i++) {
                    ShiJiModel.DataBean.MasterArrBean bean = masterArr.get(i);
                    switch (bean.getType()) {
                        case 3:
                            bean.setmDinyiType(0);
                            break;
                        case 4:
                            bean.setmDinyiType(1);
                            break;
                        case 5:
                            bean.setmDinyiType(2);
                            break;
                        case 6:
                            bean.setmDinyiType(1);
                            break;
                    }
                }
                adapter.updateRes(masterArr);
                ListViewUtils.setListViewHeightBasedOnChildren(mListView);


            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e(TAG, "onError: ");
            }

            @Override
            public void onCancelled(CancelledException cex) {
                Log.e(TAG, "onCancelled: ");
            }

            @Override
            public void onFinished() {
                Log.e(TAG, "onFinished: ");
            }
        });
    }

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


        mViewPagerAdapter.updataRes(data);


    }


    @Override
    public void onClick(View v) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

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
}
