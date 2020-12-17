package com.example.x_etc_47_53;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.x_etc_47_53.Activity.Activity_Wl;
import com.example.x_etc_47_53.Activity.Activity_dzbc1;
import com.example.x_etc_47_53.Activity.Activity_lkfx;
import com.example.x_etc_47_53.Activity.Activity_lxdt;
import com.example.x_etc_47_53.Activity.Activity_rzcx;
import com.example.x_etc_47_53.Activity.Activity_web;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout dra;
    private ImageView caidan;
    private NavigationView nav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        caidan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dra.openDrawer(GravityCompat.START);
                nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                        switch (menuItem.getItemId()){
                            case R.id.dzbc:
                                Intent intent = new Intent(MainActivity.this, Activity_dzbc1.class);
                                startActivity(intent);
                                break;
                            case R.id.wl:
                                Intent intent1 = new Intent(MainActivity.this, Activity_Wl.class);
                                startActivity(intent1);
                                break;
                            case R.id.VEBVIEW:
                                Intent intent2 = new Intent(MainActivity.this, Activity_web.class);
                                startActivity(intent2);
                                break;
                            case R.id.lkfx:
                                Intent intent3 = new Intent(MainActivity.this, Activity_lkfx.class);
                                startActivity(intent3);
                                break;
                            case R.id.lxdt:
                                Intent intent4 = new Intent(MainActivity.this, Activity_lxdt.class);
                                startActivity(intent4);
                                break;
                            case R.id.rzcx:
                                Intent intent5 = new Intent(MainActivity.this, Activity_rzcx.class);
                                startActivity(intent5);
                                break;
                        }
                        dra.closeDrawer(GravityCompat.START);
                        return false;
                    }
                });
            }
        });



    }

    private void initView() {
        dra = findViewById(R.id.dra);
        caidan = findViewById(R.id.caidan);
        nav = findViewById(R.id.nav);
    }
}