package com.example.networkmonitoringapplication;

import android.content.Context;
import android.net.wifi.WifiManager;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class NetworkManager extends AppCompatActivity {
//    WiFiマネージャーの定義
    public WifiManager wifiManager;
    public NetworkManager (Context context){
//        WiFiマネージャーの初期化
        wifiManager = (WifiManager)context.getSystemService(Context.WIFI_SERVICE);
    }
//    アクセスポイント一覧を取得
    public List getWiFiAccessPointsList(){
        return wifiManager.getScanResults();
    }
}
