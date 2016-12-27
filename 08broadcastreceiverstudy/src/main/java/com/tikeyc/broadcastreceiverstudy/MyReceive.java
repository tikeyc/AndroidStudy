package com.tikeyc.broadcastreceiverstudy;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by public1 on 2016/12/27.
 */

/**在AndroidManifest.xml中配置<servicer></servicer> 静态注册广播接收器
 * APP安装成功时注册，注册时不会创建对象，接收广播时才创建
 * APP卸载，对象执行onReceive()后就销毁回收了
 * 在APP整个生命过程中使用
 * */
public class MyReceive extends BroadcastReceiver {

    public MyReceive() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        String action = intent.getStringExtra("action");
        Log.e("TAG",action + 0);
    }
}
