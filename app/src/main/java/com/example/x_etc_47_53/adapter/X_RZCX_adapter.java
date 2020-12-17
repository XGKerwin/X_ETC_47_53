package com.example.x_etc_47_53.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.x_etc_47_53.bean.RZCX;
import com.example.x_etc_47_53.R;

import java.util.List;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2020/12/16 15:55
 */
public class X_RZCX_adapter extends BaseAdapter {
    private List<RZCX> rzcxList;

    public X_RZCX_adapter(List<RZCX> rzcxList) {
        this.rzcxList = rzcxList;
    }

    @Override
    public int getCount() {
        return rzcxList.size();
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
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_rzcx, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        RZCX rzcx = rzcxList.get(position);
        holder.txtWendu.setText(rzcx.getTemperature()+" ℃");
        holder.txtCo.setText(rzcx.getCo2()+" ppm");
        holder.txtShidu.setText(rzcx.getHumidity()+" %Rh");
        holder.txtPm.setText(rzcx.getPm25()+" ug/m3℃");
        holder.txtGuang.setText(rzcx.getIllumination()+" SI");
        holder.txtTime.setText(rzcx.getTime());
        return convertView;
    }

    class ViewHolder {
        private TextView txtCo;
        private TextView txtShidu;
        private TextView txtPm;
        private TextView txtGuang;
        private TextView txtWendu;
        private TextView txtTime;

        public ViewHolder(View view) {
            txtCo = view.findViewById(R.id.txt_co);
            txtShidu = view.findViewById(R.id.txt_shidu);
            txtPm = view.findViewById(R.id.txt_pm);
            txtGuang = view.findViewById(R.id.txt_guang);
            txtWendu = view.findViewById(R.id.txt_wendu);
            txtTime = view.findViewById(R.id.txt_time);
        }
    }
}
