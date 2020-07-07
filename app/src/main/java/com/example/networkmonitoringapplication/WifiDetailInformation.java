package com.example.networkmonitoringapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.wifi.ScanResult;
import android.os.Build;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.TextView;

import org.w3c.dom.Text;

public class WifiDetailInformation extends AppCompatActivity {
    // WiFiリスト
    private static WiFiInfoList wiFiInfoList = WiFiInfoList.getWiFiInfoList();
    // 表示対象のWiFiデータ
    private WiFiInfo wiFiInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifi_detail_information);
        // 自動更新の切り替え
        // 自動更新トグルボタンを取得
        CompoundButton autoUpdateButton = (CompoundButton) findViewById(R.id.automaticUpdatingSwitcher);
        // トグルボタンの状態を検知
        autoUpdateButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // 自動更新が有効のとき
                if (isChecked){
                    autoUpdate();
                }
            }
        });
        // インテントの取得
        Intent wifiIntent = getIntent();
        // WiFi詳細情報を取得
        wiFiInfo = wiFiInfoList.getWiFiInfo(wifiIntent.getIntExtra(MainActivity.SSID_SELECTNUMBER,999));
        // SSID名を表示するTextViewを取得
        TextView ssidTextView = findViewById(R.id.ssidTextView);
        // 電波強度を表示するTextViewを取得
        TextView rssiTextView = findViewById(R.id.rssiTextView);
        // タイムスタンプを表示するTextViewを取得
        TextView timeStampTextView = findViewById(R.id.timeStampTextView);
        // SSID名を表示する
        ssidTextView.setText(wiFiInfo.getSsid());
        // RSSIを表示する
        rssiTextView.setText(String.valueOf(wiFiInfo.getRssi()));
        // タイムスタンプを表示する
        timeStampTextView.setText(String.valueOf(wiFiInfo.getTimeStamp()));
    }

    //　自動更新
    private void autoUpdate(){
    }
}