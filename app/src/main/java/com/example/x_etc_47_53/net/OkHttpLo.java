package com.example.x_etc_47_53.net;

import org.json.JSONObject;

import java.io.IOException;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2020/12/15 10:23
 */
public interface OkHttpLo {
    void onResponse(JSONObject jsonObject);

    void onFailure(IOException obj);
}
