package com.tikeyc.servicestudy.Service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by public1 on 2016/12/23.
 */

/**
 * 需要在AndroidManifest.xml中加入Service标签
 *
 * */
public class MyService extends Service {

    /**startService()
     * 第一次调用：MyService()->onCreate()->onStartCommand()
     * 以后再次调用：->onStartCommand()
     * stopService()后:onDestroy()
     * */
    /**bindService()
     * 第一次调用：MyService()->onCreate()->onBind()->onServiceConnected()[ServiceConnection中的回调方法]
     * onbindService():只有当前Activity与Service连接->onUnbind()->onDestroy()
     * */

    public MyService() {

    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    /**
     * @param intent
     * @return 返回对象才会去调用绑定连接的onServiceDisconnected()方法
     */
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new Binder();
//        return null;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


}
