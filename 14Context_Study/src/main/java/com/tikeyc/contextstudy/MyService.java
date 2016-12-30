package com.tikeyc.contextstudy;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by public1 on 2016/12/30.
 */

public class MyService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new Binder();
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        MyApplication myApplication = (MyApplication) getApplicationContext();
        String data = myApplication.getData();
        Log.e("TAG","myApplication保存数据:" + data);

        return super.onStartCommand(intent, flags, startId);
    }
}
