package com.example.x_etc_47_53.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.x_etc_47_53.R;

import java.util.List;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2020/12/15 16:42
 */
public class X_spinner2_adapter extends BaseAdapter {
    private List<String> strings;

    public X_spinner2_adapter(List<String> strings) {
        this.strings = strings;
    }

    @Override
    public int getCount() {
        return strings.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = View.inflate(parent.getContext(),android.R.layout.simple_list_item_1,null);
        TextView textView = view.findViewById(android.R.id.text1);
        String s = strings.get(position);
        textView.setText(s);
        textView.setTextSize(25);
        textView.setTextColor(Color.BLACK);
        return view;
    }

}
