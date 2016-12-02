package com.phone1000.chayu.activity;

import android.os.Handler;
import android.os.Message;
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

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, PlatformActionListener,Handler.Callback {

    private static final String TAG = LoginActivity.class.getSimpleName();
    private static final int MSG_AUTH_CANCEL = 0x789;
    private static final int MSG_AUTH_COMPLETE = 0x456;
    private static final int MSG_AUTH_ERROR = 0x123;
    private static final int MSG_SMSSDK_CALLBACK = 0x147;
    private ImageView mWeiXin;
    private ImageView mQQ;
    private ImageView mXinlang;
    //获取指定的平台
    private Platform platform = ShareSDK.getPlatform(this, QQ.NAME);
    private Handler handler;
    private Platform mPlatform;
    private ImageView mBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        handler=new Handler(this);
    }

    private void initView() {
        mWeiXin = (ImageView) findViewById(R.id.activity_login_weixin);
        mQQ = (ImageView) findViewById(R.id.activity_login_qq);
        mXinlang = (ImageView) findViewById(R.id.activity_login_xinlang);

        mWeiXin.setOnClickListener(this);
        mQQ.setOnClickListener(this);
        mXinlang.setOnClickListener(this);
        mBack = (ImageView) findViewById(R.id.activity_login_back);
        mBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_login_weixin:
                Toast.makeText(LoginActivity.this, "Sorry没有微信登录", Toast.LENGTH_SHORT).show();
                break;
            case R.id.activity_login_qq:

                authorize(platform);
                Log.e(TAG, "onClick: "+platform.isValid() );

                break;
            case R.id.activity_login_xinlang:
                Toast.makeText(LoginActivity.this, "Sorry没有新浪登录", Toast.LENGTH_SHORT).show();
                break;
            case R.id.activity_login_back:
                finish();
                break;
        }

    }

    //执行授权,获取用户信息
    //文档：http://wiki.mob.com/Android_%E8%8E%B7%E5%8F%96%E7%94%A8%E6%88%B7%E8%B5%84%E6%96%99
    private void authorize(Platform plat) {
        if (plat == null) {
            //popupOthers();
            return;
        }

        plat.setPlatformActionListener(this);
        //关闭SSO授权
        plat.SSOSetting(false);
        plat.authorize();
        plat.showUser(null);
    }


    @Override
    public void onCancel(Platform platform, int action) {
        // TODO Auto-generated method stub
        if (action == Platform.ACTION_USER_INFOR) {
            handler.sendEmptyMessage(MSG_AUTH_CANCEL);
        }
    }

    @Override
    public void onComplete(Platform platform, int action,
                           HashMap<String, Object> res) {
        // TODO Auto-generated method stub
        if (action == Platform.ACTION_USER_INFOR) {
            Message msg = new Message();
            msg.what = MSG_AUTH_COMPLETE;
            msg.obj = new Object[] {platform.getName(), res};
            handler.sendMessage(msg);
        }
    }

    @Override
    public void onError(Platform arg0, int action, Throwable t) {
        // TODO Auto-generated method stub
        if (action == Platform.ACTION_USER_INFOR) {
            handler.sendEmptyMessage(MSG_AUTH_ERROR);
        }
        t.printStackTrace();
    }

    @SuppressWarnings("unchecked")
    public boolean handleMessage(Message msg) {
        switch(msg.what) {
            case MSG_AUTH_CANCEL: {
                //取消授权
                Toast.makeText(LoginActivity.this, "取消授权", Toast.LENGTH_SHORT).show();
            } break;
            case MSG_AUTH_ERROR: {
                //授权失败
                Toast.makeText(LoginActivity.this, "授权失败", Toast.LENGTH_SHORT).show();
            } break;
            case MSG_AUTH_COMPLETE: {
                //授权成功
                Toast.makeText(LoginActivity.this, "授权成功", Toast.LENGTH_LONG).show();

                Object[] objs = (Object[]) msg.obj;
                String platform = (String) objs[0];
                HashMap<String, Object> res = (HashMap<String, Object>) objs[1];
//                  if (signupListener != null && signupListener.onSignin(platform, res)) {
//                      SignupPage signupPage = new SignupPage();
//                      signupPage.setOnLoginListener(signupListener);
//                      signupPage.setPlatform(platform);
//                      signupPage.show(activity, null);
//                  }
                doLogined(platform);
            } break;
            case MSG_SMSSDK_CALLBACK:
                //{
//                  if (msg.arg2 == SMSSDK.RESULT_ERROR) {
//                      Toast.makeText(activity, "操作失败", Toast.LENGTH_SHORT).show();
//                  } else {
//                      switch (msg.arg1) {
//                          case SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE: {
//                              if(msgLoginDlg != null && msgLoginDlg.isShowing()){
//                                  msgLoginDlg.dismiss();
//                              }
//                              Toast.makeText(activity, "提交验证码成功", Toast.LENGTH_SHORT).show();
//                              Message m = new Message();
//                              m.what = MSG_AUTH_COMPLETE;
//                              m.obj = new Object[] {"SMSSDK", (HashMap<String, Object>) msg.obj};
//                              handler.sendMessage(m);
//                          } break;
//                          case SMSSDK.EVENT_GET_VERIFICATION_CODE:{
//                              Toast.makeText(activity, "验证码已经发送", Toast.LENGTH_SHORT).show();
//                          } break;
//                      }
//                  }
                //}
                break;
        }
        return false;
    }


    private void doLogined(String platform) {
        // TODO Auto-generated method stub
        mPlatform = ShareSDK.getPlatform(platform);
        String gender = "";
        if(platform != null){
            gender = mPlatform.getDb().getUserGender();
            if(gender.equals("m")){
                //  userInfo.setUserGender(UserInfo.Gender.BOY);
                gender = "男";
            }else{
                //userInfo.setUserGender(UserInfo.Gender.GIRL);
                gender = "女";
            }

//              userInfo.setUserIcon(platform.getDb().getUserIcon());
//              userInfo.setUserName(platform.getDb().getUserName());
//              userInfo.setUserNote(platform.getDb().getUserId());
            Toast.makeText(getApplicationContext(), gender+"/"+mPlatform.getDb().getUserName()+"/"+mPlatform.getDb().getUserId(), Toast.LENGTH_SHORT).show();
        }

//          tvUserName.setText(userInfo.getUserName());
//          tvUserGender.setText(gender);
//          tvUserNote.setText("USER ID : " + userInfo.getUserNote());
    }



}
