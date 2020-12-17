package com.example.x_etc_47_53.Activity;


import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.x_etc_47_53.R;

public class Activity_Wl extends AppCompatActivity {

    private ImageView caidan;
    private TextView title;
    private Button mainChaxun;

    private Button btnWangluo;
    private boolean isLoop = true, isShow = true;
    private AlertDialog dialog;
    private Thread thread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__wl);
        initView();
        getinit();

        NetWorkChange netWorkChange = new NetWorkChange();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(netWorkChange, intentFilter);

        thread = getMyThread();

        getMyThread();

        btnWangluo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_Wl.this,Activity_web.class);
                startActivity(intent);
            }
        });



    }

    private void getinit() {
        caidan.setImageResource(R.drawable.back);
        caidan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        title.setText("主界面");


    }

    private Thread getMyThread() {
        return new Thread(new Runnable() {
            @Override
            public void run() {
                do {
                    if (isShow) {
                        isShow = false;
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ShowDialog("网络已断开", Activity_Wl.this);
                            }
                        });
                    }
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } while (isLoop);
            }
        });
    }

    class NetWorkChange extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager connectivityManager =
                    (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isAvailable()) {
                if (dialog != null && dialog.isShowing()) {
                    dialog.dismiss();
                }
                ShowDialog("网络已连接", context);
                isLoop = false;
            } else {
                isLoop = true;
                if (thread.getState() == Thread.State.NEW) {
                    thread.start();
                } else if (thread.getState() == Thread.State.TERMINATED) {
                    thread = null;
                    thread = getMyThread();
                    thread.start();
                }
            }
        }
    }

    private void ShowDialog(String msg, Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("提示");
        builder.setMessage(msg);
        builder.setCancelable(false);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                isShow = true;
            }
        });
        dialog = builder.show();
    }

    private void initView() {
        caidan = findViewById(R.id.caidan);
        title = findViewById(R.id.title);
        mainChaxun = findViewById(R.id.main_chaxun);
        btnWangluo = findViewById(R.id.btn_wangluo);
    }
}