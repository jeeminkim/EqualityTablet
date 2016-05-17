package com.example.daniel.equalitytablet;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;


public class LogoActivity extends Activity {
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Intent intent = new Intent(LogoActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitylogo);
        Message msg = mHandler.obtainMessage();
        mHandler.sendMessageDelayed(msg, 3000);
    }
}