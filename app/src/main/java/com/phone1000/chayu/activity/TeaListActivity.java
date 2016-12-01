package com.phone1000.chayu.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import com.phone1000.chayu.R;
import com.phone1000.chayu.fragments.PingFenCheckedListFragment;
import com.phone1000.chayu.fragments.PingJianCheckedListFragment;
import com.phone1000.chayu.fragments.TeaListCheckedListFragment;
import com.phone1000.chayu.fragments.TeaListConListFragment;
import com.phone1000.chayu.modles.TeaCateBean;
import com.phone1000.chayu.modles.TeaChldeClickListenter;
import com.phone1000.chayu.modles.TeaListEvent;
import com.phone1000.chayu.weidgt.TopBar;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.reflect.InvocationTargetException;

public class TeaListActivity extends AppCompatActivity implements TeaChldeClickListenter, View.OnClickListener {

    private static final String TAG = TeaListActivity.class.getSimpleName();
    private TopBar mTopBar;
    private FrameLayout mListViewCon;
    private Fragment showfragment;
    private CheckBox mTeaType;
    private CheckBox mPingfen;
    private CheckBox mPingJian;
    private CheckBox mChaYang;
    private String sid=null;
    private String bid=null;
    private String order=null;
    private String review_year=null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tea_list);
        initView();
    }

    private void initView() {
        mTopBar = (TopBar) findViewById(R.id.tealist_activity_top_bar);
        mTopBar.logoVisible(false);


        mTeaType = (CheckBox) findViewById(R.id.tea_list_tea);
        mTeaType.setOnClickListener(this);

        mPingfen = (CheckBox) findViewById(R.id.tea_list_pingfen);
        mPingfen.setOnClickListener(this);

        mPingJian = (CheckBox) findViewById(R.id.tea_list_pinjian);
        mPingJian.setOnClickListener(this);

        mChaYang = (CheckBox) findViewById(R.id.tea_list_chayang);
        mChaYang.setOnClickListener(this);


        mListViewCon = (FrameLayout) findViewById(R.id.tea_list_listview_container);


        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        showfragment = new TeaListConListFragment();
        transaction.add(R.id.tea_list_listview_container, showfragment, TeaListConListFragment.TAG);
        transaction.commit();


    }


    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onEvent(TeaListEvent event) {
//        Log.e(TAG, "onEvent: "+event.getTag() );
        if (event.getWHAT() == 0x110) {

            if (event.getTeaName()!=null) {
                String teaName = event.getTeaName();
                mTeaType.setText(teaName);
            }

            if (event.getSid() != null) {
                sid = event.getSid();
            }

            if (event.getBid() != null) {
                bid = event.getBid();
            }


            if (event.getOrder() != null) {

                order = event.getOrder();
                Log.e("CanShu", "onEvent: Order--------------"+event.getOrder() );
            }

            if (event.getReview_year() != null) {
                review_year = event.getReview_year();
            }

            Log.e(TAG, "onEvent: tag ---------------->" + sid);

            TeaListEvent event1 = new TeaListEvent(0x10086);
            event1.setReview_year(review_year);
            event1.setOrder(order);
            event1.setBid(bid);
            event1.setSid(sid);
            Log.e("CanShu", "TealistActivity  bid:------------------->"+bid+"+sid:------------------"+sid+"order:------------"+order+"review_year:---------"+review_year );
            EventBus.getDefault().postSticky(event1);

        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }


    public void switchpage(String tag, Class<? extends Fragment> cls) {

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.hide(showfragment);
        showfragment = (Fragment) manager.findFragmentByTag(tag);
        if (showfragment != null) {
            transaction.show(showfragment);
        } else {
            try {
                showfragment = cls.getConstructor().newInstance();
                transaction.add(R.id.tea_list_listview_container, showfragment, tag);
            } catch (InstantiationException e) {
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




    @Override
    public void callMethod(String bid, String sid) {

        Log.e(TAG, "callMethod: bid ------------sid-------------" + bid + "-------------" + sid);

        switchpage(TeaListConListFragment.TAG, TeaListConListFragment.class);

        initChexBox();


    }

    @Override
    public void changeTeaType(String teaTypeName) {
        if (teaTypeName != null) {
            mTeaType.setText(teaTypeName);
        }
    }

    @Override
    public void changePingFentext(String pingfenName) {
        if (pingfenName != null) {
            mPingfen.setText(pingfenName);
            mPingfen.setTextSize(12);
        }
    }

    @Override
    public void changePingjiantext(String pingJianName) {
        if (pingJianName != null) {
            mPingJian.setText(pingJianName);
        }

    }

    private void initChexBox() {
        mTeaType.setChecked(false);
        mPingfen.setChecked(false);
        mPingJian.setChecked(false);
        mChaYang.setChecked(false);
    }

    @Override
    public void onClick(View v) {


        switch (v.getId()) {
            case R.id.tea_list_tea:

                //茶种类
                if (!mTeaType.isChecked()) {

                    switchpage(TeaListConListFragment.TAG,TeaListConListFragment.class);

                }
                else{
                    mPingfen.setChecked(false);
                    mPingJian.setChecked(false);
                    mChaYang.setChecked(false);
                    switchpage(TeaListCheckedListFragment.TAG, TeaListCheckedListFragment.class);
                    if (showfragment instanceof TeaListCheckedListFragment) {
                        ((TeaListCheckedListFragment) showfragment).setItemclicklistener(this);
                    }
                }


                break;
            case R.id.tea_list_pingfen:
                //评分
                if (!mPingfen.isChecked()) {

                    switchpage(TeaListConListFragment.TAG,TeaListConListFragment.class);

                }else {
                    mTeaType.setChecked(false);
                    mPingJian.setChecked(false);
                    mChaYang.setChecked(false);
                    switchpage(PingFenCheckedListFragment.TAG, PingFenCheckedListFragment.class);
                    if (showfragment instanceof PingFenCheckedListFragment) {
                        ((PingFenCheckedListFragment) showfragment).setItemclicklistener(this);
                    }

                }

                break;
            case R.id.tea_list_pinjian:
                //评鉴
                if (!mPingJian.isChecked()) {
                    switchpage(TeaListConListFragment.TAG,TeaListConListFragment.class);

                }else {


                    mTeaType.setChecked(false);
                    mPingfen.setChecked(false);
                    mChaYang.setChecked(false);
                    switchpage(PingJianCheckedListFragment.TAG, PingJianCheckedListFragment.class);
                    if (showfragment instanceof PingJianCheckedListFragment) {
                        ((PingJianCheckedListFragment) showfragment).setItemclicklistener(this);
                    }

                }

                break;
            case R.id.tea_list_chayang:
                //茶样
                order="remain";

                break;

        }
    }

}
