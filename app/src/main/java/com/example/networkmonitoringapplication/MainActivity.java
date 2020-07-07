package com.example.networkmonitoringapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.wifi.ScanResult;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    // インテント管理コード
    public static final String SSID_SELECTNUMBER = "SSID_SELECTNUMBER";
    //　アクセス権限コード
    private final int PERMISSION_CODE_ACCESS_COARSE_LOCATION = 0;
    // ネットワークマネージャー
    private NetworkManager networkManager;
    // WiFiリスト
    private static WiFiInfoList wiFiInfoList = WiFiInfoList.getWiFiInfoList();

    // アプリ起動時に実行
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //　権限承諾の確認
        // ビルドバーションがAndroid6.0以上か
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // 位置情報へのアクセスを許可されているか
            if (this.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                //　許可されていないのであれば実行
                //　位置情報へのアクセスをリクエスト
                this.requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, PERMISSION_CODE_ACCESS_COARSE_LOCATION);
                return;
            }
        }
        //　ネットワークマネージャーの生成
        networkManager = new NetworkManager(this);
        // WiFiリストを生成
        networkManager.createWiFiList();
        //　Wi-Fiリストの表示
        showWiFiList();
    }

    //　Wi-Fiリストの表示
    private void showWiFiList() {
        // アダプターの生成
        ArrayAdapter wifiAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, wiFiInfoList.getSsidArrayList());
        // リストビューコンポーネントを取得
        ListView ssidListView = (ListView)findViewById(R.id.ssidListView);
        // リストビューのクリック判定
        ssidListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            // 第一引数：イベントが起きたListView　第二引数：選択されたリスト項目　第三引数：選択されたリスト項目の位置　第四引数：選択されたリスト項目のIDを示す値
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // WiFi情報画面へ遷移
                showWiFiDetailInfo(position);
            }
        });
        // アダプターをリストビューに接続
        ssidListView.setAdapter(wifiAdapter);
    }

    // WiFi情報の詳細画面へ遷移
    public void showWiFiDetailInfo(int positionNumber) {
        //　インテントの生成
        Intent selectSSIDIntent = new Intent(this,WifiDetailInformation.class);
        // インテントに選択された番号を入力
        selectSSIDIntent.putExtra(SSID_SELECTNUMBER,positionNumber);
        // インテントの起動
        startActivity(selectSSIDIntent);
    }
}
