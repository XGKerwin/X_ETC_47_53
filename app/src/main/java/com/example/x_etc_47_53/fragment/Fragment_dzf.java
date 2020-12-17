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
import com.example.x_etc_47_53.bean.DZBC_ku;

import org.litepal.LitePal;

import java.util.List;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2020/12/15 19:15
 */
public class Fragment_dzf extends Fragment {

    private ExpandableListView elistview;
    private List<DZBC_ku> dzbc_kus;
    private X_wddd_adapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_yzf, null);
        initView(view);
        dzbc_kus = LitePal.findAll(DZBC_ku.class);


        show();


        return view;
    }

    private void show() {
        if (dzbc_kus.size()==0){
            return;
        }
        adapter = new X_wddd_adapter(dzbc_kus);
        elistview.setAdapter(adapter);


    }

    private void initView(View view) {
        elistview = view.findViewById(R.id.elistview);
    }
}
