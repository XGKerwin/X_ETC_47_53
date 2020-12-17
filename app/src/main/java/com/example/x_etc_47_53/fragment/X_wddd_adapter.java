package com.example.x_etc_47_53.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.x_etc_47_53.R;
import com.example.x_etc_47_53.bean.DZBC_ku;

import java.util.List;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2020/12/15 20:33
 */
public class X_wddd_adapter extends BaseExpandableListAdapter {
    private List<DZBC_ku> dzbc_kus;

    public X_wddd_adapter(List<DZBC_ku> dzbc_kus) {
        this.dzbc_kus = dzbc_kus;
    }

    @Override
    public int getGroupCount() {
        return dzbc_kus.size();
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
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_wddd, null);
            holderFu = new ViewHolderFu(convertView);
            convertView.setTag(holderFu);
        } else {
            holderFu = (ViewHolderFu) convertView.getTag();
        }
        DZBC_ku dzbc_ku = dzbc_kus.get(groupPosition);
        holderFu.number.setText("订单编号：" + dzbc_ku.getName());
        holderFu.txtPiaojia.setText("票价：￥" + dzbc_ku.getPiaojia() + ".0");
        holderFu.txtLuxian.setText(dzbc_ku.getShangche() + "-----" + dzbc_ku.getXiache());
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
        DZBC_ku ku = dzbc_kus.get(groupPosition);
        String s = ku.getRiqi().replace(",", "\n");
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
