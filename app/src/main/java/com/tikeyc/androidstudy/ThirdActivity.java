package com.tikeyc.androidstudy;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ThirdActivity extends AppCompatActivity implements View.OnLongClickListener {


    private EditText numTextView;
    private EditText smsTextView;

    private View.OnClickListener onClickListener = new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            int tag = Integer.parseInt(view.getTag().toString());

            switch (tag){
                case 1:{
                    Toast.makeText(ThirdActivity.this,"显示打电话界面",Toast.LENGTH_SHORT).show();

                    String action = "android.intent.action.DIAL";
                    action = Intent.ACTION_DIAL;
                    Intent intent = new Intent();
                    String num = numTextView.getText().toString();
                    intent.setData(Uri.parse("tel:"+num));
                    startActivity(intent);
                }
                break;
                case 2:{
                    Toast.makeText(ThirdActivity.this, "显示发短信界面", Toast.LENGTH_SHORT).show();
                    String action = "android.intent.action.SENDTO";
                    action = Intent.ACTION_SENDTO;
                    Intent intent = new Intent();
                    String num = numTextView.getText().toString();
                    String message = smsTextView.getText().toString();

                    intent.setData(Uri.parse("smsto:"+num));
                    intent.putExtra("sms_body",message);
                    startActivity(intent);
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
        setContentView(R.layout.activity_third);


        numTextView = (EditText) findViewById(R.id.third_editText1);
        smsTextView = (EditText) findViewById(R.id.third_editText2);

        Button callButton = (Button) findViewById(R.id.third_button1);
        Button smsButton = (Button) findViewById(R.id.third_button2);

        /*添加点击监听 当然也可以这样设置：smsButton.setOnClickListener(this); 需implements View.OnClickListener
        * 重写onClick(View view){}方法在此内实现事件响应
        * */
        callButton.setOnClickListener(onClickListener);
        smsButton.setOnClickListener(onClickListener);
        /*也可以用下面形式来监听：直接new一个 个人觉得此法对于单个按钮最好，按钮多余1个时用上面方法，统一调用
        *类似于iOS的block回调
        *
        smsButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                int tag = Integer.parseInt(view.getTag().toString());
                Log.i("onClick","不同方法按钮创建监听");
            }
        });*/

        //添加长按监听
        callButton.setOnLongClickListener(this);
        smsButton.setOnLongClickListener(this);

    }

    @Override
    public boolean onLongClick(View view) {
        int tag = Integer.parseInt(view.getTag().toString());

        switch (tag){
            case 1:{
                Toast.makeText(ThirdActivity.this,"直接拨打电话",Toast.LENGTH_SHORT).show();

                //在AndroidManifest.xml中添加APP的相关权限<uses-permission android:name="android.permission.对应的权限标识"/>
                String action = "android.intent.action.CALL";
                action = Intent.ACTION_CALL;
                Intent intent = new Intent();
                String num = numTextView.getText().toString();
                intent.setData(Uri.parse("tel:"+num));
                startActivity(intent);
            }
            break;
            case 2:{
                Toast.makeText(ThirdActivity.this, "直接发送短信", Toast.LENGTH_SHORT).show();

                SmsManager smsManager = SmsManager.getDefault();

                String num = numTextView.getText().toString();
                String message = smsTextView.getText().toString();

                smsManager.sendTextMessage(num,null,message,null,null);
            }
            break;
            default:
                break;
        }

        return false;//如果返回true则长按事件被消费，不会再响应
    }
}
