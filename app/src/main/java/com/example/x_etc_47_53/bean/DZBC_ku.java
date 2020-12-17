package com.example.x_etc_47_53.bean;

import org.litepal.crud.LitePalSupport;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2020/12/15 17:40
 */
public class DZBC_ku extends LitePalSupport {

    private String name,tel,shangche,xiache,riqi,piaojia;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getShangche() {
        return shangche;
    }

    public void setShangche(String shangche) {
        this.shangche = shangche;
    }

    public String getXiache() {
        return xiache;
    }

    public void setXiache(String xiache) {
        this.xiache = xiache;
    }

    public String getRiqi() {
        return riqi;
    }

    public void setRiqi(String riqi) {
        this.riqi = riqi;
    }

    public String getPiaojia() {
        return piaojia;
    }

    public void setPiaojia(String piaojia) {
        this.piaojia = piaojia;
    }
}
