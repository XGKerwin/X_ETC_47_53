package com.example.x_etc_47_53.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.x_etc_47_53.R;
import com.example.x_etc_47_53.bean.DZBC;

import java.util.List;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2020/12/15 10:20
 */
public class X_Edzbc1_adapter extends BaseExpandableListAdapter {
    private List<DZBC> dzbcList;
    private Mydzbc1 mydzbc1;

    public interface Mydzbc1 {

        void setOnclick(int groupPosition);
    }

    public void Mydzbc1(Mydzbc1 mydzbc1) {
        this.mydzbc1 = mydzbc1;
    }

    public X_Edzbc1_adapter(List<DZBC> dzbcList) {
        this.dzbcList = dzbcList;
    }

    @Override
    public int getGroupCount() {
        return dzbcList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        ViewHolderFu holderFu;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.lsit_dzbc1, null);
            holderFu = new ViewHolderFu(convertView);
            convertView.setTag(holderFu);
        } else {
            holderFu = (ViewHolderFu) convertView.getTag();
        }
        DZBC dzbc = dzbcList.get(groupPosition);
        holderFu.number.setText(dzbc.getId() + "号线");
        List<String> strings = dzbc.getBusline();
        holderFu.luxian.setText(strings.get(0) + "---" + strings.get(strings.size() - 1));
        holderFu.piaojia.setText("票价：￥" + dzbc.getFares() + ".0  里程：" + dzbc.getMileage() + ".0 km");
        holderFu.time1.setText(dzbc.getTime().split("~")[0]);
        holderFu.time2.setText(dzbc.getTime().split("~")[1]);
        if (isExpanded) {
            holderFu.imtJiantou.setImageResource(R.drawable.xiajiantou);
        } else {
            holderFu.imtJiantou.setImageResource(R.drawable.zuojiantou);
        }

        return convertView;
    }

    @Override
    public View getChildView(final int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ViewHolderZi holderZi;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.lsit_dzbc1_zi, null);
            holderZi = new ViewHolderZi(convertView);
            convertView.setTag(holderZi);
        } else {
            holderZi = (ViewHolderZi) convertView.getTag();
        }
        DZBC dzbc = dzbcList.get(groupPosition);
        List<String> strings = dzbcList.get(groupPosition).getBusline();
        String string = "";
        for (int a=0;a<strings.size();a++){
            string += strings.get(a);
            if (a!=strings.size()-1){
                string +="\r\n";
            }
        }
        holderZi.txt.setText(string);


        holderZi.lin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mydzbc1.setOnclick(groupPosition);
            }
        });

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }


    class ViewHolderFu {
        private TextView number;
        private TextView luxian;
        private TextView piaojia;
        private TextView time1;
        private TextView time2;
        private ImageView imtJiantou;

        public ViewHolderFu(View view) {
            number = view.findViewById(R.id.number);
            luxian = view.findViewById(R.id.luxian);
            piaojia = view.findViewById(R.id.piaojia);
            time1 = view.findViewById(R.id.time1);
            time2 = view.findViewById(R.id.time2);
            imtJiantou = view.findViewById(R.id.imt_jiantou);
        }
    }

    class ViewHolderZi {
        private LinearLayout lin;
        private TextView txt;

        public ViewHolderZi(View view) {
            lin = view.findViewById(R.id.lin);
            txt = view.findViewById(R.id.txt);
        }
    }

}
