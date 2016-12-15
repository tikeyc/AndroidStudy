package com.tikeyc.datastudy;

import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatDrawableManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileActivity extends AppCompatActivity {

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file);

        imageView = (ImageView) findViewById(R.id.file_imageView);
    }


    public void onClickButton(View view) throws IOException {

        switch (view.getId()){
            case R.id.button1:{

                AssetManager manager = getAssets();

                //得到inputStream 读取assets下的logo.png
                InputStream inputStream = manager.open("live_icon.jpg");
                //得到fileOutputStream /data/data/projectPackage/files/logo.png
                FileOutputStream fileOutputStream = openFileOutput("live_icon.jpg",MODE_PRIVATE);

                //边读边写 固定写法
                byte[] buffer = new byte[1024];
                int len = -1;//或0
                while ((len = inputStream.read(buffer)) != -1){
                    fileOutputStream.write(buffer,0,len);
                }
                fileOutputStream.close();
                inputStream.close();

                //
                Toast.makeText(this,"保存成功",Toast.LENGTH_SHORT).show();

            }
            break;
            case R.id.button2:{// /data/data/projectPackage/files/logo.png

                //得到图片文件路径
                   // /data/data/projectPackage/files
                String filesPath = getFilesDir().getAbsolutePath();
                String imagePath = filesPath + "/live_icon.jpg";
                //得到BitMap 读取图片
                Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
                //
                imageView.setImageBitmap(bitmap);

            }

            default:
                break;
        }
    }

}
