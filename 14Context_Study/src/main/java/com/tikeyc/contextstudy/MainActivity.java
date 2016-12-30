package com.tikeyc.contextstudy;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;


/** Activity 与 getApplicationContext()
 *
 * 显示Dialog时必须使用Activity
 * 使用Adapter时必须使用Activity
 * 显示地图是必须使用getApplicationContext()
 *
 * 其他绝大多数情况两者皆可用：使用getApplicationContext()肯定没问题，使用Activity可能会导致Activity对象无法销毁，导致内存泄漏
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //获取Application
        MyApplication myApplication = (MyApplication) getApplication();
        Context context = getApplicationContext();//地图必须使用getApplicationContext()
        if (myApplication == context) {
            Log.e("TAG","myApplication == context");
        }

        Intent myService = new Intent(this, MyService.class);
        startService(myService);

        //在Application中保存数据
        myApplication.setData("tikeyc");


    }



    public void onClickTestANR(View view) {
        Log.e("TAG","onClickTestANR");
        SystemClock.sleep(10000);
    }


    /**横竖屏 回调方法
     * @param newConfig
     *
     * 在AndroidManifest.xml中对应的Activity中添加: android:configChanges="orientation|keyboardHidden|screenSize"
     *
     * 只支持横屏：在AndroidManifest.xml中对应的Activity中添加: android:screenOrientation="landscape"
     */
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        int orientation = newConfig.orientation;

        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            Log.e("TAG","竖屏");

        } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Log.e("TAG","横屏");

        } else if (orientation == Configuration.ORIENTATION_UNDEFINED) {
            Log.e("TAG","倒屏");

        }
    }


    public void onClickSwitchOrientation(View view) {
        int orientation = getResources().getConfiguration().orientation;

        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        } else if (orientation == Configuration.ORIENTATION_UNDEFINED) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
    }

}
