package ywh.wh.test.webview;

import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

import butterknife.Bind;
import wh.ywh.base.BaseHelpActivity;
import wh.ywh.util.ToastUtil;
import ywh.wh.test.R;

/**
 * Created by Administrator on 2018-08-22.
 */

public class WebViewActivity extends BaseHelpActivity{
    @Bind(R.id.webView)
    WebView webView;

    @Override
    protected int setLayout() {
        return R.layout.ac_webview;
    }

    @Override
    protected void initData() {
        super.initData();
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webView.loadUrl("file:///android_asset/webviewTest.html");
//        webView.post(new Runnable() {
//            @Override
//            public void run() {
//                webView.loadUrl("javascript:test()");
//            }
//        });

        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                ToastUtil.toastShort(WebViewActivity.this,"url:"+url+",message:"+message);
                return true;
            }

            @Override
            public boolean onJsConfirm(WebView view, String url, String message, JsResult result) {
                return super.onJsConfirm(view, url, message, result);
            }

            @Override
            public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
                return super.onJsPrompt(view, url, message, defaultValue, result);
            }
        });
        webView.evaluateJavascript("javascript:test()", new ValueCallback<String>() {
            @Override
            public void onReceiveValue(String value) {
                //此处为 js 返回的结果
                ToastUtil.toastShort(WebViewActivity.this,"value:"+value);
            }
        });
    }
}
