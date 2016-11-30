package com.phone1000.chayu;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.phone1000.chayu.weidgt.ShareTopBar;

/**
 * Created by Administrator on 2016/11/29 0029.
 */
public class DetailsInFormation extends AppCompatActivity implements View.OnClickListener{

    private WebView mWebView;
    private String path;
    private ShareTopBar mSharetopbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailsinformation);
        Intent intent = getIntent();
        path = intent.getStringExtra("path");

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

                break;
            case R.id.share_qq:

                break;
            case R.id.share_weixin:
                break;
            case R.id.share_pengyouquan:
                break;
        }

    }
}
