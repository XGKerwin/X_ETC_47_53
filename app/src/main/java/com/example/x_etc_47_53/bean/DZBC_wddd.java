package com.example.x_etc_47_53.bean;

import java.util.List;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2020/12/15 19:39
 */
public class DZBC_wddd {

    /**
     *             "line": "光谷金融街——南湖商厦",
     *             "price": 8,
     *             "num": "a2",
     *             "data": [
     *                 "2019-07-09",
     *                 "2019-07-12",
     *                 "2019-12-12"
     *             ]
     */

    private String line,price,num;
    private List<String> data;

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }
}
