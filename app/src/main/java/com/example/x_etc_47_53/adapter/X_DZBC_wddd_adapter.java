package com.example.x_etc_47_53.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.x_etc_47_53.R;
import com.example.x_etc_47_53.bean.DZBC_wddd;

import java.util.List;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2020/12/15 19:41
 */
public class X_DZBC_wddd_adapter extends BaseExpandableListAdapter {

    private List<DZBC_wddd> dzbc_wddds;


    public X_DZBC_wddd_adapter(List<DZBC_wddd> dzbc_wddds) {
        this.dzbc_wddds = dzbc_wddds;
    }

    @Override
    public int getGroupCount() {
        return dzbc_wddds.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return dzbc_wddds.get(groupPosition).getData().size();
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
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_wddd, null);
            holderFu = new ViewHolderFu(convertView);
            convertView.setTag(holderFu);
        } else {
            holderFu = (ViewHolderFu) convertView.getTag();
        }
        DZBC_wddd dzbc_wddd = dzbc_wddds.get(groupPosition);
        holderFu.number.setText("订单编号：" + dzbc_wddd.getNum());
        holderFu.txtPiaojia.setText("票价：￥" + dzbc_wddd.getPrice() + ".0");
        holderFu.txtLuxian.setText(dzbc_wddd.getLine());
        if (isExpanded) {
            holderFu.img.setImageResource(R.drawable.xiajiantou);
        } else {
            holderFu.img.setImageResource(R.drawable.zuojiantou);
        }
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ViewHolderZi holderZi;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_wddd_zi, null);
            holderZi = new ViewHolderZi(convertView);
            convertView.setTag(holderZi);
        } else {
            holderZi = (ViewHolderZi) convertView.getTag();
        }

        List<String> strings = dzbc_wddds.get(groupPosition).getData();
        String s = "";
        for (int a = 0; a < strings.size(); a++) {
            s += strings.get(a);
            if (a != strings.size() - 1) {
                s += "\n";
            }
        }
        holderZi.riqi.setText(s);

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }



    class ViewHolderFu {
        private TextView txtLuxian;
        private TextView txtPiaojia;
        private TextView number;
        private ImageView img;

        public ViewHolderFu(View view) {
            txtLuxian = view.findViewById(R.id.txt_luxian);
            txtPiaojia = view.findViewById(R.id.txt_piaojia);
            number = view.findViewById(R.id.number);
            img = view.findViewById(R.id.img);
        }
    }

    class ViewHolderZi {
        private TextView riqi;

        public ViewHolderZi(View view) {
            riqi = view.findViewById(R.id.riqi);
        }
    }

}
