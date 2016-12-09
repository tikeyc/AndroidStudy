package com.tikeyc.androidstudy;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListviewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);


        //
        @SuppressLint("WrongViewCast") ListView listView = (ListView) findViewById(R.id.ListView_listView);

        Intent intent = getIntent();
        String listViewAdapterType = intent.getStringExtra("ListViewAdapterType");

        if (listViewAdapterType.equals("ArrayAdapter")){

            String[] datas = {"sdf","sdf","sdf","sdf","sdf","sdf","sdf","sdf","sdf","sdf","sdf","sdf","sdf","sdf","sdf","sdf","sdf","sdf","sdf","sdf","sdf"};

            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,R.layout.listviewitem_textview,datas);

            listView.setAdapter(arrayAdapter);

        }else if (listViewAdapterType.equals("SimpleAdapter")){

            List<Map<String,Object>> mapList = new ArrayList<Map<String,Object>>();

            for(int i = 0;i < 20;i++){
                HashMap hashMap = new HashMap<String,Object>();
                hashMap.put("icon",android.R.drawable.star_on);
                hashMap.put("name","name--" + i);
                hashMap.put("content","content--" + i);
                mapList.add(hashMap);
            }

            String[] from = {"icon","name","content"};//对应key
            int[] to = {R.id.simpleAdapter_icon,R.id.simpleAdapter_textView1,R.id.simpleAdapter_textView2};//对应控件的id 与from中对应的key的value为该控件的赋值

            SimpleAdapter simpleAdapter = new SimpleAdapter(this,mapList,R.layout.listview_simpleadapter,from,to);

            listView.setAdapter(simpleAdapter);
        }else if (listViewAdapterType.equals("BaseAdapter")){


            ArrayList<ShopInfoModel> shopInfoModels = new ArrayList<ShopInfoModel>();
            for(int i = 0;i < 30;i++){
                ShopInfoModel shopInfoModel = new ShopInfoModel();
                shopInfoModel.setIcon(android.R.drawable.star_off);
                shopInfoModel.setName("name----" + i);
                shopInfoModel.setContent("content----" + i);
                shopInfoModels.add(shopInfoModel);
            }
            //
            MyAdapter myAdapter = new MyAdapter();
            myAdapter.shopInfoModels = shopInfoModels;
            listView.setAdapter(myAdapter);

        }

    }


    //
    class MyAdapter extends BaseAdapter{//BaseAdapter为抽象类

        private ArrayList<ShopInfoModel> shopInfoModels;

        @Override
        public int getCount() {
            return shopInfoModels.size();
        }

        @Override
        public Object getItem(int i) {
            return shopInfoModels.get(i);
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        /*
        * view:可复用的缓存item视图对象前n+1个为null
        * viewGroup:listView对象
        * */
        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            Log.e("TAG","getView()"+"positon="+i+"view="+view);


            if (view == null){
                view = View.inflate(ListviewActivity.this, R.layout.listview_simpleadapter, null);
            }

            ImageView icon = (ImageView) view.findViewById(R.id.simpleAdapter_icon);
            TextView name_textView = (TextView) view.findViewById(R.id.simpleAdapter_textView1);
            TextView content_textView = (TextView) view.findViewById(R.id.simpleAdapter_textView2);

            ShopInfoModel shopInfoModel = shopInfoModels.get(i);

            icon.setImageResource(shopInfoModel.getIcon());
            name_textView.setText(shopInfoModel.getName());
            content_textView.setText(shopInfoModel.getContent());

            return view;
        }
    }
}
