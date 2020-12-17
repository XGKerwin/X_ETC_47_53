package com.example.x_etc_47_53.Activity;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.Marker;
import com.example.x_etc_47_53.R;

public class Activity_lxdt extends AppCompatActivity {

    private MapView mapview;
    private ImageView imgBack;
    private ImageButton btn1geren;
    private ImageButton btn2changjing;
    private ImageButton btn3xiaoche;
    private ImageButton btn4chi;
    private TextView txtTishi;
    private AMap aMap;
    private int imgs[] = {R.drawable.marker_one, R.drawable.marker_second, R.drawable.marker_thrid, R.drawable.marker_forth};
    private PopupWindow popupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lxdt);
        initView();
        mapview.onCreate(savedInstanceState);
        aMap = mapview.getMap();
        amapinit();
        btn();

    }

    private void btn() {

    }

    private void amapinit() {
        aMap.getUiSettings().setMyLocationButtonEnabled(true);
        aMap.setOnMarkerClickListener(new AMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                if (marker.isInfoWindowShown()) {
                    marker.hideInfoWindow();
                } else {
                    marker.showInfoWindow();
                }
                return false;
            }
        });


    }

    private void initView() {
        mapview = findViewById(R.id.mapview);
        imgBack = findViewById(R.id.img_back);
        txtTishi = findViewById(R.id.txt_tishi);
        btn1geren = findViewById(R.id.btn_1geren);
        btn2changjing = findViewById(R.id.btn_2changjing);
        btn3xiaoche = findViewById(R.id.btn_3xiaoche);
        btn4chi = findViewById(R.id.btn_4chi);
    }
}