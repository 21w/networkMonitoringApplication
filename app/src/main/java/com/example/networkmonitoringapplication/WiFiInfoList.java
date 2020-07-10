package com.example.networkmonitoringapplication;

import java.util.ArrayList;

public class WiFiInfoList {
    // データ型の定義
    private static WiFiInfoList wiFiInfoList = new WiFiInfoList();
    private ArrayList<WiFiInfo> wiFiInfoArrayList = new ArrayList<>();

    private WiFiInfoList() {
    }

    // リストに新しいWiFi情報を登録
    public void addWiFiInfo(String ssid, int rssi, String timeStamp) {
        wiFiInfoArrayList.add(new WiFiInfo(ssid, rssi, timeStamp));
        System.out.println(wiFiInfoArrayList.get(0));
    }

    //　特定のWiFi情報を更新する
    public WiFiInfo updateWiFiInfo (String ssid, int rssi, String timeStamp){
        for (WiFiInfo info:wiFiInfoArrayList){
            if (info.getSsid().equals(ssid)){
                info.setRssi(rssi);
                info.setTimeStamp(timeStamp);
                return info;
            }
        }
        return null;
    }

    // SSID一覧を参照する
    public ArrayList<String> getSsidArrayList() {
        // SSID一覧を取得
        ArrayList<String> ssidList = new ArrayList<>();
        for (int i = 0; i < wiFiInfoArrayList.size(); i++) {
            ssidList.add(wiFiInfoArrayList.get(i).getSsid());
        }
        return ssidList;
    }

    //　リスト（個別）を参照する
    public WiFiInfo getWiFiInfo(int number){
        if(number>=999){
            return null;
        }
        return wiFiInfoArrayList.get(number);
    }

    // リストを全件削除する
    public void removeAllWiFiInfo() {
        wiFiInfoArrayList.clear();
    }

    // リストが空かどうか
    public boolean isEmptyList(){
        return wiFiInfoArrayList != null;
    }

    //　インスタンスを返す
    public static WiFiInfoList getWiFiInfoList() {
        return wiFiInfoList;
    }
}
