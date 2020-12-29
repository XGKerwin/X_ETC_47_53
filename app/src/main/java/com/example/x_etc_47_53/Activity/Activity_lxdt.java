package com.example.x_etc_47_53.Activity;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.example.x_etc_47_53.R;
import com.example.x_etc_47_53.net.OKHttpTo;
import com.example.x_etc_47_53.net.OkHttpLo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

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
    private List<LXDT> lxdts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lxdt);
        initView();

        mapview.onCreate(savedInstanceState);
        aMap = mapview.getMap();

        amapinit();
        btn();
        btnpop();

    }

    private void btn() {

        btn3xiaoche.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (aMap.getMapScreenMarkers().size() == 0){
                    getOkHttp();
                    txtTishi.setText("1,2,3,4号小车地图标记已完成");
                } else {
                    List<Marker> list = aMap.getMapScreenMarkers();     //使显示出来的定位坐标隐藏
                    for (int i=0;i< list.size();i++){
                        list.get(i).remove();
                    }
                    txtTishi.setText("小车地图信息");
                }
            }
        });

        btn2changjing.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (popupWindow.isShowing()){
                    popupWindow.dismiss();
                } else {
                    popupWindow.showAsDropDown(btn2changjing,-(int)(btn2changjing.getMeasuredWidth()*getResources().getDisplayMetrics().density+120),
                            -btn2changjing.getMeasuredHeight());
                }
            }
        });

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btn1geren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){

            }
        });

        btn4chi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });




    }


    private void getOkHttp(){
        new OKHttpTo()
                .setUrl("get_site_data").setJsonObject("UserName","user1")
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        lxdts = new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString(),
                                new TypeToken<List<LXDT>>(){}.getType());
                        Log.d("aaaaa","----"+lxdts.size());
                        for (int i =0 ; i < 4; i++) {
                            LXDT lxdt = lxdts.get(i);
                            MarkerOptions markerOptions = new MarkerOptions();      //创建获取坐标位置对象
                            markerOptions.position(new LatLng(Double.parseDouble(lxdt.getLongitude()), Double.parseDouble(lxdt.getLatitude())));
                            markerOptions.title(lxdt.getSite()).snippet(lxdt.getSite()+": "+lxdt.getLongitude()+lxdt.getLatitude());    //输入坐标
                            markerOptions.draggable(true);      //设置MArker可拖动
                            markerOptions.icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory
                                    .decodeResource(getResources(),imgs[i])));      //设置显示图片
                            markerOptions.setFlat(true);              //将marker 设置为贴地显示，可以双指下拉地图查看效果
                            aMap.addMarker(markerOptions);            //通过amap显示出来
                        }
                        aMap.moveCamera(CameraUpdateFactory.changeLatLng(new LatLng(Float.parseFloat(lxdts.get(0).getLongitude())
                                , Float.parseFloat(lxdts.get(0).getLatitude()))));
                        aMap.moveCamera(CameraUpdateFactory.zoomTo(13));
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();
    }

    private boolean isjiaotong=false;

    private void btnpop() {
        ViewHolder holder;
        View view = LayoutInflater.from(this).inflate(R.layout.list_ditu,null);
        holder = new ViewHolder(view);
        view.setTag(holder);

        holder.daohang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aMap.setMapType(AMap.MAP_TYPE_NAVI);            // 设置导航地图模式，aMap是地图控制器对象。
                txtTishi.setText("已切换为导航视图");
                if (popupWindow != null && popupWindow.isShowing())popupWindow.dismiss();
            }
        });

        holder.yejing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aMap.setMapType(AMap.MAP_TYPE_NIGHT);
                txtTishi.setText("已切换为夜景视图");
                if (popupWindow!=null&&popupWindow.isShowing())popupWindow.dismiss();
            }
        });

        holder.biaozhun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aMap.setMapType(AMap.MAP_TYPE_NORMAL);
                txtTishi.setText("已切换为标准视图");
                if (popupWindow!=null&&popupWindow.isShowing())popupWindow.dismiss();
            }
        });

        holder.weixing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aMap.setMapType(AMap.MAP_TYPE_SATELLITE);
                txtTishi.setText("已切换为卫星视图");
                if (popupWindow!=null&&popupWindow.isShowing())popupWindow.dismiss();
            }
        });

        holder.jiaotong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isjiaotong){
                    isjiaotong = true;
                    aMap.setTrafficEnabled(true);   //显示实时路况图层，aMap是地图控制器对象。
                    txtTishi.setText("已开启交通试图");
                }else {
                    isjiaotong = false;
                    aMap.setTrafficEnabled(false);   //显示实时路况图层，aMap是地图控制器对象。
                    txtTishi.setText("已关闭交通视图");
                }
                if (popupWindow!=null&&popupWindow.isShowing())popupWindow.dismiss();
            }
        });


        popupWindow = new PopupWindow(view, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
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
                return true;
            }
        });
    }

    static class ViewHolder{
        private TextView daohang,yejing,biaozhun,weixing,jiaotong;

        public ViewHolder(View view) {
            daohang = view.findViewById(R.id.list_dhst);
            yejing = view.findViewById(R.id.list_yjst);
            biaozhun = view.findViewById(R.id.list_bzst);
            weixing = view.findViewById(R.id.list_wxst);
            jiaotong = view.findViewById(R.id.list_jtst);
        }
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
    @Override
    protected void onResume() {
        super.onResume();
        mapview.onResume();
    }

    @Override
    protected void onPause(){
        super.onPause();
        mapview.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        mapview.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        mapview.onDestroy();
    }




}