package com.tikeyc.messageandthreadstudy.ListView;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tikeyc.messageandthreadstudy.R;

import org.json.JSONObject;

import java.lang.reflect.Type;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Map;

public class ListViewActivity extends AppCompatActivity {

    public ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        WindowManager manager = this.getWindowManager();
        DisplayMetrics outMetrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(outMetrics);
        int screenWidth = outMetrics.widthPixels;
        int screenHeight = outMetrics.heightPixels;

        listView = (ListView) findViewById(R.id.live_listView);

        //get data
        getLiveData();

    }


    /**
     * 获取直播列表
     */
    private void getLiveData() {

        final ProgressDialog progressDialog = ProgressDialog.show(this,null,"加载中...");

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        String url = "http://116.211.167.106/api/live/aggregation?uid=133825214&interest=1";


        MyStringRequest stringRequest = new MyStringRequest(Request.Method.GET,url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {

                Gson gson = new Gson();
                LiveModel liveModel = gson.fromJson(s,LiveModel.class);

                progressDialog.dismiss();

//                Toast.makeText(ListViewActivity.this,liveModel.lives.get(0).creator.portrait,Toast.LENGTH_LONG).show();
                refreshListView(liveModel);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

                volleyError.printStackTrace();
                Toast.makeText(ListViewActivity.this,volleyError.getLocalizedMessage(),Toast.LENGTH_LONG).show();

                progressDialog.dismiss();

            }
        });

        requestQueue.add(stringRequest);

    }

    /**刷新列表
     * @param liveModel
     */
    public void refreshListView(LiveModel liveModel) {
        MyAdapter myAdapter = new MyAdapter(this);

        myAdapter.liveInfoModels = liveModel.lives;

        listView.setAdapter(myAdapter);

    }

}
