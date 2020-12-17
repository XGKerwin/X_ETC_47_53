package com.example.x_etc_47_53.net;

import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2020/12/15 10:22
 */

public class OKHttpTo extends Thread{

    private String Url = "http://192.168.155.205:8080/traffic/";
    private JSONObject jsonObject = new JSONObject();
    private int Time;
    private boolean isLoop;
    private OkHttpLo okHttpLo;

    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            if (msg.what == 1){
                okHttpLo.onResponse((JSONObject) msg.obj);
            }else if (msg.what == 2){
                okHttpLo.onFailure((IOException) msg.obj);
            }
            return false;
        }
    });

    public OKHttpTo setUrl(String url){
        this.Url += url;
        return this;
    }

    public OKHttpTo setJsonObject(String k,Object v){
        try {
            jsonObject.put(k,v);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return this;
    }

    public OKHttpTo setTime(int time){
        this.Time = time;
        return this;
    }

    public OKHttpTo setIsLoop(boolean isLoop){
        this.isLoop = isLoop;
        return this;
    }

    public OKHttpTo setOkHttpLo(OkHttpLo okHttpLo){
        this.okHttpLo = okHttpLo;
        return this;
    }

    @Override
    public void run() {
        do {
            OkHttpClient okHttpClient = new OkHttpClient();
            RequestBody requestBody = RequestBody.create(MediaType.get("application/json;charset=utf-8"),jsonObject.toString());
            Request request = new Request.Builder()
                    .url(Url)
                    .post(requestBody)
                    .build();
            okHttpClient.newCall(request)
                    .enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            Message message = new Message();
                            message.what = 2;
                            message.obj = e;
                            handler.sendMessage(message);
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            Message message = new Message();
                            message.what = 1;
                            try {
                                message.obj = new JSONObject(response.body().string());
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            handler.sendMessage(message);
                        }
                    });
            try {
                Thread.sleep(Time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }while (isLoop);
    }
}
