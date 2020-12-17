package com.example.x_etc_47_53.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.x_etc_47_53.R;
import com.example.x_etc_47_53.bean.DZBC;

import java.util.List;

public class Activity_dzbc2 extends AppCompatActivity {

    private int pos;
    private TextView luxian;
    private TextView piaojia;
    private TextView licheng;
    private Button btnXiayibu;
    private DZBC dzbc;
    private ImageView caidan;
    private TextView title;
    private ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dzbc2);
        initView();
        caidan.setImageResource(R.drawable.back);
        caidan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        title.setText("定制班车");
        dzbc = (DZBC) getIntent().getSerializableExtra("1");
        List<String> strings = dzbc.getBusline();

        luxian.setText(strings.get(0) + "------" + strings.get(strings.size()-1));
        piaojia.setText("票价：￥" + dzbc.getFares() + ".0");
        licheng.setText("里程：" + dzbc.getMileage() + ".0km");

        switch (dzbc.getId()) {
            case "1":
                img.setImageResource(R.drawable.ditu);
                break;
            case "2":
                img.setImageResource(R.drawable.ditu2);
                break;
        }

        btnXiayibu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_dzbc2.this,Activity_dzbc3.class)
                        .putExtra("1",dzbc);
                startActivity(intent);
            }
        });

    }

    private void initView() {
        luxian = findViewById(R.id.luxian);
        piaojia = findViewById(R.id.piaojia);
        licheng = findViewById(R.id.licheng);
        btnXiayibu = findViewById(R.id.btn_xiayibu);
        caidan = findViewById(R.id.caidan);
        title = findViewById(R.id.title);
        img = findViewById(R.id.img);
    }
}