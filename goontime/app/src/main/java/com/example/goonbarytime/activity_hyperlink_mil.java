package com.example.goonbarytime;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class activity_hyperlink_mil extends AppCompatActivity {

    private WebView webview_mil;
    private String url = "https://www.army.mil.kr";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hyperlink_mil);
        webview_mil = (WebView)findViewById(R.id.webview_mil);
        webview_mil.getSettings().setJavaScriptEnabled(true);
        webview_mil.loadUrl(url);
        webview_mil.setWebChromeClient(new WebChromeClient());
        webview_mil.setWebViewClient(new WebViewClientClass());
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webview_mil.canGoBack()) {
            webview_mil.goBack();
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