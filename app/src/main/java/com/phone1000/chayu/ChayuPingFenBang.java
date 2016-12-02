package com.phone1000.chayu;

import android.content.Intent;
import android.os.Bundle;
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
import com.phone1000.chayu.event.BangDanEvent;
import com.phone1000.chayu.event.BnagDanToFragmentEvent;
import com.phone1000.chayu.fragments.BangDanFragment;
import com.phone1000.chayu.fragments.BangDanNiandu;
import com.phone1000.chayu.fragments.ChaLeiBangdanFragment;
import com.phone1000.chayu.fragments.ChaYuZongHe;
import com.phone1000.chayu.fragments.ChayuPingFenFragment;
import com.phone1000.chayu.modles.BnagdanActivity;
import com.phone1000.chayu.path.UtilPath;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by Administrator on 2016/11/30 0030.
 */
public class ChayuPingFenBang extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    private static final String TAG = ChayuPingFenBang.class.getSimpleName();
    private CheckBox mPingfenbang;
    private CheckBox mChaLei;
    private CheckBox mNianfen;
    private String bang;
    private Fragment showFragment;
    private boolean b1 = true;
    private BnagdanActivity bnagdanActivity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chayupingfenbang);
        Intent intent = getIntent();
        bang = intent.getStringExtra("bang");
        initView();
        setupView();

    }

    private void setupView() {

        RequestParams params = new RequestParams(UtilPath.BANG_DAN_ACTIVITY);
        params.addParameter("source","3");
        params.addParameter("version","5");
        params.addParameter("imei","4731955d1f681a93");
        params.addParameter("versionCode","2.2.4");
        params.addParameter("agent","5");
        x.http().post(params, new Callback.CommonCallback<String>() {


            @Override
            public void onSuccess(String result) {
                Log.e(TAG, "onSuccess: "+result );

                Gson gson = new Gson();
                bnagdanActivity = gson.fromJson(result, BnagdanActivity.class);
                Toast.makeText(ChayuPingFenBang.this, "数据加载完毕", Toast.LENGTH_SHORT).show();
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

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        showFragment = new ChayuPingFenFragment();
        Bundle bundle = new Bundle();
        bundle.putString("bang", bang);
        showFragment.setArguments(bundle);
        transaction.add(R.id.bangdan_framlayout, showFragment, ChayuPingFenFragment.TAG);
        transaction.commit();
    }

    private void initView() {

        mPingfenbang = (CheckBox) findViewById(R.id.pingfenbang);
        mChaLei = (CheckBox) findViewById(R.id.chalei);
        mNianfen = (CheckBox) findViewById(R.id.nianfen);


        mPingfenbang.setOnCheckedChangeListener(this);
        mChaLei.setOnCheckedChangeListener(this);
        mNianfen.setOnCheckedChangeListener(this);


        if (bang.equals("1")) {
            mPingfenbang.setText("茶语评分榜");
        } else {
            mPingfenbang.setText("综合评分榜");
        }

    }


    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        switch (compoundButton.getId()) {
            case R.id.pingfenbang:

                b1 = true;
                if (b){
                    mNianfen.setChecked(false);
                    mChaLei.setChecked(false);
                    switchpage(ChaYuZongHe.TAG,ChaYuZongHe.class);
                }else{
                    if (b1){
                        transaction.hide(showFragment);
                        showFragment = manager.findFragmentByTag(ChayuPingFenFragment.TAG);
                        if (showFragment != null) {
                            transaction.show(showFragment);
                        }else{
                            showFragment = new ChayuPingFenFragment();
                            transaction.add(R.id.bangdan_framlayout,showFragment,ChayuPingFenFragment.TAG);
                        }
                    }
                    transaction.commit();
                }

                break;
            case R.id.chalei:
                b1 = false;
                if (b){
                    mPingfenbang.setChecked(false);
                    mNianfen.setChecked(false);
                    switchpage(ChaLeiBangdanFragment.TAG,ChaLeiBangdanFragment.class);
                }else{
                    transaction.hide(showFragment);
                    showFragment = manager.findFragmentByTag(ChayuPingFenFragment.TAG);
                    if (showFragment != null) {
                        transaction.show(showFragment);
                    }else{
                        showFragment = new ChayuPingFenFragment();
                        transaction.add(R.id.bangdan_framlayout,showFragment,ChayuPingFenFragment.TAG);
                    }

                }
                transaction.commit();
                break;
            case R.id.nianfen:
                b1 = false;
                if (b){
                    mChaLei.setChecked(false);
                    mPingfenbang.setChecked(false);
                    switchpage(BangDanFragment.TAG, BangDanNiandu.class);
                }else{
                    transaction.hide(showFragment);
                    showFragment = manager.findFragmentByTag(ChayuPingFenFragment.TAG);
                    if (showFragment != null) {
                        transaction.show(showFragment);
                    }else{
                        showFragment = new ChayuPingFenFragment();
                        transaction.add(R.id.bangdan_framlayout,showFragment,ChayuPingFenFragment.TAG);
                    }

                }
                transaction.commit();
                break;
        }

    }

    private void switchpage(String tag, Class<? extends Fragment> cls) {

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.hide(showFragment);
        showFragment = manager.findFragmentByTag(tag);
        Bundle args = new Bundle();
        args.putParcelable("bangdan",bnagdanActivity);
        if (showFragment!=null){
            transaction.show(showFragment);
            transaction.commit();
        }else{

            try {
                showFragment = cls.getConstructor().newInstance();
                transaction.add(R.id.bangdan_framlayout,showFragment,tag);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
            showFragment.setArguments(args);
            transaction.commit();
        }
    }

    @Subscribe
    public void onEvent(BnagDanToFragmentEvent event){
        switch (event.WHAT) {
            case 199:
                break;
            case 198:
                break;
            case 100:
                mPingfenbang.setChecked(false);
                String bangming = event.getBangming();
                mPingfenbang.setText(bangming);
                break;
            case 101:
                mChaLei.setChecked(false);
                String leibie = event.getLeibie();
                mChaLei.setText(leibie);
                break;
            case 102:
                mNianfen.setChecked(false);
                String nianfen = event.getNianfen();
                mNianfen.setText(nianfen);
                break;
        }


    }

    @Override
    public void onStart() {
        super.onStart();
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }
}
