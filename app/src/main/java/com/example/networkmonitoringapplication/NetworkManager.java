package com.example.networkmonitoringapplication;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.logging.SimpleFormatter;

public class NetworkManager {
    //　WiFiマネージャーの定義
    private WifiManager wifiManager;
    // WiFi情報リストの定義
    private static WiFiInfoList wiFiInfoList = WiFiInfoList.getWiFiInfoList();

    public NetworkManager(Context context) {
        // WiFiマネージャーの初期化
        wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
    }

    // WiFi情報を更新する
    public WiFiInfo updateWiFiInfo(String ssid){
        System.out.println(wifiManager.startScan());
        //　特定のWiFi情報を取得
        ScanResult updateInfo = getScanResult(ssid);
        // 特定のWiFi情報を更新
        return wiFiInfoList.updateWiFiInfo(updateInfo.SSID,updateInfo.level,getTimeStamp(updateInfo.timestamp));
    }

    //　特定のWiFi情報を取得
    private ScanResult getScanResult(String ssid){
        // WiFiをスキャンする
        List<ScanResult> scanResult = wifiManager.getScanResults();
        for (ScanResult result : scanResult){
            if (result.SSID.equals(ssid)){
                return result;
            }
        }
        return null;
    }

    //　現在時刻の取得
    private String getTimeStamp(long timeStamp){
        // 現在時刻を取得
        Timestamp timestamp1 = new Timestamp(System.currentTimeMillis());
        // 日付フォーマット
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.");
        return simpleDateFormat.format(timestamp1)+String.valueOf(timeStamp);
    }

    //　WiFi一覧を生成
    public void createWiFiList() {
        // 検出可能なWiFi一覧を取得
        List<ScanResult> scanResult = wifiManager.getScanResults();
        //　もし、リストが空でなかったら
        if (wiFiInfoList.isEmptyList()){
            //　リストを初期化する
            wiFiInfoList.removeAllWiFiInfo();
        }
        // WiFiInfoListに新規登録する
        for (ScanResult info : scanResult){
            wiFiInfoList.addWiFiInfo(info.SSID,info.level,getTimeStamp(info.timestamp));
        }
    }
}
