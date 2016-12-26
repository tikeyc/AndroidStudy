package com.tikeyc.servicestudy;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

/**
 * Created by public1 on 2016/12/26.
 */

public class ListenCallService extends Service {
    /**startService()
     * 第一次调用：MyService()->onCreate()->onStartCommand()
     * 以后再次调用：->onStartCommand()
     * stopService()后:onDestroy()
     * */
    /**bindService()
     * 第一次调用：MyService()->onCreate()->onBind()->onServiceConnected()[ServiceConnection中的回调方法]
     * onbindService():只有当前Activity与Service连接->onUnbind()->onDestroy()
     * */

    private TelephonyManager telephonyManager;
    private PhoneStateListener phoneStateListener = new PhoneStateListener(){
        /**当通话状态发生变化时调用
         * @param state
         * @param incomingNumber
         */
        @Override
        public void onCallStateChanged(int state, String incomingNumber) {
            super.onCallStateChanged(state, incomingNumber);

            switch (state) {
                case TelephonyManager.CALL_STATE_IDLE:{//空闲

                }
                break;
                case TelephonyManager.CALL_STATE_RINGING:{//响铃
                    if (incomingNumber.equals("110")) {

                    }

                }
                break;
                case TelephonyManager.CALL_STATE_OFFHOOK:{//接通

                }
                break;
                default:
                    break;
            }
        }
    };

    public ListenCallService() {

    }

    @Override
    public void onCreate() {
        super.onCreate();

        //
        telephonyManager = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
        //开启电话监听
        telephonyManager.listen(phoneStateListener,PhoneStateListener.LISTEN_CALL_STATE);
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

        //停止电话监听（必要代码）
        telephonyManager.listen(phoneStateListener,PhoneStateListener.LISTEN_NONE);
    }
}
