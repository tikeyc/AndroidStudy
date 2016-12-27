package com.tikeyc.broadcastreceiverstudy;

import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

/**
 * 广播事件处理属于系统级别的事件处理
 * 一个应用可以在发生特定时间时发送Broadcast,系统中任何APP只要注册了对应的Receiver就会接收到此Boardcast
 * APP(进程间)之间通信的一种手段
 */
public class MainActivity extends AppCompatActivity {

    private MyReceive1 myReceive1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void onClickButtonAction (View view) {
        switch (view.getId()) {
            case R.id.main_button1:{//动态注册广播接收器
                if (myReceive1 == null) {
                    myReceive1 = new MyReceive1();
                    IntentFilter intentFilter = new IntentFilter("com.tikeyc.broadcastreceiverstudy.MyReceiver.action");
                    registerReceiver(myReceive1,intentFilter);
                }

            }
            break;
            case R.id.main_button2:{
                if (myReceive1 != null) {
                    unregisterReceiver(myReceive1);
                    myReceive1 = null;
                }


            }
            break;
            default:
                break;
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (myReceive1 != null) {
            unregisterReceiver(myReceive1);
            myReceive1 = null;
        }
    }
}
