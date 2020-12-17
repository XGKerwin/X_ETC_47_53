package com.example.x_etc_47_53.Activity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.x_etc_47_53.R;
import com.example.x_etc_47_53.bean.LKFX;
import com.example.x_etc_47_53.net.OKHttpTo;
import com.example.x_etc_47_53.net.OkHttpLo;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Activity_lkfx extends AppCompatActivity {

    private ImageView caidan;
    private TextView title;
    private LinearLayout linXueyuan;
    private LinearLayout linLianxiang;
    private LinearLayout linYiyuan;
    private LinearLayout linXingfu;
    private LinearLayout linHuanchengkuaisu;
    private LinearLayout linHuanchenggaosu;
    private LinearLayout linTingchechang;
    private BarChart barchart;
    private BarDataSet dataSet1, dataSet2, dataSet3, dataSet4, dataSet5, dataSet6, dataSet7;

    private Map<String, List<LKFX>> map;
    private String[] arr = {"周一","周二","周三","周四","周五","周六","周日"};

    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            map.clear();
            for (int i=0;i<7;i++){
                getOkhttp(i);
            }
            return false;
        }
    });

    private void getOkhttp(final int i) {
        new OKHttpTo()
                .setUrl("get_road_status")
                .setJsonObject("UserName","user1")
                .setJsonObject("RoadId",0)
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        List<LKFX> lkfxes = new Gson().fromJson(jsonObject.optString("ROWS_DETAIL").toString(),
                                new TypeToken<List<LKFX>>(){}.getType());
                        map.put(arr[i],lkfxes);
                        if (map.size()==7){
                            setbar();
                        }
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();

    }

    private void setbar() {
        for (int i = 0; i < map.size(); i++) {
            List<BarEntry> barEntries = new ArrayList<>();
            List<LKFX> lkcxes = map.get(arr[i]);
            for (int j = 0; j < lkcxes.size(); j++) {
                barEntries.add(new BarEntry(j, lkcxes.get(j).getState()));
            }
            switch (i){
                case 0:
                    if (dataSet1 == null){
                        dataSet1 = new BarDataSet(barEntries,arr[i]);
                    }else{
                        dataSet1.setValues(barEntries);
                    }
                    break;
                case 1:
                    if (dataSet2 == null){
                        dataSet2 = new BarDataSet(barEntries,arr[i]);
                    }else {
                        dataSet2.setValues(barEntries);
                    }
                    break;
                case 2:
                    if (dataSet3 == null){
                        dataSet3 = new BarDataSet(barEntries,arr[i]);
                    }else {
                        dataSet3.setValues(barEntries);
                    }
                    break;
                case 3:
                    if (dataSet4 == null){
                        dataSet4 = new BarDataSet(barEntries,arr[i]);
                    }else {
                        dataSet4.setValues(barEntries);
                    }
                    break;
                case 4:
                    if (dataSet5 == null){
                        dataSet5 = new BarDataSet(barEntries,arr[i]);
                    }else {
                        dataSet5.setValues(barEntries);
                    }
                    break;
                case 5:
                    if (dataSet6 == null){
                        dataSet6 = new BarDataSet(barEntries,arr[i]);
                    }else {
                        dataSet6.setValues(barEntries);
                    }
                    break;
                case 6:
                    if (dataSet7 == null){
                        dataSet7 = new BarDataSet(barEntries,arr[i]);
                    }else {
                        dataSet7.setValues(barEntries);
                    }
                    break;
            }
        }
        dataSet1.setColor(Color.parseColor("#AC2326"));
        dataSet2.setColor(Color.parseColor("#243441"));
        dataSet3.setColor(Color.parseColor("#4D8991"));
        dataSet4.setColor(Color.parseColor("#BF6A51"));
        dataSet5.setColor(Color.parseColor("#7CB497"));
        dataSet6.setColor(Color.parseColor("#5F8A6D"));
        dataSet7.setColor(Color.parseColor("#B46F1D"));
        BarData data = new BarData(dataSet1,dataSet2,dataSet3,dataSet4,dataSet5,dataSet6,dataSet7);
        data.setBarWidth(0.08f);
        data.setDrawValues(false);
        data.setValueTextColor(Color.BLACK);
        barchart.setData(data);
        barchart.getDescription().setEnabled(false);
        barchart.getLegend().setEnabled(false);
        barchart.groupBars(-0.5f, 0.3f, 0.02f);
        barchart.setTouchEnabled(false);
        barchart.invalidate();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lkfx);
        initView();
        getinit();

        btn();

        map = new HashMap<>();

        new Thread(new Runnable() {
            @Override
            public void run() {
                do {
                    handler.sendEmptyMessage(0);
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }while (true);
            }
        }).start();


    }

    private void btn() {
        linXueyuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataSet1.setVisible(!dataSet1.isVisible());
                barchart.invalidate();
            }
        });

        linLianxiang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataSet2.setVisible(!dataSet2.isVisible());
                barchart.invalidate();
            }
        });

        linYiyuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataSet3.setVisible(!dataSet3.isVisible());
                barchart.invalidate();
            }
        });

        linXingfu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataSet4.setVisible(!dataSet4.isVisible());
                barchart.invalidate();
            }
        });

        linHuanchengkuaisu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataSet5.setVisible(!dataSet5.isVisible());
                barchart.invalidate();
            }
        });

        linHuanchenggaosu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataSet6.setVisible(!dataSet6.isVisible());
                barchart.invalidate();
            }
        });

        linTingchechang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataSet7.setVisible(!dataSet7.isVisible());
                barchart.invalidate();
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
        title.setText("路况分析");

        YAxis yAxis = barchart.getAxisLeft();
        List<String> yleft = new ArrayList<>();
        yleft.add("");
        yleft.add("畅通");
        yleft.add("缓行");
        yleft.add("一般拥堵");
        yleft.add("中度拥堵");
        yleft.add("严重拥堵");
        yleft.add("");
        yAxis.setAxisMaximum(6);
        yAxis.setAxisMinimum(0);
        yAxis.setValueFormatter(new IndexAxisValueFormatter(yleft));
        yAxis.setGranularity(1f);
        yAxis.setTextSize(20f);
        yAxis.setDrawGridLines(false);
        YAxis yAxis1 = barchart.getAxisRight();
        yAxis1.setTextSize(20f);
        yAxis1.setLabelCount(5);
        yAxis1.setAxisMinimum(0);


        XAxis xAxis = barchart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(arr));
        xAxis.setLabelRotationAngle(0);
        xAxis.setTextSize(20);

    }

    private void initView() {
        caidan = findViewById(R.id.caidan);
        title = findViewById(R.id.title);
        linXueyuan = findViewById(R.id.lin_xueyuan);
        linLianxiang = findViewById(R.id.lin_lianxiang);
        linYiyuan = findViewById(R.id.lin_yiyuan);
        linXingfu = findViewById(R.id.lin_xingfu);
        linHuanchengkuaisu = findViewById(R.id.lin_huanchengkuaisu);
        linHuanchenggaosu = findViewById(R.id.lin_huanchenggaosu);
        linTingchechang = findViewById(R.id.lin_tingchechang);
        barchart = findViewById(R.id.barchart);
    }
}