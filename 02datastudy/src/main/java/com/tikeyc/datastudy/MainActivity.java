package com.tikeyc.datastudy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void onClickButton(View view){
        switch (view.getId()){
            case R.id.button1:{
                /*SharedPerferences存储：单一的小数据
                * 文件类型： boolean float int long String
                * 路径 /data/data/packageName/shared_prefs/xxx.xml
                * 可以设置当前应用可以读取
                * 应用卸载时会删除此数据
                * */
                Intent intent = new Intent(this,SPActivity.class);
                startActivity(intent);
            }
            break;
            case R.id.button2:{
                /*内部file存储：较大数据或图片，可以用文件保存在手机内部
               *文件类型：任意
               * 路径：/data/data/projectPackage/files/
               *可以设置当前应用可以读取
               *应用卸载时会删除此数据
                * */
                Intent intent = new Intent(this,FileActivity.class);
                startActivity(intent);

            }
            break;
            case R.id.button3:{
                /*外部SD卡存储：数据文件（如：图片），可以报错到SD卡中
               *文件类型：任意
               * 路径1：/storage/sdcard/Android/data/packageName/files/ 应用卸载时 会 删除此数据
               * 路径2：/storage/sdcard/xxx/  自己创建一个文件xxx 应用卸载时 不会 删除此数据
               *其他应用可以读取
               *必须保证SD卡插在手机上
                * */
                Intent intent = new Intent(this,SdcardActivity.class);
                startActivity(intent);

            }
            break;
            case R.id.button4:{
                /**SQlite数据库存储
                 *
                 * */
                Intent intent = new Intent(this,SQliteActivity.class);
                startActivity(intent);

            }
            break;
            case R.id.button5:{
                /**Http远程服务器存储   须在AndroidManifest.xml中设置网络权限：android.permission.INTERNET
                 * JDK内置原生API：HttpUrlConnection
                 * Android内置的包装API：HttpClient
                 * 异步网络请求框架：Volley 、 Xutils
                 * */
                Intent intent = new Intent(this,HttpActivity.class);
                startActivity(intent);

            }
            break;
            default:
                break;
        }
    }
}
