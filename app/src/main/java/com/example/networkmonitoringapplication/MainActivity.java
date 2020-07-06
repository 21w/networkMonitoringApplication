package com.example.networkmonitoringapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.networkMonitoringApplication.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

//    送信ボタンを押したときの処理
    public void sendMessage(View view){
//        インテント：異なるコンポーネント間で、オブジェクトの参照を出来るようにするためのオブジェクト
//        インテントの生成（第二引数というActivityを起動させる）
        Intent intent = new Intent(this, DisplayMessageActivity.class);
//        IDからビューを見つける（ここではEditTextビューを見つけてる）
        EditText editText = (EditText) findViewById(R.id.editTextTextPersonName);
//        EditTextビューから入力された文字列を取得する
        String message = editText.getText().toString();
//        第一引数（キー）の値を第二引数（値）として、インテントに追加する
        intent.putExtra(EXTRA_MESSAGE,message);
//        インテントを実行する
        startActivity(intent);
    }
}
