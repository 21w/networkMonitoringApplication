package com.example.networkmonitoringapplication;

public class WiFiInfo {
    // データ型の定義
    private String ssid;
    private int rssi;
    private String timeStamp;

    public WiFiInfo(String ssid, int rssi, String timeStamp) {
        this.ssid = ssid;
        this.rssi = rssi;
        this.timeStamp = timeStamp;
    }

    // セッター

    public void setRssi(int rssi) {
        this.rssi = rssi;
    }

    public void setTimeStamp(String timeStamp){
        this.timeStamp = timeStamp;
    }

    //ゲッター
    public String getSsid() {
        return ssid;
    }

    public int getRssi() {
        return rssi;
    }

    public String getTimeStamp(){
        return timeStamp;
    }

}
