package com.example.x_etc_47_53.bean;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2020/12/16 15:42
 */
public class RZCX {
    /**
     *     "temperature": 34,
     *     "humidity": 11,
     *     "illumination": 3873,
     *     "co2": 6462,
     *     "pm25": 213,
     *     "RESULT": "S"
     */

    private int temperature,humidity,illumination,co2,pm25;
    private String time;

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public int getIllumination() {
        return illumination;
    }

    public void setIllumination(int illumination) {
        this.illumination = illumination;
    }

    public int getCo2() {
        return co2;
    }

    public void setCo2(int co2) {
        this.co2 = co2;
    }

    public int getPm25() {
        return pm25;
    }

    public void setPm25(int pm25) {
        this.pm25 = pm25;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
