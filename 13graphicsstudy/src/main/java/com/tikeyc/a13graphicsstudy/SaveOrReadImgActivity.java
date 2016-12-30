package com.tikeyc.a13graphicsstudy;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.io.FileNotFoundException;

public class SaveOrReadImgActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_or_read_img);

        ImageView toSaveImg = (ImageView) findViewById(R.id.to_save_imageView);
        //加载资源文件中的图片
        toSaveImg.setImageResource(R.drawable.live_icon);

        ImageView toReadImg = (ImageView) findViewById(R.id.to_read_imageView);
        //加载存储空间中的图片资源
        Bitmap bitmap = BitmapFactory.decodeFile("/storage/sdcard/ic_launcher.png");
        toReadImg.setImageBitmap(bitmap);

    }



    public void onClickButtonAction(View view) throws FileNotFoundException {
        //保存bitmap
        Bitmap bitmap = BitmapFactory.decodeFile("/storage/sdcard/live_icon.jpg");
        bitmap.compress(Bitmap.CompressFormat.PNG,100,openFileOutput("live_icon.jpg", Context.MODE_PRIVATE));

    }
}
