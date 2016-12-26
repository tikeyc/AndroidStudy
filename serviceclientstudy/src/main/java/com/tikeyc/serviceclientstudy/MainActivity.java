package com.tikeyc.serviceclientstudy;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.tikeyc.serviceclientstudy.R;
import com.tikeyc.serviceclientstudy.Service.IStudentService;
import com.tikeyc.serviceclientstudy.Service.Student;

public class MainActivity extends AppCompatActivity {

    private EditText editText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.remote_editText);
    }


    private ServiceConnection serviceConnection;
    private IStudentService iStudentService;

    public void onClickButtonAction(View view) {
        switch (view.getId()) {
            case R.id.remote_service_bind_button:{//绑定远程服务
                Intent remoteServiceIntent = new Intent("com.tikeyc.servicestudy.Service.MyRemoteService");
                if (serviceConnection == null) {
                    serviceConnection = new ServiceConnection() {

                        @Override
                        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                            //须在MyService中重新的方法onBind(Intent intent)中返回Binder()对象才会回调此处
                            Log.e("TAG","onServiceDisconnected");
                            Toast.makeText(MainActivity.this,"onServiceDisconnected()",Toast.LENGTH_SHORT).show();

                            iStudentService = IStudentService.Stub.asInterface(iBinder);
                        }

                        @Override
                        public void onServiceDisconnected(ComponentName componentName) {
                            //几乎不会调用
                        }
                    };
                    bindService(remoteServiceIntent,serviceConnection, Context.BIND_AUTO_CREATE);
                }
            }
            break;
            case R.id.remote_service_call_button:{
                if (iStudentService != null) {
                    try {
                        Student student = iStudentService.getStudentById(100);

                        Toast.makeText(this,student.toString(),Toast.LENGTH_SHORT).show();
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }

            }
            break;
            case R.id.remote_service_unbind_button:{
                if (serviceConnection != null) {
                    unbindService(serviceConnection);
                    serviceConnection = null;
                    iStudentService = null;
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
            iStudentService = null;
        }
    }
}
