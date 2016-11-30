package com.phone1000.chayu.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import com.phone1000.chayu.R;
import com.phone1000.chayu.fragments.TeaListCheckedListFragment;
import com.phone1000.chayu.fragments.TeaListConListFragment;
import com.phone1000.chayu.modles.TeaChldeClickListenter;
import com.phone1000.chayu.modles.TeaListEvent;
import com.phone1000.chayu.weidgt.TopBar;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.reflect.InvocationTargetException;

public class TeaListActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener,TeaChldeClickListenter {

    private static final String TAG = TeaListActivity.class.getSimpleName();
    private TopBar mTopBar;
    private FrameLayout mListViewCon;
    private Fragment showfragment;
    private CheckBox mTeaType;
    private CheckBox mPingfen;
    private CheckBox mPingJian;
    private CheckBox mChaYang;



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
        mTeaType.setOnCheckedChangeListener(this);

        mPingfen = (CheckBox) findViewById(R.id.tea_list_pingfen);
        mPingfen.setOnCheckedChangeListener(this);

        mPingJian = (CheckBox) findViewById(R.id.tea_list_pinjian);
        mPingJian.setOnCheckedChangeListener(this);

        mChaYang = (CheckBox) findViewById(R.id.tea_list_chayang);
        mChaYang.setOnCheckedChangeListener(this);


        mListViewCon = (FrameLayout) findViewById(R.id.tea_list_listview_container);


        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        showfragment = new TeaListConListFragment();
        transaction.add(R.id.tea_list_listview_container,showfragment, TeaListConListFragment.TAG);
        transaction.commit();




    }



    @Subscribe(sticky = true,threadMode = ThreadMode.MAIN)
    public void onEvent(TeaListEvent event){
//        Log.e(TAG, "onEvent: "+event.getTag() );
        if (event.getWHAT()==0x100) {
            String sid = event.getSid();
            String bid = event.getBid();
            Log.e(TAG, "onEvent: tag ---------------->"+sid );

            TeaListEvent event1 = new TeaListEvent(0x110);
            event1.setBid(sid);
            event1.setSid(bid);
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
        }else {
            try {
                showfragment = cls.getConstructor().newInstance();
                transaction.add(R.id.tea_list_listview_container,showfragment,tag);
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
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        switch (buttonView.getId()) {
            case R.id.tea_list_tea:
                //茶种类

                switchpage(TeaListCheckedListFragment.TAG,TeaListCheckedListFragment.class);
                if (showfragment instanceof TeaListCheckedListFragment) {
                    ((TeaListCheckedListFragment) showfragment).setItemclicklistener(this);
                }

                break;
            case R.id.tea_list_pingfen:
                //评分

                break;
            case R.id.tea_list_pinjian:
                //评鉴

                break;
            case R.id.tea_list_chayang:
                //茶样

                break;
        }
    }

    @Override
    public void callMethod(String bid,String sid) {

        Log.e(TAG, "callMethod: bid ------------sid-------------"+bid+"-------------"+sid );

        switchpage(TeaListConListFragment.TAG,TeaListConListFragment.class);



    }
}
