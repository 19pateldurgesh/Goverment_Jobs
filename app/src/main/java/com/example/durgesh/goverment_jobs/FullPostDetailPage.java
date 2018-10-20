package com.example.durgesh.goverment_jobs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

public class FullPostDetailPage extends AppCompatActivity {


    String pname, pdate, pdetail, plink="";
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_post_detail_page);

        webView = (WebView) findViewById(R.id.webview1);

        Intent in = getIntent();
        pname = in.getStringExtra("PostName");
        pdate = in.getStringExtra("PostDate");
        pdetail = in.getStringExtra("PostDetail");
        plink = in.getStringExtra("PostLink");

        webView.setWebViewClient(new WebViewClient());

        if(!plink.equals("")){
            webView.loadUrl(plink);
        }
        else{
            webView.loadUrl("https://www.google.com/");
        }
    }
}
