package com.tikeyc.broadcastreceiverstudy;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by public1 on 2016/12/27.
 */

/**动态注册广播接收器，代码注册
 * 执行registerReceiver()方法注册，注册就创建对象
 * 执行unregisterReceiver()（注：Activity退出必须解注册，否则报错）后对象销毁回收
 * 只服务员某一个Activity/Service
 * */
public class MyReceive2 extends BroadcastReceiver {

    public MyReceive2() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        String action = intent.getStringExtra("action");
        Log.e("TAG",action + 2);
    }
}
