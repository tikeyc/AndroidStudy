package com.tikeyc.serviceplaymusic;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity1 extends AppCompatActivity {

    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }



    public void onClickButtonAction(View view) {
        switch (view.getId()) {
            case R.id.main_play_button:{
                playMusic();
            }
            break;
            case R.id.main_stop_button:{
                stopMusic();

            }
            break;
            case R.id.main_pause_button:{
                pauseMusic();

            }
            break;
            case R.id.main_exit_button:{
                exitMusic();
            }
            break;
            default:
                break;

        }
    }




    public void playMusic() {
        if (mediaPlayer == null) {

            mediaPlayer = MediaPlayer.create(this,R.raw.giveyour);

        }
        if (mediaPlayer != null) mediaPlayer.start();
    }

    public void stopMusic() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();//停止
            mediaPlayer.reset();//重置
            mediaPlayer.release();//释放资源
            mediaPlayer = null;
        }
    }

    public void pauseMusic() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }
    }

    public void exitMusic() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();//停止
            mediaPlayer.reset();//重置
            mediaPlayer.release();//释放资源
            mediaPlayer = null;
        }
        finish();//退出APP
    }
}
