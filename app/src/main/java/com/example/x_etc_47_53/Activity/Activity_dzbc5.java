package com.example.x_etc_47_53.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.x_etc_47_53.R;
import com.example.x_etc_47_53.bean.DZBC;
import com.example.x_etc_47_53.bean.DZBC_ku;

import java.util.List;

public class Activity_dzbc5 extends AppCompatActivity {

    private ImageView caidan;
    private TextView title;
    private ImageView biaotiEwm;
    private TextView biaotiWddd;
    private TextView luxian;
    private TextView txtName;
    private TextView txtTel;
    private TextView txtShangche;
    private TextView txtXiache;
    private TextView txtRiqi;
    private Button btnTijiao;

    private DZBC dzbc;
    private String shangche;
    private String xiache;
    private String name;
    private String tel;
    private String riqi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dzbc5);
        initView();
        getinit();

        List<String> strings = dzbc.getBusline();

        luxian.setText(strings.get(0)+"------"+strings.get(strings.size()-1));
        txtName.setText("乘客姓名："+name);
        txtTel.setText("手机号码："+tel);
        txtShangche.setText("上车地点："+shangche);
        txtXiache.setText("下车地点："+ xiache);
        txtRiqi.setText("乘车日期："+riqi);

        btnTijiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DZBC_ku dzbc_ku = new DZBC_ku();
                dzbc_ku.setName(name);
                dzbc_ku.setShangche(shangche);
                dzbc_ku.setXiache(xiache);
                dzbc_ku.setPiaojia(dzbc.getFares());
                dzbc_ku.setRiqi(riqi);
                dzbc_ku.setTel(tel);
                if (dzbc_ku.save()){
                    Intent intent = new Intent(Activity_dzbc5.this, Activity_dzbc1.class);
                    intent.setFlags(intent.FLAG_ACTIVITY_CLEAR_TASK|intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);

                    Toast.makeText(Activity_dzbc5.this,"提交成功",Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(Activity_dzbc5.this,"提交失败",Toast.LENGTH_SHORT).show();
                }
            }
        });





    }


    private void getinit() {

        /**
         * .putExtra("1",dzbc)
         * .putExtra("2",shangche)
         * .putExtra("3",xiache)
         * .putExtra("4",name)
         * .putExtra("5",tel)
         * .putExtra("6",riqi);
         */

        dzbc = (DZBC) getIntent().getSerializableExtra("1");
        shangche = getIntent().getStringExtra("2");
        xiache = getIntent().getStringExtra("3");
        name = getIntent().getStringExtra("4");
        tel = getIntent().getStringExtra("5");
        riqi = getIntent().getStringExtra("6");

        caidan.setImageResource(R.drawable.back);
        caidan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        title.setText("确定订单");



    }

    private void initView() {
        caidan = findViewById(R.id.caidan);
        title = findViewById(R.id.title);
        biaotiEwm = findViewById(R.id.biaoti_ewm);
        biaotiWddd = findViewById(R.id.biaoti_wddd);
        luxian = findViewById(R.id.luxian);
        txtName = findViewById(R.id.txt_name);
        txtTel = findViewById(R.id.txt_tel);
        txtShangche = findViewById(R.id.txt_shangche);
        txtXiache = findViewById(R.id.txt_xiache);
        txtRiqi = findViewById(R.id.txt_riqi);
        btnTijiao = findViewById(R.id.btn_tijiao);
    }
}