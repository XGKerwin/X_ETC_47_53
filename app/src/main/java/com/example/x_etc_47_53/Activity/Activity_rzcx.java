package com.example.x_etc_47_53.Activity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.x_etc_47_53.R;
import com.example.x_etc_47_53.adapter.X_RZCX_adapter;
import com.example.x_etc_47_53.bean.RZCX;
import com.example.x_etc_47_53.net.OKHttpTo;
import com.example.x_etc_47_53.net.OkHttpLo;
import com.example.x_etc_47_53.util.MyListView;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Activity_rzcx extends AppCompatActivity {

    private ImageView caidan;
    private TextView title;
    private MyListView listview;
    private List<RZCX> rzcxList;
    private X_RZCX_adapter adapter;
    private int size = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rzcx);
        initView();
        getinit();
        rzcxList = new ArrayList<>();

        getfor();

    }

    private void getfor() {
        for (int i=0;i<4;i++){
            getOkHttp(1);
        }
    }

    private void getOkHttp(final int il) {
        new OKHttpTo()
                .setUrl("get_all_sense")
                .setJsonObject("UserName","user1")
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        getTime();
                        RZCX rzcx = new Gson().fromJson(jsonObject.toString(),RZCX.class);
                        rzcx.setTime(time);
                        rzcxList.add(0,rzcx);
                        if (il == 1){
                            if (rzcxList.size()==4){
                                adapter = new X_RZCX_adapter(rzcxList);
                                listview.setAdapter(adapter);
                                listview.setOnRefreshListener(new MyListView.OnRefreshListener() {
                                    @Override
                                    public void refresh() {
                                        new Handler().postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                for (int i=0;i<2;i++){
                                                    getOkHttp(2);
                                                }
                                            }
                                        },2000);
                                    }
                                });
                            }
                        }else{
                            if (rzcxList.size() == size+2){
                                listview.finishRefresh();     //刷新成功
                                adapter.notifyDataSetChanged();
                                size += 2;
                                Toast.makeText(Activity_rzcx.this,"刷新成功",Toast.LENGTH_SHORT).show();
                            }
                        }

                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();


    }

    private String time;
    private void getTime() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        time = format.format(date);
    }

    private void getinit() {
        title.setText("日志查询");
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
        listview = findViewById(R.id.listview);
    }
}