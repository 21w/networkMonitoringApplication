package com.example.networkmonitoringapplication;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class NetworkManager extends AppCompatActivity {
    //　WiFiマネージャーの定義
    private static WifiManager wifiManager;
    // WiFi情報リストの定義
    private static WiFiInfoList wiFiInfoList = WiFiInfoList.getWiFiInfoList();

    public NetworkManager(Context context) {
        // WiFiマネージャーの初期化
        wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
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
            wiFiInfoList.addWiFiInfo(info.SSID,info.level,info.timestamp);
        }
    }
}
