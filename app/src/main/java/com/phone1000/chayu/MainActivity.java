package com.phone1000.chayu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.phone1000.chayu.Interface.FragmentChangeInterface;
import com.phone1000.chayu.activity.LoginActivity;
import com.phone1000.chayu.activity.UserActivity;
import com.phone1000.chayu.fragments.ChaPingFragment;
import com.phone1000.chayu.fragments.HomeFragment;
import com.phone1000.chayu.fragments.QuanZiFragment;
import com.phone1000.chayu.fragments.ShiJiFragment;
import com.phone1000.chayu.fragments.WenZhangFragment;
import com.phone1000.chayu.weidgt.TopBar;

import java.lang.reflect.InvocationTargetException;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qq.QQ;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener, View.OnClickListener,FragmentChangeInterface {
    private static final String TAG = MainActivity.class.getSimpleName();
    private RadioGroup main_rg;
    private Fragment showfragment;
    private TopBar mTopBar;
    private RadioButton mShijiBtn;
    private RadioButton mQuanziBtn;
    private RadioButton mWenzhangbtn;
    private Platform platform = ShareSDK.getPlatform(this, QQ.NAME);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        main_rg = ((RadioGroup) findViewById(R.id.main_controller));
        main_rg.setOnCheckedChangeListener(this);
        mTopBar = (TopBar) findViewById(R.id.top_bar);
        mTopBar.setMineListener(this);

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        showfragment = new HomeFragment();
        mTopBar.topshareVisible(false);
        transaction.add(R.id.main_container,showfragment,HomeFragment.TAG);
        transaction.commit();

        if (showfragment instanceof HomeFragment) {
            Log.e(TAG, "onCheckedChanged: Homepage的更多的监听开启");
            ((HomeFragment) showfragment).setFragmentChangelistener(this);
        }

        mShijiBtn = (RadioButton) findViewById(R.id.main_controller_shi_ji);
        mQuanziBtn = (RadioButton) findViewById(R.id.main_controller_quan_zi);
        mWenzhangbtn = (RadioButton) findViewById(R.id.main_controller_wen_zhang);

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){

            case R.id.main_controller_home:
                switchpage(HomeFragment.TAG,HomeFragment.class);
                mTopBar.logoVisible(true);
                mTopBar.topshareVisible(false);

                break;
            case R.id.main_controller_cha_ping:
                switchpage(ChaPingFragment.TAG,ChaPingFragment.class);
                mTopBar.logoVisible(false);
                mTopBar.topshareVisible(true);
                break;
            case R.id.main_controller_shi_ji:
                switchpage(ShiJiFragment.TAG,ShiJiFragment.class);
                mTopBar.logoVisible(false);
                mTopBar.topshareVisible(true);
                break;
            case R.id.main_controller_quan_zi:
                switchpage(QuanZiFragment.TAG,QuanZiFragment.class);
                mTopBar.logoVisible(false);
                mTopBar.topshareVisible(true);
                break;
            case R.id.main_controller_wen_zhang:
                switchpage(WenZhangFragment.TAG,WenZhangFragment.class);
                mTopBar.logoVisible(false);
                mTopBar.topshareVisible(true);
                break;
        }
    }
    private void switchpage(String tag, Class<? extends Fragment> cls) {

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.hide(showfragment);
        showfragment = (Fragment) manager.findFragmentByTag(tag);
        if (showfragment != null) {
            transaction.show(showfragment);
        }else {
            try {
                showfragment = cls.getConstructor().newInstance();
                transaction.add(R.id.main_container,showfragment,tag);
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
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.top_mine_btn:

                Log.e(TAG, "onClick: "+platform.isValid());
                if (platform.isValid()) {

                    Intent intent = new Intent(this, UserActivity.class);
                    startActivity(intent);


                }else {

                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                }


                break;

        }



    }

//------------------------homepage mor 点击监听---------------------
    @Override
    public void shijiMoreClick() {
        mShijiBtn.setChecked(true);
    }

    @Override
    public void quanziMoreClick() {
        mQuanziBtn.setChecked(true);

    }

    @Override
    public void wenzhangMoreClick() {
        mWenzhangbtn.setChecked(true);

    }
}
