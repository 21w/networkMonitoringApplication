package com.example.networkmonitoringapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.CompoundButton;
import android.widget.TextView;


import java.util.Timer;
import java.util.TimerTask;

public class WifiDetailInformation extends AppCompatActivity {
    // 定義
    private String ssid = "";
    private boolean auto = false;
    // TextViewの定義
    TextView ssidTextView;
    TextView rssiTextView;
    TextView timeStampTextView;
    // WiFiリスト
    private static WiFiInfoList wiFiInfoList = WiFiInfoList.getWiFiInfoList();
    // ネットワークマネージャー
    private NetworkManager networkManager;
    // 表示対象のWiFiデータ
    private WiFiInfo wiFiInfo;
    // ハンドラーの定義
    private Handler handler = new Handler();

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
                //　自動更新
                autoUpdate(isChecked);
            }
        });
        //　ネットワークマネージャーの初期化
        networkManager = new NetworkManager(this);
        // インテントの取得
        Intent wifiIntent = getIntent();
        // WiFi詳細情報を取得
        wiFiInfo = wiFiInfoList.getWiFiInfo(wifiIntent.getIntExtra(MainActivity.SSID_SELECTNUMBER, 999));
        // SSID名を表示するTextViewを取得
        ssidTextView = findViewById(R.id.ssidTextView);
        // 電波強度を表示するTextViewを取得
        rssiTextView = findViewById(R.id.rssiTextView);
        // タイムスタンプを表示するTextViewを取得
        timeStampTextView = findViewById(R.id.timeStampTextView);
        // 情報を表示
        showData(wiFiInfo);
    }

    // 自動更新
    private void autoUpdate(boolean isChecked){
        // Timerクラスの生成
        Timer timer = new Timer();;
        if (isChecked){
            // Timerクラスが存在するか
            if (timer == null){
                // Timerクラスを再生成
                timer = new Timer();
            }
            // Timerクラスの実行
            timer.schedule(new AutoUpdateTask(),1000,1000);
            auto = true;
        }else{
            auto = false;
        }
    }

    // 情報を表示
    private void showData(WiFiInfo wiFiInfo){
        // SSIDを確保する
        ssid = wiFiInfo.getSsid();
        // SSID名を表示する
        ssidTextView.setText(wiFiInfo.getSsid());
        // RSSIを表示する
        rssiTextView.setText(String.valueOf(wiFiInfo.getRssi()));
        // タイムスタンプを表示する
        timeStampTextView.setText(wiFiInfo.getTimeStamp());

    }

    // 自動更新タスク(インナークラス)
    class AutoUpdateTask extends TimerTask {
        @Override
        public void run(){
            handler.post(new Runnable() {
                @Override
                public void run() {
                    WiFiInfo info = networkManager.updateWiFiInfo(ssid);
                    // WiFi情報の更新と表示
                    showData(info);
                    System.out.println(info.getTimeStamp());
                    // 自動更新の停止
                    if (!auto){
                        cancel();
                    }
                }
            });
        }
    }
}

