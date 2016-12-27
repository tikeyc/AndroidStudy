package com.tikeyc.a09boardcaststudy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }



    public void onClickButtonAction (View view) {
        switch (view.getId()) {
            case R.id.main_button1:{//发送一段广播
                Intent intent = new Intent("com.tikeyc.broadcastreceiverstudy.MyReceiver.action");
                intent.putExtra("action","sendBroadcast--");
                sendBroadcast(intent);

            }
            break;
            case R.id.main_button2:{//发送有序广播 ,可中断，可设置优先级
                Intent intent = new Intent("com.tikeyc.broadcastreceiverstudy.MyReceiver.action1");
                intent.putExtra("action","sendBroadcast--");
                sendOrderedBroadcast(intent,null);

            }
            break;

            default:
                break;
        }
    }
}
