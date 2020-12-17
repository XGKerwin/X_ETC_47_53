package com.example.x_etc_47_53.util;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.x_etc_47_53.R;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2020/12/16 15:38
 */
public class MyListView extends ListView {

    private View convertView;
    private ViewHolder holder;
    private int headerHeight;

    public MyListView(Context context) {
        super(context);
        init();
    }

    public MyListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        convertView = View.inflate(getContext(), R.layout.mylistview, null);
        holder = new ViewHolder(convertView);
        convertView.measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED);
        headerHeight = convertView.getMeasuredHeight();
        convertView.setPadding(0, -headerHeight, 0, 0);
        addHeaderView(convertView);
    }

    private int downY, moveY;
    private boolean isUpdate;
    public static final int DOWN_REFRESH = 1;
    public static final int RELEASE_REFRESH = 2;
    public static final int REFRESHING = 3;
    private int currentStateHeader = DOWN_REFRESH;


    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downY = (int) ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                if (!isUpdate){
                    moveY = (int) ev.getY();
                    int instance = moveY - downY;
                    holder.img_xiala.setVisibility(VISIBLE);
                    if (instance > 0 && getFirstVisiblePosition() == 0 && currentStateHeader != REFRESHING) {
                        int topPadding = -headerHeight + instance;
                        if (topPadding < 20) {
                            currentStateHeader = DOWN_REFRESH;
                            holder.txt_tishi.setText("下拉刷新");
                            holder.img_xiala.animate().rotation(0).setDuration(500);
                        } else if (topPadding > 20) {
                            currentStateHeader = RELEASE_REFRESH;
                            holder.txt_tishi.setText("即将刷新");
                            holder.img_xiala.animate().rotation(180).setDuration(500);
                        }
                        convertView.setPadding(0, instance, 0, 0);
                    }else {
                        currentStateHeader=-1;
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                if (currentStateHeader == RELEASE_REFRESH) {
                    currentStateHeader = REFRESHING;
                    convertView.setPadding(0, 0, 0, 0);
                    holder.txt_tishi.setText("正在刷新");
                    isUpdate = true;
                    holder.img_xiala.setVisibility(GONE);
                    holder.pro.setVisibility(VISIBLE);
                    if (onRefreshListener != null) {
                        onRefreshListener.refresh();
                    }
                } else if (currentStateHeader == DOWN_REFRESH) {
                    convertView.setPadding(0, -headerHeight, 0, 0);
                }
                break;
        }
        return super.onTouchEvent(ev);
    }

    public interface OnRefreshListener {
        void refresh();
    }

    private OnRefreshListener onRefreshListener;

    public void setOnRefreshListener(OnRefreshListener onRefreshListener) {
        this.onRefreshListener = onRefreshListener;
    }


    public void finishRefresh() {
        holder.txt_tishi.setText("刷新完成");
        holder.pro.setVisibility(GONE);
        holder.img_xiala.setVisibility(VISIBLE);
        holder.img_xiala.setImageResource(R.drawable.shuxinwanche);
        holder.img_xiala.animate().rotation(0).setDuration(100);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                convertView.setPadding(0, -headerHeight, 0, 0);
                currentStateHeader = DOWN_REFRESH;
                holder.img_xiala.setImageResource(R.drawable.jiantou);
                isUpdate = false;
            }
        }, 2000);
    }

    static class ViewHolder{
        private TextView txt_tishi;
        private ImageView img_xiala;
        private ProgressBar pro;

        public ViewHolder(View view) {
            txt_tishi = view.findViewById(R.id.txt_tishi);
            img_xiala = view.findViewById(R.id.img_xiala);
            pro = view.findViewById(R.id.pro);
        }
    }

}
