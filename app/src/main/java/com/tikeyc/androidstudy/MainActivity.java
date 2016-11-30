package com.tikeyc.androidstudy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText textView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView1 = (EditText) findViewById(R.id.main_editText1);
        //
        Button button1 = (Button) findViewById(R.id.main_button1);
        //
        button1.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        Toast.makeText(this,"提示",Toast.LENGTH_LONG).show();
        //一般启动界面
//        Intent intent = new Intent(this,SecondActivity.class);
//        intent.putExtra("main_message",textView1.getText().toString());
//        startActivity(intent);


        //带回调启动界面  Intent相当于一个信使，用于携带数据（携带一个Activity,一个字符串或其他类型的数据）
        //安装操作系统中的ActivityManager类会自动管理和启动Activity和数据
        Intent intent2 = new Intent(this,SecondActivity.class);
        //设置传入的数据
        intent2.putExtra("main_message",textView1.getText().toString());
        int requestCode = 2;
        startActivityForResult(intent2,requestCode);
    }

    /*
     *带回调启动界面的回调方法，在此获取回调数据
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //根据启动是设置的requestCode 和 回调时设置的resultCode 获取制定的回调数据
        if (requestCode == 2 && resultCode == 3){
            String resultString = data.getStringExtra("Result").toString();
            textView1.setText(resultString);
        }
    }



    /*
     *Activity 生命周期
     * 界面从死亡--》运行
     *     创建对象--》onCreate()-->onStart()-->onResume()
     * 界面从运行--》死亡
     *     onPause()-->onStop()-->onDestory()
     * 界面从停止--》运行
     *     onRestart()-->onStart()-->onResume()
     * 界面从运行--》暂停
     *     onPause()
     * 界面从暂停--》运行
     *     onResume()
     */

    @Override
    protected void onStart() {

        Log.e("TAG","onStart");
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.e("TAG","onResume");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.e("TAG","onPause");
        super.onPause();
    }

    @Override
    protected void onRestart() {
        Log.e("TAG","onRestart");
        super.onRestart();
    }

    @Override
    protected void onStop() {
        Log.e("TAG","onStop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.e("TAG","onResume");
        super.onDestroy();
    }

}

