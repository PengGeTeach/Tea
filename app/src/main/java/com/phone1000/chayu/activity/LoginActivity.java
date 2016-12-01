package com.phone1000.chayu.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.phone1000.chayu.R;

import java.util.HashMap;
import java.util.Set;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qq.QQ;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, PlatformActionListener {

    private static final String TAG = LoginActivity.class.getSimpleName();
    private ImageView mWeiXin;
    private ImageView mQQ;
    private ImageView mXinlang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView() {
        mWeiXin = (ImageView) findViewById(R.id.activity_login_weixin);
        mQQ = (ImageView) findViewById(R.id.activity_login_qq);
        mXinlang = (ImageView) findViewById(R.id.activity_login_xinlang);

        mWeiXin.setOnClickListener(this);
        mQQ.setOnClickListener(this);
        mXinlang.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_login_weixin:
                Toast.makeText(LoginActivity.this, "Sorry没有微信登录", Toast.LENGTH_SHORT).show();
                break;
            case R.id.activity_login_qq:

                landUser();

                break;
            case R.id.activity_login_xinlang:
                Toast.makeText(LoginActivity.this, "Sorry没有新浪登录", Toast.LENGTH_SHORT).show();
                break;
        }

    }

    private void landUser() {

        //首先 初始化 ShareSdk
        ShareSDK.initSDK(this);
        //获取指定的平台
        Platform platform = ShareSDK.getPlatform(this, QQ.NAME);
        //给平台添加监听
        platform.setPlatformActionListener(this);
        //调用平台认证

        platform.authorize();
        //showUser
        platform.showUser(null);


    }


    @Override
    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
        Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();

        Log.e(TAG, "onComplete: " + i);
        Set<String> strings = hashMap.keySet();
        for (String key :
                strings) {
            Log.e(TAG, "onComplete: key" + key + "------" + hashMap.get(key));
            //用户的唯一标示,通常用来第三方的登录的绑定
            Log.e(TAG, "onComplete: " + platform.getDb().getUserId());
        }

        //        Message msg = handler.obtainMessage();
        //        msg.what=0100;
        //        msg.obj=hashMap;
        //        handler.sendMessage(msg);

    }

    @Override
    public void onError(Platform platform, int i, Throwable throwable) {

    }

    @Override
    public void onCancel(Platform platform, int i) {

    }
}
