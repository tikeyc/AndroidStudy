package com.tikeyc.androidstudy;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.support.v7.app.AlertDialog;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText textView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //
        initView();
    }

    /*
     *Activity 生命周期  与iOS中的viewController中的viewDidLoad,等类似
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


    ////////////////////////////////////////init

    public void initView(){
        textView1 = (EditText) findViewById(R.id.main_editText1);
        //
        Button button1 = (Button) findViewById(R.id.main_button1);
        Button button2 = (Button) findViewById(R.id.main_button2);
        //
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);

        //UI
        Button button3 = (Button) findViewById(R.id.main_button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,UIStudyActivity.class);
                startActivity(intent);
            }
        });

        //ListView
        Button button4 = (Button) findViewById(R.id.main_button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String[] items = {"ArrayAdapter","SimpleAdapter","BaseAdapter"};
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("选择ListView Adapter类型")
                        .setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                switch (i){
                                    case 0:{

                                    }
                                    break;
                                    case 1:{

                                    }
                                    break;
                                    case 2:{

                                    }
                                    break;
                                    default:
                                        break;
                                }

                                //
                                Intent intent = new Intent(MainActivity.this,ListviewActivity.class);

                                intent.putExtra("ListViewAdapterType",items[i]);

                                startActivity(intent);
                                //
                                dialogInterface.dismiss();
                            }
                        })
                        .show();
            }
        });
    }

    ////////////////////////////////////////Actions Method

    @Override
    public void onClick(View view) {


        int tag = Integer.parseInt(view.getTag().toString());
        Toast.makeText(this,"提示 " + tag,Toast.LENGTH_LONG).show();
        switch (tag){
            case 1:{
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
                break;
            }

            case 2: {//电话 短信
                Intent intent = new Intent(this,ThirdActivity.class);
                startActivity(intent);
                break;
            }

            default:
                break;

        }



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




}

