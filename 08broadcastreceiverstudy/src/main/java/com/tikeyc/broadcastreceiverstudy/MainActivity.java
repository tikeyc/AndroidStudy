package com.tikeyc.broadcastreceiverstudy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * 广播事件处理属于系统级别的事件处理
 * 一个应用可以在发生特定时间时发送Broadcast,系统中任何APP只要注册了对应的Receiver就会接收到此Boardcast
 * APP(进程间)之间通信的一种手段
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
