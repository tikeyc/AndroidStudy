package com.tikeyc.messageandthreadstudy;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.SystemClock;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class AsyncTaskActivity extends AppCompatActivity {

    private ProgressDialog progressDialog;

    private File apkFilePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task);

        progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);


        apkFilePath = new File(getExternalFilesDir(null),"datastudy-release.apk");
    }


    //AsyncTask使用
    public void downloadAPK (View view) {
        AsyncTask asyncTask = new AsyncTask() {
            //1.主线程显示提示视图
            @Override
            protected void onPreExecute() {

                super.onPreExecute();

                progressDialog.show();
            }

            //2.多线程请求数据
            @Override
            protected Object doInBackground(Object[] objects) {

                try {

                    String path = "http://ofw8qba61.bkt.clouddn.com/datastudy-release.apk";
                    path = "http://ofw8qba61.bkt.clouddn.com/app-release.apk";
                    //
                    URL url = new URL(path);
                    //
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    //
//                    httpURLConnection.setRequestMethod("GET");
                    httpURLConnection.setConnectTimeout(6000);
                    httpURLConnection.setReadTimeout(10000);
                    //
                    httpURLConnection.connect();
                    //
                    int responseCode = httpURLConnection.getResponseCode();
                    if (responseCode == 200) {
                        //设置最大进度
                        progressDialog.setMax(httpURLConnection.getContentLength());
                        //
                        InputStream inputStream = httpURLConnection.getInputStream();
                        //读取数据
                        FileOutputStream fileOutputStream = new FileOutputStream(apkFilePath);
                        //边度边写
                        byte[] buffer = new  byte[1024];
                        int len = -1;
                        while ((len = inputStream.read(buffer)) != -1){
                            fileOutputStream.write(buffer,0,len);
                            //显示下载进度（本不应该在多线程上）,incrementProgressBy()内部使用了handler实现了进度更新
//                            progressDialog.incrementProgressBy(len);//在上一次进度上加载len
                            /**假设进度条是自定义的UI
                             * 则在分线程中发布进度publishProgress()，在主线程中更新进度onProgressUpdate(Object[] values)
                             * */
                        publishProgress(len);

                            //模拟网速慢
                            //Thread.sleep(50); 区别 此法内部实现try catch了
//                            SystemClock.sleep(50);
                        }

                        fileOutputStream.close();
                        inputStream.close();

                    }else {
                        progressDialog.dismiss();
                    }
                    //断开连接
                    httpURLConnection.disconnect();

                } catch (Exception e) {
                    e.printStackTrace();
                }

                //
                return null;
            }

            //3.主线程更新UI
            @Override
            protected void onPostExecute(Object o) {
                super.onPostExecute(o);

                progressDialog.dismiss();

                //安装APK
                installAPK();
            }

            //在主线程中更新进度在调用publishProgress()后执行
            @Override
            protected void onProgressUpdate(Object[] values) {
                super.onProgressUpdate(values);


                Integer integer = (Integer) values[0];
                progressDialog.incrementProgressBy(integer);

            }
        };
        //执行任务
        asyncTask.execute();

    }



    //安装APK
    public void installAPK() {

        if (apkFilePath.exists()){
            Toast.makeText(this,"准备安装"+apkFilePath.toString(),Toast.LENGTH_LONG).show();
        }

        Intent intent = new Intent(Intent.ACTION_INSTALL_PACKAGE);
        //判断是否是AndroidN以及更高的版本
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            SystemClock.sleep(2000);
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            Uri contentUri = FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID + ".fileProvider", apkFilePath);
            intent.setDataAndType(contentUri, "application/vnd.android.package-archive");
        } else {

            intent.setDataAndType(Uri.fromFile(apkFilePath), "application/vnd.android.package-archive");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        startActivity(intent);

//        Intent intent = new Intent(Intent.ACTION_INSTALL_PACKAGE);
//        intent.setDataAndType(Uri.fromFile(apkFilePath),"application/vnd.android.package-archive");
//////        intent.setDataAndType(Uri.parse("file://" + apkFilePath.toString()), "application/vnd.android.package-archive");
//        startActivity(intent);

    }
}
