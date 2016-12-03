package com.phone1000.chayu.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.phone1000.chayu.DetailsInFormation;
import com.phone1000.chayu.R;
import com.phone1000.chayu.adapters.TeaCommFragmentAdapter;
import com.phone1000.chayu.adapters.TeaCommImageAdapter;
import com.phone1000.chayu.event.Chapinevent;
import com.phone1000.chayu.modles.TeaComm;
import com.phone1000.chayu.path.UtilPath;
import com.phone1000.chayu.weidgt.CustomViewPager;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.xutils.DbManager;
import org.xutils.common.Callback;
import org.xutils.ex.DbException;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;


public class ChaPingFragment extends Fragment implements View.OnClickListener,PullToRefreshBase.OnRefreshListener2, ViewPager.OnPageChangeListener, RadioGroup.OnCheckedChangeListener {

    private PullToRefreshScrollView scrollView;
    private PullToRefreshScrollView mPulScrallView;
    private ViewPager mViewPagerTitle;
    private TextView mlldiquText;
    private TextView mllTexView;
    private LinearLayout mLl;
    private TeaCommImageAdapter teaCommImageAdapter;
    private TeaComm teaComm;
    private int proviceindex = 0;
    private int totlenum = 0;
    private List<TeaComm.DataBean.BannerBean> banner;

    public static final String TAG = ChaPingFragment.class.getSimpleName();
    private static final String GET_URL = "http://app.vmoiver.com/apiv3/post/getPostInCate?cateid=0&p=1";
    private View layout;
    private Fragment showFragment;
    private RadioGroup mRadioGroup;
    DbManager.DaoConfig daoConfig = new DbManager.DaoConfig()
            .setDbName("ChaYu.db")
            .setAllowTransaction(true)
            .setDbDir(Environment.getExternalStorageDirectory())
            .setDbVersion(1);

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        layout = inflater.inflate(R.layout.fragment_cha_ping, container, false);

        return layout;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initView();

        DbManager db = x.getDb(daoConfig);

        try {
            banner = db.selector(TeaComm.DataBean.BannerBean.class).findAll();
            List<TeaComm.DataBean.CategoryListBean> categoryListBeen = db.selector(TeaComm.DataBean.CategoryListBean.class).findAll();
            List<TeaComm.DataBean.CategoryListBean.Children> childrenList = db.selector(TeaComm.DataBean.CategoryListBean.Children.class).findAll();

            if((banner==null||banner.size()==0)||(categoryListBeen==null||categoryListBeen.size()==0)||(childrenList==null||childrenList.size()==0)){
                setupView();
            }else{
                for (int i = 0; i < categoryListBeen.size(); i++) {
                    List<TeaComm.DataBean.CategoryListBean.Children> list = new ArrayList<>();
                    int size = categoryListBeen.get(i).getSize();
                    Log.e(TAG, "onActivityCreated:长度 "+size);
                    if (size==0){
                        categoryListBeen.get(i).setChildren(null);
                        continue;
                    }else{

                        for(int j = totlenum;j<(totlenum+size);j++){
                            list.add(childrenList.get(j));
                            Log.e(TAG, "onActivityCreated: jjjj"+j );
                        }
                        Log.e(TAG, "onActivityCreated: 每次长度"+(totlenum+size) );
                        totlenum = totlenum+size;
                        categoryListBeen.get(i).setChildren(list);
                    }
                }
                Chapinevent chapinevent = new Chapinevent();
                chapinevent.setTeaComm(categoryListBeen);
                EventBus.getDefault().postSticky(chapinevent);
                setupViewPagerTitle();
                Log.e(TAG, "onActivityCreated: 发出信息");
            }

        } catch (DbException e) {
            e.printStackTrace();
        }

        //setupView();

    }

    private void setupView() {
        final RequestParams params = new RequestParams(UtilPath.TEA_COMMEN_VIEWPAGE_TITLE);
        params.addParameter("source","3");
        params.addParameter("version","5");
        params.addParameter("imei","4731955d1f681a93");
        params.addParameter("versionCode","2.2.4");
        params.addParameter("agent","5");

        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e(TAG, "onSuccess: " +result);

                mPulScrallView.onRefreshComplete();

                Gson gson = new Gson();
                teaComm = gson.fromJson(result, TeaComm.class);
                List<TeaComm.DataBean.CategoryListBean> category_list = teaComm.getData().getCategory_list();
                Chapinevent chapinevent = new Chapinevent();
                chapinevent.setTeaComm(category_list);
                EventBus.getDefault().post(chapinevent);
                banner = teaComm.getData().getBanner();
                setupViewPagerTitle();

                DbManager db = x.getDb(daoConfig);
                for(TeaComm.DataBean.CategoryListBean categoryListBean:category_list){
                    categoryListBean.setSize(categoryListBean.getChildren().size());
                    Log.e(TAG, "onSuccess:长度 "+ categoryListBean.getChildren().size());
                    try {
                        db.saveOrUpdate(category_list);
                    } catch (DbException e) {
                        e.printStackTrace();
                    }
                }

                for (TeaComm.DataBean.BannerBean b:teaComm.getData().getBanner()) {
                    try {
                        db.saveOrUpdate(b);
                    } catch (DbException e) {
                        e.printStackTrace();
                    }
                }

                for (int i = 0; i < teaComm.getData().getCategory_list().size(); i++) {
                    List<TeaComm.DataBean.CategoryListBean.Children> children = teaComm.getData().getCategory_list().get(i).getChildren();
                    for (TeaComm.DataBean.CategoryListBean.Children child:children) {
                        try {
                            db.saveOrUpdate(child);
                        } catch (DbException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e(TAG, "onError: " +ex.getMessage());
                Log.e(TAG, "onError: " +ex.getCause());
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

    private void setupViewPagerTitle() {



        List<ImageView> data = new ArrayList<ImageView>();

        for (int i = 0; i < banner.size(); i++) {
            ImageView imageView = new ImageView(getActivity());
            Log.e(TAG, "onSuccess: "+banner.get(i).getThumb() );
            data.add(imageView);
            //Picasso.with(getActivity()).load(banner.get(i).getThumb()).into(imageView);
            x.image().bind(imageView,banner.get(i).getThumb());
            View view = new View(getActivity());
            DisplayMetrics metrics = new DisplayMetrics();
            getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
            int width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,6,metrics);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(width,width);
            layoutParams.setMargins(10,0,0,0);
            view.setLayoutParams(layoutParams);
            view.setBackgroundResource(R.mipmap.dian_uncheck);
            imageView.setOnClickListener(this);

            imageView.setTag(banner.get(i).getUrl());

            mLl.addView(view);
        }

        mlldiquText.setText(banner.get(0).getTags());
        mllTexView.setText(banner.get(0).getTitle());
        mLl.getChildAt(0).setBackgroundResource(R.mipmap.dian_checked);
        teaCommImageAdapter.updataRes(data);

    }

    private void initView() {

        mPulScrallView = (PullToRefreshScrollView)layout.findViewById(R.id.tea_conment_scrollView);
        mPulScrallView.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
        mViewPagerTitle = (ViewPager)layout.findViewById(R.id.tea_conment_title_viewpage);
        mlldiquText = (TextView) layout.findViewById(R.id.tea_conment_fenlei);
        mllTexView = (TextView) layout.findViewById(R.id.tea_conment_text);
        mLl = (LinearLayout) layout.findViewById(R.id.ll_dot);
        mRadioGroup = ((RadioGroup) layout.findViewById(R.id.rg_group));

        mRadioGroup.setOnCheckedChangeListener(this);

        teaCommImageAdapter = new TeaCommImageAdapter(null);
        mViewPagerTitle.setAdapter(teaCommImageAdapter);

        mViewPagerTitle.setOnPageChangeListener(this);


        mPulScrallView.setOnRefreshListener(this);


        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        showFragment = new PingPaiFragment();
        transaction.add(R.id.frame,showFragment,PingPaiFragment.TAG);
        Log.e(TAG, "initView:添加fragment " );
        transaction.commit();

    }





    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        mlldiquText.setText(banner.get(position).getTags());
        mllTexView.setText(banner.get(position).getTitle());
        mLl.getChildAt(proviceindex).setBackgroundResource(R.mipmap.dian_uncheck);
        mLl.getChildAt(position).setBackgroundResource(R.mipmap.dian_checked);
        proviceindex = position;

    }

    @Override
    public void onPageSelected(int position) {

    }

        @Override
    public void onPageScrollStateChanged(int state) {

    }


    @Override
    public void onClick(View view) {
        if (view instanceof ImageView) {
            String tag = (String) view.getTag();
            Log.e(TAG, "onClick: "+tag );

            Intent intent = new Intent(getActivity(), DetailsInFormation.class);

            intent.putExtra("path",tag);
            startActivity(intent);
        }
    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {

        DbManager db = x.getDb(daoConfig);
        try {
            db.delete(TeaComm.DataBean.CategoryListBean.Children.class);
            db.delete(TeaComm.DataBean.CategoryListBean.class);
            db.delete(TeaComm.DataBean.BannerBean.class);
        } catch (DbException e) {
            e.printStackTrace();
        }

        setupView();

    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {

    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {

        switch (i){

            case R.id.rb_1:
                switchpage(PingPaiFragment.TAG,PingPaiFragment.class);
                break;
            case R.id.rb_2:
                switchpage(ChaLeiFragment.TAG,ChaLeiFragment.class);
                break;
            case R.id.rb_3:
                switchpage(BangDanFragment.TAG,BangDanFragment.class);
                break;

        }

    }

    private void switchpage(String tag, Class<? extends  Fragment> cls) {

        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.hide(showFragment);
        showFragment = (Fragment) manager.findFragmentByTag(tag);
        if (showFragment!=null){
            transaction.show(showFragment);
        }else{

            try {
                showFragment = cls.getConstructor().newInstance();
                transaction.add(R.id.frame,showFragment,tag);
            } catch (java.lang.InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }

        }
        transaction.commit();

    }
}
