package com.example.x_etc_47_53.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.x_etc_47_53.R;
import com.example.x_etc_47_53.fragment.Fragment_dzf;
import com.example.x_etc_47_53.fragment.Fragment_yzf;

public class Activity_dzbc_wddd extends AppCompatActivity {

    private ExpandableListView Elistview;
    private FragmentTransaction fragmentTransaction;
    private TextView btnDaizhifu;
    private TextView btnYizhifu;
    private FrameLayout dzbcFragment;
    private ImageView caidan;
    private TextView title;
    private ImageView biaotiEwm;
    private TextView biaotiWddd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dzbc_wddd);
        initView();

        caidan.setImageResource(R.drawable.back);
        caidan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        title.setText("我的订单");

        btnDaizhifu.setBackgroundResource(R.drawable.xiahong);
        btnYizhifu.setBackgroundResource(0);
        Fragment1(new Fragment_dzf());


        btn();


    }

    private void btn() {

        btnDaizhifu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnDaizhifu.setBackgroundResource(R.drawable.xiahong);
                btnYizhifu.setBackgroundResource(0);
                Fragment1(new Fragment_dzf());
            }
        });

        btnYizhifu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnYizhifu.setBackgroundResource(R.drawable.xiahong);
                btnDaizhifu.setBackgroundResource(0);
                Fragment1(new Fragment_yzf());
            }
        });

    }

    private void Fragment1(Fragment fragment) {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.dzbc_fragment, fragment).commit();
    }

    private void initView() {
        Elistview = findViewById(R.id.Elistview);
        btnDaizhifu = findViewById(R.id.btn_daizhifu);
        btnYizhifu = findViewById(R.id.btn_yizhifu);
        dzbcFragment = findViewById(R.id.dzbc_fragment);
        caidan = findViewById(R.id.caidan);
        title = findViewById(R.id.title);
        biaotiEwm = findViewById(R.id.biaoti_ewm);
        biaotiWddd = findViewById(R.id.biaoti_wddd);
    }
}