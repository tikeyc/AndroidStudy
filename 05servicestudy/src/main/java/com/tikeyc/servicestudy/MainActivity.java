package com.tikeyc.servicestudy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

/**Service:
 * Service在后台运行（在主线程运行）退出APP  在默认情况下运行在应用程序进程中
 * Service是一个应用组件，用来在后台完成一个时间跨度比较大的工作，且没有关联任何界面
 * 可以完成以下工作：1.访问网络 2.播放音乐 3.文件IO操作 4.大数据量的数据库操作
 *
 *
 * Local Service:Service对象与Service启动者在同一个进程中
 *
 * Remote Service:Service对象与Service启动者不在同一个进程中（其他APP的service启动），需要进程间通信（AIDL来实现）
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }



    public void onClickButtonAction(View view) {
        switch (view.getId()) {
            case R.id.main_service_button1:{
                Intent intent = new Intent(this,LocalServiceActivity.class);
                startActivity(intent);

            }
            break;
            case R.id.main_service_button2:{
                Intent intent = new Intent(this,RemoteServiceActivity.class);
                startActivity(intent);

            }
            break;



            //////////////////////须添加拨打电话权限

            case R.id.main_service_button3:{//开启来电监听

                startService(new Intent(this,ListenCallService.class));

            }
            break;
            case R.id.main_service_button4:{
                stopService(new Intent(this,ListenCallService.class));

            }
            break;
            default:
                break;

        }

    }

}
