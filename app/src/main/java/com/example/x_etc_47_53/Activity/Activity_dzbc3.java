package com.example.x_etc_47_53.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.x_etc_47_53.R;
import com.example.x_etc_47_53.adapter.X_dzbc3_adapter;
import com.example.x_etc_47_53.bean.DZBC;
import com.example.x_etc_47_53.bean.Dzbc_color;
import com.example.x_etc_47_53.bean.Dzbc_time;

import java.util.ArrayList;
import java.util.List;

public class Activity_dzbc3 extends AppCompatActivity {
    private DZBC dzbc;
    private ImageView caidan;
    private TextView title;
    private GridView gridview;
    private Button btnXiayibu;
    private X_dzbc3_adapter adapter;
    private String[] yangli = {"", "", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "10", "11", "12",
            "13", "14", "15", "16", "17", "18", "19",
            "20", "21", "22", "23", "24", "25", "26",
            "27", "28", "29", "30", "31", "", ""};
    private String[] yinli = {"", "", "十七", "十八", "十九", "二十", "廿一",
            "廿二", "廿三", "廿四", "廿五", "廿六", "廿七", "廿八",
            "廿九", "三十", "冬月", "初二", "初三", "初四", "初五",
            "初六", "初七", "初八", "初九", "平安夜", "圣诞节", "十二",
            "十三", "十四", "十五", "十六", "十七", "", ""};

    private List<Dzbc_color> colors;

    private List<Dzbc_time> timeList;
    private TextView txtRiqi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dzbc3);
        initView();
        getinit();
        colors = new ArrayList<>();
        timeList = new ArrayList<>();

        dzbc = (DZBC) getIntent().getSerializableExtra("1");

        for (int i = 0; i < yangli.length; i++) {
            colors.add(new Dzbc_color("1"));
        }

        showlist();

        gettextview();

        btnXiayibu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (riqi.equals("")){
                    Toast.makeText(Activity_dzbc3.this,"日期不能为空",Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent = new Intent(Activity_dzbc3.this,Activity_dzbc4.class)
                            .putExtra("1",dzbc)
                            .putExtra("2",riqi);
                    startActivity(intent);
                }
            }
        });


    }

    private String riqi;

    private void gettextview() {
        riqi = "";
        for (int i = 0; i < timeList.size(); i++) {
            Dzbc_time time = timeList.get(i);
            if (riqi.equals("")) {
                riqi = time.getShijian();
            } else {
                riqi += "," + time.getShijian();
            }
        }
        txtRiqi.setText(riqi);
    }

    private void showlist() {
        adapter = new X_dzbc3_adapter(yangli, yinli, colors);
        gridview.setAdapter(adapter);

        adapter.setMydzbc3y(new X_dzbc3_adapter.Mydzbc3y() {
            @Override
            public void setOnclick(int position) {
                getTime("2020-12-" + yangli[position]);
            }
        });


        adapter.setMydzbc3n(new X_dzbc3_adapter.Mydzbc3n() {
            @Override
            public void setOnclick(int position) {
                getremove("2020-12-" + yangli[position]);
            }
        });
    }

    private void getremove(String shanchu) {
        for (int i = 0; i < timeList.size(); i++) {
            Dzbc_time time = timeList.get(i);
            if (time.getShijian().equals(shanchu)) {
                timeList.remove(i);
            }
        }
        gettextview();
    }

    private void getTime(String add) {
        timeList.add(new Dzbc_time(add));
        gettextview();
    }

    private void getinit() {
        title.setText("定制班车");
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
        gridview = findViewById(R.id.gridview);
        btnXiayibu = findViewById(R.id.btn_xiayibu);
        txtRiqi = findViewById(R.id.txt_riqi);
    }
}