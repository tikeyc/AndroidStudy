package com.tikeyc.serviceplaymusic;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by public1 on 2016/12/26.
 */


/**
 * 需要在AndroidManifest.xml中加入Service标签
 *
 * */
public class MusicService extends Service {
    /**startService()
     * 第一次调用：MyService()->onCreate()->onStartCommand()
     * 以后再次调用：->onStartCommand()
     * stopService()后:onDestroy()
     * */
    /**bindService()
     * 第一次调用：MyService()->onCreate()->onBind()->onServiceConnected()[ServiceConnection中的回调方法]
     * onbindService():只有当前Activity与Service连接->onUnbind()->onDestroy()
     * */

    private MediaPlayer mediaPlayer;

    public MusicService() {

    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    /**每次都会调用
     * @param intent
     * @param flags
     * @param startId
     * @return
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        String action = intent.getStringExtra("action");

        if (action.equals("play")) {

            playMusic();

        } else if (action.equals("stop")) {
            stopMusic();

        } else if (action.equals("pause")) {
            pauseMusic();

        }
//        else if (action.equals("exit")) {
//            exitMusic();
//        }

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

        exitMusic();
    }



    ////////////////////////////////////


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

    }
}
