package com.example.x_etc_47_53.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.x_etc_47_53.R;
import com.example.x_etc_47_53.adapter.X_Edzbc1_adapter;
import com.example.x_etc_47_53.bean.DZBC;
import com.example.x_etc_47_53.net.OKHttpTo;
import com.example.x_etc_47_53.net.OkHttpLo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Activity_dzbc1 extends AppCompatActivity {

    private ImageView caidan;
    private TextView title;
    private ImageView biaotiEwm;
    private TextView biaotiWddd;
    private ExpandableListView Elistview;
    private X_Edzbc1_adapter adapter;
    private List<DZBC> dzbcList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dzbc1);
        initView();
        init();

        Elistview.setGroupIndicator(null);

        getOkHttp();

        biaotiWddd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_dzbc1.this,Activity_dzbc_wddd.class);
                startActivity(intent);
            }
        });


    }

    private void getOkHttp() {
        if (dzbcList == null){
            dzbcList = new ArrayList<>();
        }else {
            dzbcList.clear();
        }

        new OKHttpTo()
                .setUrl("get_bus_info")
                .setJsonObject("UserName","user1")
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        dzbcList.addAll((Collection<? extends DZBC>) new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString(),
                                new TypeToken<List<DZBC>>(){}.getType()));

                        Log.i("vvvvvvvvvvv", "onResponse: "+dzbcList.size());
                        showList();
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();

    }

    private void showList() {
        adapter = new X_Edzbc1_adapter(dzbcList);
        Elistview.setAdapter(adapter);

        adapter.Mydzbc1(new X_Edzbc1_adapter.Mydzbc1() {
            @Override
            public void setOnclick(int groupPosition) {
                Intent intent = new Intent(Activity_dzbc1.this,Activity_dzbc2.class)
                        .putExtra("1", (Serializable) dzbcList.get(groupPosition));
                startActivity(intent);
            }
        });
    }

    private void init() {
        title.setText("定制班车");
        biaotiEwm.setVisibility(View.VISIBLE);
        biaotiWddd.setVisibility(View.VISIBLE);
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
        biaotiEwm = findViewById(R.id.biaoti_ewm);
        biaotiWddd = findViewById(R.id.biaoti_wddd);
        Elistview = findViewById(R.id.Elistview);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        biaotiWddd.setVisibility(View.GONE);
        biaotiEwm.setVisibility(View.GONE);
    }
}