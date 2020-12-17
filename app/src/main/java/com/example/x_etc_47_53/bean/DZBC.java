package com.example.x_etc_47_53.bean;

import java.io.Serializable;
import java.util.List;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2020/12/15 10:36
 */
public class DZBC implements Serializable {

    /**
     *             "id": 1,
     *             "busline": [
     *                 "光谷金融街",
     *                 "戎军南路",
     *                 "长河公园",
     *                 "南湖商厦"
     *             ],
     *             "fares": 8,
     *             "mileage": 20,
     *             "time": "06:45~19:45"
     */

    private String id,fares,mileage,time;
    private List<String> busline;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFares() {
        return fares;
    }

    public void setFares(String fares) {
        this.fares = fares;
    }

    public String getMileage() {
        return mileage;
    }

    public void setMileage(String mileage) {
        this.mileage = mileage;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public List<String> getBusline() {
        return busline;
    }

    public void setBusline(List<String> busline) {
        this.busline = busline;
    }
}
