package com.phone1000.chayu;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.google.gson.Gson;
import com.phone1000.chayu.adapters.PinPaiGridViewAdapter3;
import com.phone1000.chayu.event.PinPaiGridViewEvent;
import com.phone1000.chayu.fragments.PinJianNiandu;
import com.phone1000.chayu.fragments.PinPaiGridView;
import com.phone1000.chayu.fragments.PinPaiListViewFragment;
import com.phone1000.chayu.fragments.PinPaiPingFenFragment;
import com.phone1000.chayu.modles.PinPaiGridViewModle;
import com.phone1000.chayu.path.UtilPath;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by Administrator on 2016/11/29 0029.
 */
public class PinPaiActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    private static final String TAG = PinPaiActivity.class.getSimpleName();
    private CheckBox mCxname;
    private CheckBox mCxpingfen;
    private CheckBox mCxniandu;
    private CheckBox mCxkuncun;
    private String name;
    private String brandid;
    private Fragment showFragment;
    private PinPaiGridViewModle pinPaiGridViewModle;
    private boolean b1;
    private boolean iscanfalse = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.remenactivity);

        Intent intent = getIntent();

        name = intent.getStringExtra("name");
        brandid = intent.getStringExtra("brandid");



        RequestParams params = new RequestParams(UtilPath.PINPAI_GRIDVIEW);
        params.addParameter("source","3");
        params.addParameter("version","5");
        params.addParameter("imei","4731955d1f681a93");
        params.addParameter("versionCode","2.2.4");
        params.addParameter("agent","5");
        params.addParameter("type","brand");
        x.http().post(params, new Callback.CommonCallback<String>() {

            @Override
            public void onSuccess(String result) {
                Log.e(TAG, "onSuccess: " +result);

                Gson gson = new Gson();
                pinPaiGridViewModle = gson.fromJson(result, PinPaiGridViewModle.class);
                Toast.makeText(PinPaiActivity.this, "数据加载完毕", Toast.LENGTH_SHORT).show();
                initView();

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e(TAG, "onError: " );
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

    private void initView() {

        mCxname = (CheckBox) findViewById(R.id.cx_name);
        mCxpingfen = (CheckBox) findViewById(R.id.cx_pingfen);
        mCxniandu = (CheckBox) findViewById(R.id.cx_niandu);
        mCxkuncun = (CheckBox) findViewById(R.id.cx_kucun);
        mCxname.setOnCheckedChangeListener(this);
        mCxpingfen.setOnCheckedChangeListener(this);
        mCxniandu.setOnCheckedChangeListener(this);
        mCxkuncun.setOnCheckedChangeListener(this);

        mCxname.setText(name);

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        showFragment = new PinPaiListViewFragment();
        Bundle bundle = new Bundle();
        bundle.putString("name",name);
        bundle.putString("brandid",brandid);
        showFragment.setArguments(bundle);
        transaction.add(R.id.pinpai_fragment,showFragment,PinPaiListViewFragment.TAG);
        transaction.commit();

    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

        switch (compoundButton.getId()) {

            case R.id.cx_name:
                b1 = true;
                iscanfalse = false;
                switchnamepage(b);
                break;
            case R.id.cx_pingfen:
                b1 = false;
                iscanfalse = false;
                switchpingfenpage(b);
                break;
            case R.id.cx_niandu:
                b1 = false;
                iscanfalse = false;
                switchnaindupage(b);

                break;
            case R.id.cx_kucun:
                b1 = false;
                switchkucunpage(b);

                break;

        }


    }

    private void switchkucunpage(boolean b) {

        if(b){

            mCxpingfen.setChecked(false);
            PinPaiGridViewEvent pinPaiGridViewEvent = new PinPaiGridViewEvent(192);
            String order = "remain";
            pinPaiGridViewEvent.setOrder(order);
            EventBus.getDefault().postSticky(pinPaiGridViewEvent);
            iscanfalse = true;
        }else{

           if(iscanfalse){
               mCxkuncun.setChecked(true);
           }

        }
        mCxname.setChecked(false);

    }

    private void switchnaindupage(boolean b) {
        if (b){
            mCxname.setChecked(false);
            mCxkuncun.setChecked(false);
            mCxpingfen.setChecked(false);
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.hide(showFragment);
            PinJianNiandu pinJianNiandu = new PinJianNiandu();
            Bundle bundle = new Bundle();
            bundle.putParcelable("bundle",pinPaiGridViewModle);
            bundle.putString("id",brandid);
            pinJianNiandu.setArguments(bundle);
            showFragment = pinJianNiandu;
            transaction.add(R.id.pinpai_fragment,pinJianNiandu);
            transaction.commit();
        }else{
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            //transaction.hide(showFragment);
            Fragment fragmentByTag = manager.findFragmentByTag(PinPaiListViewFragment.TAG);
            if (fragmentByTag != null) {
                transaction.replace(R.id.pinpai_fragment,fragmentByTag);
                showFragment = fragmentByTag;

            }else {
                fragmentByTag = new PinPaiListViewFragment();
                transaction.replace(R.id.pinpai_fragment,fragmentByTag);
                transaction.add(R.id.pinpai_fragment,fragmentByTag,PinPaiListViewFragment.TAG);
                showFragment = fragmentByTag;
            }
            transaction.show(showFragment);
            transaction.commit();
        }


    }

    private void switchpingfenpage(boolean b) {

        if (b){
            mCxname.setChecked(false);
            mCxniandu.setChecked(false);
            mCxkuncun.setChecked(false);
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.hide(showFragment);
            PinPaiPingFenFragment pinPaiPingFenFragment = new PinPaiPingFenFragment();
            Bundle bundle = new Bundle();
            bundle.putParcelable("bundle",pinPaiGridViewModle);
            bundle.putString("id",brandid);
            pinPaiPingFenFragment.setArguments(bundle);
            showFragment = pinPaiPingFenFragment;
            transaction.add(R.id.pinpai_fragment,pinPaiPingFenFragment);
            transaction.commit();
        }else{
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            //transaction.hide(showFragment);
            Fragment fragmentByTag = manager.findFragmentByTag(PinPaiListViewFragment.TAG);
            if (fragmentByTag != null) {
                transaction.replace(R.id.pinpai_fragment,fragmentByTag);
                showFragment = fragmentByTag;

            }else {
                fragmentByTag = new PinPaiListViewFragment();
                transaction.replace(R.id.pinpai_fragment,fragmentByTag);
                transaction.add(R.id.pinpai_fragment,fragmentByTag,PinPaiListViewFragment.TAG);
                showFragment = fragmentByTag;
            }
            transaction.show(showFragment);
            transaction.commit();
        }

    }

    private void switchnamepage(boolean b) {

        if (b){
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.hide(showFragment);
            PinPaiGridView pinPaiGridView = new PinPaiGridView();
            Bundle bundle = new Bundle();
            bundle.putParcelable("bundle",pinPaiGridViewModle);
            pinPaiGridView.setArguments(bundle);
            transaction.add(R.id.pinpai_fragment,pinPaiGridView);
            transaction.commit();
            mCxpingfen.setChecked(false);
            mCxkuncun.setChecked(false);
            mCxniandu.setChecked(false);
        }else if(b==false&&b1){
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.hide(showFragment);
            Fragment fragmentByTag = manager.findFragmentByTag(PinPaiListViewFragment.TAG);
            if (fragmentByTag != null) {
                transaction.replace(R.id.pinpai_fragment,fragmentByTag);
                showFragment = fragmentByTag;

            }else {
                fragmentByTag = new PinPaiListViewFragment();
                transaction.replace(R.id.pinpai_fragment,fragmentByTag);
                transaction.add(R.id.pinpai_fragment,fragmentByTag,PinPaiListViewFragment.TAG);
                showFragment = fragmentByTag;
            }
            transaction.show(showFragment);
            transaction.commit();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onEvent(PinPaiGridViewEvent event){
        switch (event.getWhat()){

            case 198:
                name = event.getName();
                brandid = event.getId();
                Log.e(TAG, "onEvent: 传过来的数据"+name+brandid );
                mCxname.setText(name);
                mCxname.setChecked(false);
                break;
            case 196:{
                b1 = true;

                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                //transaction.hide(showFragment);
                Fragment fragmentByTag = manager.findFragmentByTag(PinPaiListViewFragment.TAG);
                if (fragmentByTag != null) {
                    transaction.replace(R.id.pinpai_fragment,fragmentByTag);
                    showFragment = fragmentByTag;

                }else {
                    fragmentByTag = new PinPaiListViewFragment();
                    transaction.replace(R.id.pinpai_fragment,fragmentByTag);
                    transaction.add(R.id.pinpai_fragment,fragmentByTag,PinPaiListViewFragment.TAG);
                    showFragment = fragmentByTag;
                }
                transaction.show(showFragment);
                transaction.commit();

                mCxniandu.setChecked(false);
                mCxpingfen.setChecked(false);

            }
        }
    }

}
