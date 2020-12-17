package com.example.x_etc_47_53.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.x_etc_47_53.R;
import com.example.x_etc_47_53.bean.DZBC;
import com.example.x_etc_47_53.adapter.X_spinner1_adapter;
import com.example.x_etc_47_53.adapter.X_spinner2_adapter;

import java.util.List;

public class Activity_dzbc4 extends AppCompatActivity {

    private DZBC dzbc;
    private String riqi;
    private ImageView caidan;
    private TextView title;
    private TextView luxian;
    private EditText edName;
    private EditText edTel;
    private Spinner spinner1;
    private Spinner spinner2;
    private X_spinner1_adapter adapter;
    private X_spinner2_adapter adapter2;
    private List<String> strings;
    private String shangche;
    private String xiache;
    private Button btnXiayibu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dzbc4);
        initView();
        getinit();

        shangche = strings.get(0);
        xiache = strings.get(0);

        showSpinner();
        spinner();

        btnXiayibu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edName.getText().toString();
                String tel = edTel.getText().toString();
                if (name.equals("")){
                    Toast.makeText(Activity_dzbc4.this,"请输入乘客姓名",Toast.LENGTH_SHORT).show();
                }else if (tel.equals("")){
                    Toast.makeText(Activity_dzbc4.this,"请输入手机号",Toast.LENGTH_SHORT).show();
                }else if (shangche.equals(xiache)){
                    Toast.makeText(Activity_dzbc4.this,"上车地点下车地点不能一致",Toast.LENGTH_SHORT).show();
                }else if (tel.length()==11){
                    Intent intent = new Intent(Activity_dzbc4.this,Activity_dzbc5.class)
                            .putExtra("1",dzbc)
                            .putExtra("2",shangche)
                            .putExtra("3",xiache)
                            .putExtra("4",name)
                            .putExtra("5",tel)
                            .putExtra("6",riqi);
                    startActivity(intent);
                }else {
                    Toast.makeText(Activity_dzbc4.this,"请输入正确的手机号",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void spinner() {
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                shangche = strings.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                xiache = strings.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void showSpinner() {
        adapter = new X_spinner1_adapter(strings);
        spinner1.setAdapter(adapter);

        adapter2 = new X_spinner2_adapter(strings);
        spinner2.setAdapter(adapter);

    }

    private void getinit() {
        riqi = getIntent().getStringExtra("2");
        dzbc = (DZBC) getIntent().getSerializableExtra("1");

        title.setText("定制班车");
        caidan.setImageResource(R.drawable.back);
        caidan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        strings = dzbc.getBusline();
        luxian.setText(strings.get(0) + "------" + strings.get(strings.size() - 1));


    }

    private void initView() {
        caidan = findViewById(R.id.caidan);
        title = findViewById(R.id.title);
        luxian = findViewById(R.id.luxian);
        edName = findViewById(R.id.ed_name);
        edTel = findViewById(R.id.ed_tel);
        spinner1 = findViewById(R.id.spinner1);
        spinner2 = findViewById(R.id.spinner2);
        btnXiayibu = findViewById(R.id.btn_xiayibu);
    }
}