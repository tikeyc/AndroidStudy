package com.tikeyc.broadcastreceiverstudy;

import android.content.BroadcastReceiver;

import android.content.Context;
import android.content.Intent;
import android.content.res.ObbInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.telephony.SmsMessage;
import android.util.Log;

/**
 * Created by public1 on 2016/12/27.
 */

public class SmsReceiver extends BroadcastReceiver {
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onReceive(Context context, Intent intent) {

        //得到intent短信数据，并封装成短信对象smsMessage
        Bundle extras = intent.getExtras();
        Object[] pdus = (ObbInfo[]) extras.get("pdus");

        SmsMessage smsMessage = SmsMessage.createFromPdu((byte[])pdus[0],null);
        //
        String number = smsMessage.getOriginatingAddress();
        String content = smsMessage.getMessageBody();
        Log.e("TAG","SmsMessage" + number);

    }
}
