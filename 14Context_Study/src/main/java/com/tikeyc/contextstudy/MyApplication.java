package com.tikeyc.contextstudy;

import android.app.Application;
import android.util.Log;

/**
 * Created by public1 on 2016/12/30.
 */


/**须在AndroidManifest.xml中的application标签中配置 android:name=".MyApplication"
 *
 * APP退出Application不会销毁，除非杀死APP进程
 * */
public class MyApplication extends Application {

    private String data;

    public String getData() {
        return data;
    }

    @Override
    public String toString() {
        return "MyApplication{" +
                "data='" + data + '\'' +
                '}';
    }

    public void setData(String data) {
        this.data = data;
    }

    public MyApplication() {
        Log.e("TAG","MyApplication()");
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Log.e("TAG","MyApplication onCreate()");
    }
}
