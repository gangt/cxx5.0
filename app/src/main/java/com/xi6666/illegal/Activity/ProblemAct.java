package com.xi6666.illegal.Activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.xi6666.R;


/**
 * Created by Administrator on 2016/6/7 0007.
 */
public class ProblemAct extends IllegalBaseActivity {
        private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problem);
        initToolBar();
        webView= (WebView) findViewById(R.id.web_problem);
        WebSettings webSettings=webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
//        webView.loadUrl("http://www.xi6666.com/chexiaoxi.php?m=active&act=traffic&detail=problem&by=android");
        webView.loadUrl("http://dev-app.xiaoxi6.com/illegal/problems");
    }
    private void initToolBar() {
        Toolbar tb = (Toolbar) findViewById(R.id.problem_tb);
        setSupportActionBar(tb);
        findViewById(R.id.problem_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


}
