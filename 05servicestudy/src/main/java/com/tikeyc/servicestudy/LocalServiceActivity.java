package com.tikeyc.servicestudy;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.tikeyc.servicestudy.Service.MyService;

public class LocalServiceActivity extends AppCompatActivity {

    private Intent localServiceIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_service);

        localServiceIntent = new Intent(this, MyService.class);
    }



    private ServiceConnection serviceConnection;
    public void onClickButtonAction(View view) {
        switch (view.getId()) {
            case R.id.local_service_start_button:{//启动Service

                startService(localServiceIntent);

                Toast.makeText(this,"startService()",Toast.LENGTH_SHORT).show();
            }
            break;
            case R.id.local_service_stop_button:{//停止
                stopService(localServiceIntent);
                Toast.makeText(this,"stopService()",Toast.LENGTH_SHORT).show();
            }
            break;


            case R.id.local_service_bind_button:{//绑定
                if (serviceConnection == null) {
                    serviceConnection = new ServiceConnection() {

                        @Override
                        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                            //须在MyService中重新的方法onBind(Intent intent)中返回Binder()对象才会回调此处
                            Log.e("TAG","onServiceDisconnected");
                            Toast.makeText(LocalServiceActivity.this,"onServiceDisconnected()",Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onServiceDisconnected(ComponentName componentName) {
                            //几乎不会调用
                        }
                    };
                    bindService(localServiceIntent,serviceConnection, Context.BIND_AUTO_CREATE);
                }

            }
            break;
            case R.id.local_service_unbind_button:{//解绑
                if (serviceConnection != null) {
                    unbindService(serviceConnection);
                    serviceConnection = null;
                    Toast.makeText(this,"unbindService()",Toast.LENGTH_SHORT).show();
                }
            }
            break;
            default:
                break;

        }
    }

    /**
     * Activity销毁之前调用
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (serviceConnection != null) {
            unbindService(serviceConnection);
            serviceConnection = null;
        }
    }
}
