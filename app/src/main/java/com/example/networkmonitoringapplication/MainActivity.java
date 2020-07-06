package com.example.networkmonitoringapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
//    アクセス権限コード
    private final int PERMISSION_CODE_ACCESS_COARSE_LOCATION = 0;
//    アプリ起動時に実行
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        権限承諾の確認
//        ビルドバーションがAndroid6.0以上か
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
//            位置情報へのアクセスを許可されているか
            if (this.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
//                許可されていないのであれば実行
//                位置情報へのアクセスをリクエスト
                this.requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},PERMISSION_CODE_ACCESS_COARSE_LOCATION);
                return;
            }
        }
//        ネットワークマネージャーを初期化
        NetworkManager networkManager = new NetworkManager(this);
//        アクセスポイント一覧を取得
        List<ScanResult> scanResultList = networkManager.getWiFiAccessPointsList();
//        SSIDリスト
        List<String> ssidList = new ArrayList<String>();
//        アクセスポイント一覧からSSIDをリスト化する
        for (ScanResult accessPoint : scanResultList){
            ssidList.add(accessPoint.SSID);
        }
//        アダプターの生成
        ArrayAdapter ssidAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, ssidList);
//        リストを取得
        ListView wifiList = (ListView)findViewById(R.id.wifiList);
//        アダプターをリストに接続
        wifiList.setAdapter(ssidAdapter);
    }

////    【未使用】送信ボタンを押したときの処理
//    public void sendMessage(View view){
////        インテント：異なるコンポーネント間で、オブジェクトの参照を出来るようにするためのオブジェクト
////        インテントの生成（第二引数というActivityを起動させる）
//        Intent intent = new Intent(this, DisplayMessageActivity.class);
////        IDからビューを見つける（ここではEditTextビューを見つけてる）
//        EditText editText = (EditText) findViewById(R.id.editTextTextPersonName);
////        EditTextビューから入力された文字列を取得する
//        String message = editText.getText().toString();
////        第一引数（キー）の値を第二引数（値）として、インテントに追加する
//        intent.putExtra(EXTRA_MESSAGE,message);
////        インテントを実行する
//        startActivity(intent);
//    }
}
