package com.tikeyc.messageandthreadstudy;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class TestHanderActivity extends AppCompatActivity {

    private EditText responseTextView;

    private ProgressDialog progressDialog;


    //创建Handler,重写handleMessage()
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {//在主线程执行
            super.handleMessage(msg);

            if (msg.what == 1) {
                String object = (String) msg.obj;
                //
                responseTextView.setText(object);

                progressDialog.dismiss();
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_hander);


        responseTextView = (EditText) findViewById(R.id.test_hander_editText1);

    }




    public void onClickButtonAction(View view) {

        switch (view.getId()) {

            case R.id.test_hander_Button1: {//不使用Hander实现异步

                progressDialog = ProgressDialog.show(this,null,"加载中...");

                //开启分线程
                new Thread() {
                    @Override
                    public void run() {
                        super.run();

                        try {

                            String path = "http://116.211.167.106/api/live/aggregation?uid=133825214&interest=1";
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
            case R.id.test_hander_Button2: {//使用Hander实现异步
                //主线程显示ProgressDialog
                progressDialog = ProgressDialog.show(this,null,"加载中...");
                //分线程，加载数据
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String path = "http://116.211.167.106/api/live/aggregation?uid=133825214&interest=1";
                        //
                        try {

                            String result = path.toString();

                            Message message = Message.obtain();
//                            Message.obtain(handler,what,arg1,arg2,obj);
                            message.what = 1;
                            message.obj = result;

                            //使用handler对象发送Message
//                            handler.sendMessage(message);
                            handler.sendMessageDelayed(message,2000);

                        } catch (Exception e){
                            e.printStackTrace();
                        } finally {

                        }

                    }
                }).start();

            }
            break;
            default:
                break;

        }

    }
}
