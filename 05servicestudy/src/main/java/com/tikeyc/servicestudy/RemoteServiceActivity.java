package com.tikeyc.servicestudy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


/**
 * Remote Service: 两个应用A,B间的通信
 * 使用AIDL(Android Interface Definition Language) 启动另一个APP进程的服务，在不同的进程间传递数据对象
 */
public class RemoteServiceActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remote_service);

    }


    public void onClickButtonAction(View view) {
        switch (view.getId()) {
            case R.id.remote_service_bind_button:{//绑定远程服务

            }
            break;
            case R.id.remote_service_call_button:{


            }
            break;
            case R.id.remote_service_unbind_button:{

            }
            break;
            default:
                break;

        }
    }

}
