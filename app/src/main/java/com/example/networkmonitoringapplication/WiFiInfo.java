package com.example.networkmonitoringapplication;

public class WiFiInfo {
    // データ型の定義
    private String ssid;
    private int rssi;
    private long timeStamp;

    public WiFiInfo(String ssid, int rssi, long timeStamp) {
        this.ssid = ssid;
        this.rssi = rssi;
        this.timeStamp = timeStamp;
    }

    // セッター
    public void setSsid(String ssid) {
        this.ssid = ssid;
    }

    public void setRssi(int rssi) {
        this.rssi = rssi;
    }

    public void setTimeStamp(long timeStamp){
        this.timeStamp = timeStamp;
    }

    //ゲッター
    public String getSsid() {
        return ssid;
    }

    public int getRssi() {
        return rssi;
    }

    public long getTimeStamp(){
        return timeStamp;
    }

}
