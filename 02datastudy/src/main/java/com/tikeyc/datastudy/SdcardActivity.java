package com.tikeyc.datastudy;

import android.content.SharedPreferences;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class SdcardActivity extends AppCompatActivity {

    private EditText nameText;
    private EditText contentText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sdcard);

        nameText = (EditText) findViewById(R.id.sd_editText1);
        contentText = (EditText) findViewById(R.id.sd_editText2);
    }


    public void onClickButton(View view) throws IOException {

        //得到SD卡的状态
        Boolean sd_status = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
        if (!sd_status) {
            Toast.makeText(this,"SD卡状态不妙",Toast.LENGTH_SHORT).show();
            return;
        }

        String fileName = nameText.getText().toString();
        String content = contentText.getText().toString();

        switch (view.getId()){
            case R.id.sd_button1:{//保存1

                //得到指定的OutputStream
                   //得到SD卡下的files路径 /storage/sdcard/Android/data/packageName/files
                String filesPath = getExternalFilesDir(null).getAbsolutePath();
                filesPath = filesPath + "/" + fileName;//fileName 此处我输入为:t.text

                FileOutputStream fileOutputStream = new FileOutputStream(filesPath);
                //写数据
                fileOutputStream.write(content.getBytes("utf-8"));
                fileOutputStream.close();

                //
                Toast.makeText(this,"保存成功",Toast.LENGTH_SHORT).show();

            }
            break;
            case R.id.sd_button2:{//读取1
                //得到指定文件的InputStream
                   //得到SD卡下的files路径 /storage/sdcard/Android/data/packageName/files
                String filesPath = getExternalFilesDir(null).getAbsolutePath();
                filesPath = filesPath + "/" + fileName;//fileName 此处我输入为:t.text
                //创建FileInputStream
                FileInputStream fileInputStream = new FileInputStream(filesPath);
                //读取数据
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                byte[] buffer = new  byte[1024];
                int len = -1;
                while ((len = fileInputStream.read(buffer)) != -1){
                    byteArrayOutputStream.write(buffer,0,len);
                }
                String fileContent = byteArrayOutputStream.toString();
                fileInputStream.close();
                byteArrayOutputStream.close();
                //
                contentText.setText(fileContent);
//                Toast.makeText(this,"文件内容：" + fileContent,Toast.LENGTH_SHORT).show();
            }
            break;

            /////////////////路径2：/storage/sdcard/xxx/  自己创建一个文件xxx 应用卸载时 不会 删除此数据
            case R.id.sd_button3:{//保存2
                //得到指定的OutputStream
                  // /storege/sdcard
                String sdPath = Environment.getExternalStorageDirectory().getAbsolutePath();
                //创建文件夹/storege/sdcard/tikeyc
                File file = new File(sdPath + "/tikeyc");
                if (!file.exists()){
                    file.mkdirs();//创建文件夹
                }
                //. /storege/sdcard/tikeyc/xxx.txt
                sdPath = sdPath + "/tikeyc/" + fileName;
                //创建输出流
                FileOutputStream fileOutputStream = new FileOutputStream(sdPath);//sdPath路径找不到文件报错而崩溃 为什么？
                //写数据
                fileOutputStream.write(content.getBytes("utf-8"));
                fileOutputStream.close();

                //
                Toast.makeText(this,"保存成功",Toast.LENGTH_SHORT).show();
            }
            break;
            case R.id.sd_button4:{//读取2

                //得到指定文件的InputStream
                //得到SD卡下的files路径 /storage/sdcard/Android/data/packageName/files
                String sdPath = Environment.getExternalStorageDirectory().getAbsolutePath();
                sdPath = sdPath + "/tikeyc/" + fileName;//fileName 此处我输入为:t.text
                //创建FileInputStream
                FileInputStream fileInputStream = new FileInputStream(sdPath);
                //读取数据
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                byte[] buffer = new  byte[1024];
                int len = -1;
                while ((len = fileInputStream.read(buffer)) != -1){
                    byteArrayOutputStream.write(buffer,0,len);
                }
                String fileContent = byteArrayOutputStream.toString();
                fileInputStream.close();
                byteArrayOutputStream.close();
                //
                contentText.setText(fileContent);
            }
            break;
            default:
                break;
        }
    }
}
