package com.example.petbomi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        WebView webView=findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.addJavascriptInterface(new BridgeInterface(),"Android");
        webView.setWebViewClient((new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                webView.loadUrl("javascript:sample2_execDaumPostcode()");
            }
        }));
            webView.loadUrl("https://mapsearch-40c6d.web.app");
    }

    private class BridgeInterface {
        @JavascriptInterface
        public void processDATA(String data){
            Intent intent=new Intent();
            intent.putExtra("data",data);
            setResult(RESULT_OK,intent);
            finish();
        }
    }
}