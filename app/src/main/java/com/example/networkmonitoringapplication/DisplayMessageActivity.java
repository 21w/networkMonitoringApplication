package com.example.networkmonitoringapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DisplayMessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);
//        インテントを取得する
        Intent intent = getIntent();
//        インテントからメッセージを取得する
        String message  = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

//        テキストビューを取得する
        TextView textView = findViewById(R.id.textView);
//        取得したテキストビューにメッセージを代入する
        textView.setText(message);
    }
}