package com.tikeyc.serviceplaymusic;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }



    public void onClickButtonAction(View view) {

        Intent intent = new Intent(this,MusicService.class);

        switch (view.getId()) {
            case R.id.main_play_button:{
                intent.putExtra("action","play");
                startService(intent);

            }
            break;
            case R.id.main_stop_button:{
                intent.putExtra("action","stop");
                startService(intent);

            }
            break;
            case R.id.main_pause_button:{
                intent.putExtra("action","pause");
                startService(intent);

            }
            break;
            case R.id.main_exit_button:{
//                intent.putExtra("action","exit");
                stopService(intent);//将调用MusicService的onDestroy()

                finish();//退出APP
            }
            break;
            default:
                break;

        }
    }





}
