package com.example.goonbarytime;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class activity_hyperlink_mma extends AppCompatActivity {

    private WebView webview_mma;
    private String url = "https://www.mma.go.kr";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hyperlink_mma);
        webview_mma = (WebView)findViewById(R.id.webview_mma);
        webview_mma.getSettings().setJavaScriptEnabled(true);
        webview_mma.loadUrl(url);
        webview_mma.setWebChromeClient(new WebChromeClient());
        webview_mma.setWebViewClient(new WebViewClientClass());
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webview_mma.canGoBack()) {
            webview_mma.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private class WebViewClientClass extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) { // 이 메소드는 현재 페이지의 url을 읽어올수있는 메소드이다.
            view.loadUrl(url);
            return true;
        }
    }
}