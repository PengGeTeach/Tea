package com.phone1000.chayu;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.phone1000.chayu.weidgt.ShareTopBar;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.tencent.qq.QQ;

/**
 * Created by Administrator on 2016/11/29 0029.
 */
public class DetailsInFormation extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = DetailsInFormation.class.getSimpleName();
    private WebView mWebView;
    private String path;
    private ShareTopBar mSharetopbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailsinformation);
        Intent intent = getIntent();
        path = intent.getStringExtra("path");
        Log.e(TAG, "onCreate: "+path );

        initView();

    }

    private void initView() {

        mSharetopbar = (ShareTopBar) findViewById(R.id.share_topbar);
        mSharetopbar.shareTopshare(this);

        mWebView = (WebView) findViewById(R.id.web_view);

        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new WebViewClient());

        WebChromeClient chromeClient = new WebChromeClient();

        mWebView.setWebChromeClient(chromeClient);
        mWebView.loadUrl(path);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.back:
                finish();
                break;
            case R.id.top_share:

                showShare();

                break;
            case R.id.share_qq:

                QQ.ShareParams sp = new QQ.ShareParams();
                sp.setTitle("测试分享的标题");
                sp.setTitleUrl(path);
                Platform platform = ShareSDK.getPlatform(QQ.NAME);
                platform.setPlatformActionListener(new PlatformActionListener() {
                    @Override
                    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {

                    }

                    @Override
                    public void onError(Platform platform, int i, Throwable throwable) {

                    }

                    @Override
                    public void onCancel(Platform platform, int i) {

                    }
                });

                platform.share(sp);

                break;
            case R.id.share_weixin:
                break;
            case R.id.share_pengyouquan:
                break;
        }

    }


    private void showShare() {
        ShareSDK.initSDK(this);
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

// 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
        //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        oks.setTitle("测试标题");
        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
        oks.setTitleUrl(path);
        // text是分享文本，所有平台都需要这个字段
        oks.setText("我是测试文本");
        //分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
        oks.setImageUrl("http://f1.sharesdk.cn/imgs/2014/02/26/owWpLZo_638x960.jpg");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://sharesdk.cn");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite("ShareSDK");
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://sharesdk.cn");

// 启动分享GUI
        oks.show(this);
    }

}
