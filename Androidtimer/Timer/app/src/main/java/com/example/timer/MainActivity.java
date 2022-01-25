package com.example.timer;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TimeHandler _timer = null;
    long _start = 0;
    TextView _show = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _show = findViewById(R.id.TimeText);
        //instance
        _timer = new TimeHandler();
    }

    public void onStart(View view) {
        _start = System.currentTimeMillis();
        //loop start
        _timer.sendEmptyMessageDelayed(0,100);
    }

    public void onStop(View view) {
        _timer.removeMessages(0);
    }

    //inner class
    public class TimeHandler extends Handler {
        public void handleMessage(Message msg) {
            long now = System.currentTimeMillis();
            long diff = now - _start;

            long sec = (diff / 1000) % 60;
            long mil = diff % 1000;
            long min = (diff / 1000) / 60;

            //_show.setText(String.valueOf(diff));
            _show.setText(String.format("%02d",min) + ":" + String.format("%02d",sec) + ":" + String.format("%03d", mil));


            Log.d("hogehoge","loop...");
            this.sendEmptyMessageDelayed(0,100);
        }
    }
}