package com.tikeyc.datastudy;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.SystemClock;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.w3c.dom.Text;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class HttpActivity extends AppCompatActivity {

    private EditText responseTextView;

    private TextView urlTextView;

    private File apkFilePath;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http);


        urlTextView = (TextView) findViewById(R.id.http_textView1);

        urlTextView.setText("http://116.211.167.106/api/live/aggregation?uid=133825214&interest=1");

        responseTextView = (EditText) findViewById(R.id.http_textView5);

        apkFilePath = new File(getExternalFilesDir(null),"datastudy-release.apk");

    }

    //下载APK并安装测试
    public void onClickButtonDownloadAPK(View view) {

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.show();

        new Thread(new Runnable() {

            @Override
            public void run() {

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
                            //显示下载进度
                            progressDialog.incrementProgressBy(len);//在上一次进度上加载len

                            //模拟网速慢
                            //Thread.sleep(100); 区别 此法内部实现try catch了
//                            SystemClock.sleep(100);
                        }

                        fileOutputStream.close();
                        inputStream.close();

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                progressDialog.dismiss();

                                //安装APK
                                installAPK();
                            }
                        });

                    }else {
                        progressDialog.dismiss();
                    }
                    //断开连接
                    httpURLConnection.disconnect();

                } catch (Exception e) {
                    e.printStackTrace();
                    //
                    progressDialog.dismiss();
                }

            }
        }).start();

    }

    //安装APK
    public void installAPK() {

        if (apkFilePath.exists()){
            Toast.makeText(this,"准备安装"+apkFilePath.toString(),Toast.LENGTH_LONG).show();
        }

        Intent intent = new Intent(Intent.ACTION_INSTALL_PACKAGE);
        //判断是否是AndroidN以及更高的版本
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
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


    ////////////////////////////////////////////////////////////

    /**GET请求
     * */
    public void onClickButtonGET(View view) {
        //
        switch (view.getId()) {
            case R.id.http_button1:{//测试HttpUrlConnection

                final ProgressDialog progressDialog = ProgressDialog.show(this,null,"加载中...");

                //开启分线程
                new Thread() {
                    @Override
                    public void run() {
                        super.run();

                        try {

                            String path = urlTextView.getText().toString();
                            //path = path + "?key1=value1&key2=value2"; //GET请求参数拼接
                            //
                            URL url = new URL(path);
                            //
                            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                            //
                            httpURLConnection.setRequestMethod("GET");
                            httpURLConnection.setConnectTimeout(6000);
                            httpURLConnection.setReadTimeout(6000);
                            //
                            httpURLConnection.connect();
                            //
                            int responseCode = httpURLConnection.getResponseCode();
                            if (responseCode == 200) {
                                InputStream inputStream = httpURLConnection.getInputStream();
                                //读取数据
                                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                                byte[] buffer = new  byte[1024];
                                int len = -1;
                                while ((len = inputStream.read(buffer)) != -1){
                                    byteArrayOutputStream.write(buffer,0,len);
                                }

                                final String responseContent = byteArrayOutputStream.toString();

                                byteArrayOutputStream.close();
                                inputStream.close();

                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        responseTextView.setText(responseContent);

                                        progressDialog.dismiss();
                                    }
                                });
                                //断开连接
                                httpURLConnection.disconnect();
                            }else {
                                progressDialog.dismiss();
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                            //
                            progressDialog.dismiss();
                        }

                    }
                }.start();

            }
            break;
            case R.id.http_button3:{//测试HttpClient
                //Android 已弃用HttpClient

            }
            break;
            case R.id.http_button5:{//测试Volley  Volley.jar包学习见note.txt的记录
                final ProgressDialog progressDialog = ProgressDialog.show(this,null,"加载中...");

                //创建请求队列
                RequestQueue requestQueue = Volley.newRequestQueue(this);
                //创建请求对象
                String url = urlTextView.getText().toString();
                //path = url + "?key1=value1&key2=value2"; //GET请求参数拼接

                StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {//成功回调
                        progressDialog.dismiss();
                        responseTextView.setText(s);

                    }
                },new Response.ErrorListener() {//错误回调
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        progressDialog.dismiss();

                    }
                });
                //添加到队列中
                requestQueue.add(stringRequest);


            }
            break;
            default:
                break;

        }

    }


    /**POST请求
     * */
    public void onClickButtonPOST(View view) {
        //
        switch (view.getId()) {
            case R.id.http_button2:{//测试HttpUrlConnection
                final ProgressDialog progressDialog = ProgressDialog.show(this,null,"加载中...");

                //开启分线程,与对应的上面不同
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {

                            String path = urlTextView.getText().toString();
                            //
                            URL url = new URL(path);
                            //
                            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                            //
                            httpURLConnection.setRequestMethod("POST");
                            httpURLConnection.setConnectTimeout(6000);
                            httpURLConnection.setReadTimeout(6000);
                            //
                            httpURLConnection.connect();
                            //POST请求设置POST参数
                            OutputStream outputStream = httpURLConnection.getOutputStream();
                            String data = "key1=value1&key2=value2";
                            outputStream.write(data.getBytes("utf-8"));
                            //
                            int responseCode = httpURLConnection.getResponseCode();
                            if (responseCode == 200) {
                                InputStream inputStream = httpURLConnection.getInputStream();
                                //读取数据
                                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                                byte[] buffer = new  byte[1024];
                                int len = -1;
                                while ((len = inputStream.read(buffer)) != -1){
                                    byteArrayOutputStream.write(buffer,0,len);
                                }

                                final String responseContent = byteArrayOutputStream.toString();

                                byteArrayOutputStream.close();
                                inputStream.close();

                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        responseTextView.setText(responseContent);

                                        progressDialog.dismiss();
                                    }
                                });
                                //断开连接
                                httpURLConnection.disconnect();
                            }else {
                                progressDialog.dismiss();
                            }
                            outputStream.close();

                        } catch (Exception e) {
                            e.printStackTrace();
                            //
                            progressDialog.dismiss();
                        }

                    }
                }).start();


            }
            break;
            case R.id.http_button4:{//测试HttpClient
                //Android 已弃用HttpClient

            }
            break;
            case R.id.http_button6:{//测试Volley
                final ProgressDialog progressDialog = ProgressDialog.show(this,null,"加载中...");

                //创建请求队列
                RequestQueue requestQueue = Volley.newRequestQueue(this);
                //创建请求对象
                String url = urlTextView.getText().toString();
                //path = url + "?key1=value1&key2=value2"; //GET请求参数拼接

                StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {//成功回调
                        progressDialog.dismiss();

                        responseTextView.setText(s);

                    }
                },new Response.ErrorListener() {//错误回调
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        progressDialog.dismiss();

                    }
                }){//方法外加{}

                    //重新此方法返回参数的Map作为请求体
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {

                        Map<String, String> map = new HashMap<String, String>();
//                        map.put("key1","value1");
//                        map.put("key2","value2");

                        return map;//super.getParams()
                    }
                };

                //添加到队列中
                requestQueue.add(stringRequest);

            }
            break;
            default:
                break;

        }
    }


}
