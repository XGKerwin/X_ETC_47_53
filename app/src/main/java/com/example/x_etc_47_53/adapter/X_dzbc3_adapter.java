package com.example.x_etc_47_53.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.x_etc_47_53.bean.Dzbc_color;
import com.example.x_etc_47_53.R;

import java.util.List;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2020/12/15 15:30
 */
public class X_dzbc3_adapter extends BaseAdapter {
    private String[] yangli;
    private String[] yinli;
    private List<Dzbc_color> colors;
    private Mydzbc3y mydzbc3y;
    private Mydzbc3n mydzbc3n;

    public interface Mydzbc3y{

        void setOnclick(int position);
    }

    public void setMydzbc3y(Mydzbc3y mydzbc3y){
        this.mydzbc3y = mydzbc3y;
    }

    public interface Mydzbc3n{

        void setOnclick(int position);
    }

    public void setMydzbc3n(Mydzbc3n mydzbc3n){
        this.mydzbc3n = mydzbc3n;
    }


    public X_dzbc3_adapter(String[] yangli, String[] yinli, List<Dzbc_color> colors) {
        this.yangli = yangli;
        this.yinli = yinli;
        this.colors = colors;
    }

    @Override
    public int getCount() {
        return yangli.length;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_dzbc_3, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.txtYang.setText(yangli[position]);
        holder.txtYin.setText(yinli[position]);


        final Dzbc_color color = colors.get(position);

        if (position%7==0 || position%7 == 6){
            holder.lin.setBackgroundResource(R.drawable.hui_xian1);
        }else {
            holder.lin.setBackgroundResource(R.drawable.bai_xian);
        }
        if (yinli[position].equals("")){
            holder.lin.setBackgroundResource(R.drawable.hui_xian1);
        }

        holder.lin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (yangli[position].equals("")){

                }else {
                    if (color.getColor1().equals("1")){
                        mydzbc3y.setOnclick(position);
                        holder.lin.setBackgroundResource(R.drawable.hong_xian);
                        color.setColor1("2");
                    }else {
                        mydzbc3n.setOnclick(position);
                        color.setColor1("1");
                        if (position%7==0 || position%7 == 6){
                            holder.lin.setBackgroundResource(R.drawable.hui_xian1);
                        }else {
                            holder.lin.setBackgroundResource(R.drawable.bai_xian);
                        }
                    }
                }
            }
        });


        return convertView;
    }

    class ViewHolder {
        private LinearLayout lin;
        private TextView txtYang;
        private TextView txtYin;

        public ViewHolder(View view) {
            lin = view.findViewById(R.id.lin);
            txtYang = view.findViewById(R.id.txt_yang);
            txtYin = view.findViewById(R.id.txt_yin);
        }
    }
}
