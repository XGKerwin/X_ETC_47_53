package com.example.x_etc_47_53.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.x_etc_47_53.R;
import com.example.x_etc_47_53.adapter.X_DZBC_wddd_adapter;
import com.example.x_etc_47_53.bean.DZBC_wddd;
import com.example.x_etc_47_53.net.OKHttpTo;
import com.example.x_etc_47_53.net.OkHttpLo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2020/12/15 19:19
 */
public class Fragment_yzf extends Fragment {

    private ExpandableListView elistview;
    private List<DZBC_wddd> dzbc_wddds;
    private X_DZBC_wddd_adapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_yzf, null);
        initView(view);

        getOkHttp();

        return view;
    }

    private void getOkHttp() {
        if (dzbc_wddds == null){
            dzbc_wddds = new ArrayList<>();
        }else {
            dzbc_wddds.clear();
        }
        new OKHttpTo()
                .setUrl("get_bus_order")
                .setJsonObject("UserName","user1")
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        dzbc_wddds.addAll((Collection<? extends DZBC_wddd>) new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString(),
                                new TypeToken<List<DZBC_wddd>>(){}.getType()));
                        showList();
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();
    }

    private void showList() {
        adapter = new X_DZBC_wddd_adapter(dzbc_wddds);
        elistview.setAdapter(adapter);

    }

    private void initView(View view) {
        elistview = view.findViewById(R.id.elistview);
    }
}
