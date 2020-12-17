package com.example.x_etc_47_53.Activity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.x_etc_47_53.R;

public class Activity_web extends AppCompatActivity {

    private ImageView caidan;
    private TextView title;
    private ProgressBar webPro;
    private WebView web;
    private boolean isLoop = true;
    private int P0 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        initView();
        getinit();


        getTime();


    }

    private void getTime() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (isLoop){
                    webPro.setProgress(P0);
                    if (P0 >=100){
                        isLoop = false;
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                web.loadUrl("file:///android_asset/www/bendi.html");
                                web.getSettings().setSupportZoom(true);
                                web.getSettings().setBuiltInZoomControls(true);
                                webPro.setVisibility(View.GONE);
                            }
                        });
                    }
                    P0 += 1;
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }


    private void getinit() {
        title.setText("WEBVIEW");
        caidan.setImageResource(R.drawable.back);
        caidan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initView() {
        caidan = findViewById(R.id.caidan);
        title = findViewById(R.id.title);
        webPro = findViewById(R.id.web_pro);
        web = findViewById(R.id.web);
    }
}