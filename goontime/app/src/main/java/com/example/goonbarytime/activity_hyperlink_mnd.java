package com.example.goonbarytime;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class activity_hyperlink_mnd extends AppCompatActivity {

    private WebView webview_mnd;
    private String url = "https://www.mnd.go.kr";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hyperlink_mnd);
        webview_mnd = (WebView)findViewById(R.id.webview_mnd);
        webview_mnd.getSettings().setJavaScriptEnabled(true);
        webview_mnd.loadUrl(url);
        webview_mnd.setWebChromeClient(new WebChromeClient());
        webview_mnd.setWebViewClient(new WebViewClientClass());
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webview_mnd.canGoBack()) {
            webview_mnd.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private class WebViewClientClass extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}