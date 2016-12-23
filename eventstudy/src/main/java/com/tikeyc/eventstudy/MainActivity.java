package com.tikeyc.eventstudy;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.tikeyc.eventstudy.customView.CustomViewActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //视图的操作MotionEvent
        //按键操作KeyEvent

        Button button1 = (Button) findViewById(R.id.main_button1);
        Button button2 = (Button) findViewById(R.id.main_button2);

    }


    public void onClickButtonAction(View view) {
        switch (view.getId()) {
            case R.id.main_button1:{
                Intent intent = new Intent(this,MotionEventActivity.class);
                startActivity(intent);

            }
            break;
            case R.id.main_button2:{
                Intent intent = new Intent(this,KeyEventActivity.class);
                startActivity(intent);
            }
            break;
            case R.id.main_button3:{
                Intent intent = new Intent(this,CustomViewActivity.class);
                startActivity(intent);
            }
            break;
            default:
                break;
        }

    }


    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        //监听手机底部返回按钮
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            //显示提示
            new AlertDialog.Builder(this)
                    .setMessage("确认退出")
                    .setPositiveButton("残忍退出", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            finish();
                        }
                    })
                    .setNegativeButton("取消", null)
                    .show();

            return true;//不会退出
        }

        return super.onKeyUp(keyCode, event);
    }
}
