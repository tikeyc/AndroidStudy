package com.tikeyc.messageandthreadstudy;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class HandlerDemoActivity extends AppCompatActivity {

    public static final int WHAT_ADD = 1;
    public static final int WHAT_DELE = 2;

    private TextView numTextView;
    private Button addButton;
    private Button deleButton;
    private Button stopButton;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {//主线程
            super.handleMessage(msg);


            int number = Integer.parseInt(numTextView.getText().toString());
            switch (msg.what) {
                case WHAT_ADD:{
                    if (number < 100) {
                        //
                        number++;
                        numTextView.setText(number+"");
                        //自动增加
                        handler.sendEmptyMessage(WHAT_ADD);
                    }else {
                        stopButton.setEnabled(false);
                        Toast.makeText(HandlerDemoActivity.this,"最大值"+number,Toast.LENGTH_SHORT).show();
                    }

                }
                break;
                case WHAT_DELE:{
                    if (number > -50) {
                        //
                        number--;
                        numTextView.setText(number+"");
                        //自动减少
                        handler.sendEmptyMessage(WHAT_DELE);

                    }else {
                        stopButton.setEnabled(false);
                        Toast.makeText(HandlerDemoActivity.this,"最小值"+number,Toast.LENGTH_SHORT).show();
                    }

                }
                break;
                default:
                    break;
            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_demo);


        numTextView = (TextView) findViewById(R.id.handler_demo_textView1);
        addButton = (Button) findViewById(R.id.handler_demo_button1);
        deleButton = (Button) findViewById(R.id.handler_demo_button2);
        stopButton = (Button) findViewById(R.id.handler_demo_button3);
        stopButton.setEnabled(false);

    }



    public void onClickButtonAction(View view) {

        switch (view.getId()) {

            case R.id.handler_demo_button1: {
                addButton.setEnabled(false);
                deleButton.setEnabled(true);
                stopButton.setEnabled(true);

                //
                handler.removeMessages(WHAT_DELE);
                //
//                handler.sendEmptyMessageAtTime(what, SystemClock.uptimeMillis() + uptimeMillis);
                handler.sendEmptyMessage(WHAT_ADD);

                //
                for (int i = 0; i < 10;i++ ) {//测试分线程
                    //do something
                }

            }
            break;
            case R.id.handler_demo_button2: {
                addButton.setEnabled(true);
                deleButton.setEnabled(false);
                stopButton.setEnabled(true);
                //
                handler.removeMessages(WHAT_ADD);
                //
                handler.sendEmptyMessage(WHAT_DELE);

            }
            break;
            case R.id.handler_demo_button3: {//暂停
                addButton.setEnabled(true);
                deleButton.setEnabled(true);
                stopButton.setEnabled(false);
                //
                handler.removeMessages(WHAT_ADD);
                handler.removeMessages(WHAT_DELE);

            }
            break;
            default:
                break;

        }

    }
}
