package com.leoman.helper.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.leoman.helper.BaseAcHelper;
import com.leoman.helper.HelperLibrary;
import com.leoman.helper.R;

/**
 * Created by spurs on 2017/3/28.
 * 加载网页页面
 */

public class WebActivity extends BaseAcHelper {

    private RelativeLayout rl_title;
    private LinearLayout ll_back;
    private TextView tv_title;
    private WebView webView;
    private ProgressBar progressbar;
    private String url, title;
    private int flag;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_web);
        flag = getIntent().getIntExtra("flag", 0);//0--url  1--富文本
        url = getIntent().getStringExtra("url");
        title = getIntent().getStringExtra("title");
        initView();
        initWeb();
        loadUrl();
    }

    private void initView() {
        rl_title = (RelativeLayout) findViewById(R.id.rl_title);
        ll_back = (LinearLayout) findViewById(R.id.ll_back);
        tv_title = (TextView) findViewById(R.id.tv_title);
        webView = (WebView) findViewById(R.id.web);
        progressbar = (ProgressBar) findViewById(R.id.progress);

        tv_title.setText(title);
        rl_title.setBackgroundResource(HelperLibrary.getInstance().getColor());
        ll_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    private void loadUrl() {
        if (!TextUtils.isEmpty(url)) {
            if (flag == 0)
                webView.loadUrl(url.startsWith("http") || url.startsWith("file:") ? url : ("http://" + url));
            else
                webView.loadDataWithBaseURL(null, url, "text/html", "utf-8", null);
        }
    }

    private void initWeb() {
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setPluginState(WebSettings.PluginState.ON);
        webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webView.setVerticalScrollBarEnabled(false);
        webView.setVerticalScrollbarOverlay(false);
        webView.setHorizontalScrollBarEnabled(false);
        webView.setHorizontalScrollbarOverlay(false);

        webView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

        webView.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {
                super.onProgressChanged(view, progress);
                progressbar.setProgress(progress);
                if (progress == 100) {
                    progressbar.setVisibility(View.GONE);
                }
            }
        });
    }
}
