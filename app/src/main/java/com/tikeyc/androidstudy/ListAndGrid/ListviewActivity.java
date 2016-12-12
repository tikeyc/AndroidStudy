package com.tikeyc.androidstudy.ListAndGrid;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.tikeyc.androidstudy.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListviewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);


        initListView();
    }

    ////////////获取手机安装的APP
    protected ArrayList<ShopInfoModel> getAllAppInfoModels(){

        ArrayList<ShopInfoModel> shopInfoModels = new ArrayList<ShopInfoModel>();
        //得到应用的packageManager
        PackageManager packageManager = getPackageManager();
        //创建一个主界面的intent
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        //得到包含应用信息的列表
        List<ResolveInfo> resolveInfos = packageManager.queryIntentActivities(intent,0);
        //遍历
        for (ResolveInfo resolveInfo : resolveInfos){
            //得到包名
            String packageName = resolveInfo.activityInfo.packageName;
            //
            Drawable icon = resolveInfo.loadIcon(packageManager);
            //
            String appName = resolveInfo.loadLabel(packageManager).toString();
            //
            ShopInfoModel shopInfoModel = new ShopInfoModel();
            shopInfoModel.setIconDraw(icon);
            shopInfoModel.setName(appName);
            shopInfoModels.add(shopInfoModel);
        }
        return shopInfoModels;
    }


    protected void initListView(){
        //
        @SuppressLint("WrongViewCast") final ListView listView = (ListView) findViewById(R.id.ListView_listView);

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

            //自定义的数据
            ArrayList<ShopInfoModel> shopInfoModels = new ArrayList<ShopInfoModel>();
            for(int i = 0;i < 30;i++){
                ShopInfoModel shopInfoModel = new ShopInfoModel();
                shopInfoModel.setIcon(android.R.drawable.star_off);
                shopInfoModel.setName("name----" + i);
                shopInfoModel.setContent("content----" + i);
                shopInfoModels.add(shopInfoModel);
            }
            //获取手机安装的APP信息
            shopInfoModels = getAllAppInfoModels();
            //
            final MyAdapter myAdapter = new MyAdapter();
            myAdapter.shopInfoModels = shopInfoModels;
            listView.setAdapter(myAdapter);


            //item点击事件
            final ArrayList<ShopInfoModel> finalShopInfoModels = shopInfoModels;
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                /*
                *adapterView:ListView也可能不是
                * view：当前行的item
                * i:intex或者position
                * l:
                * */
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    String appName = finalShopInfoModels.get(i).getName();
                    Toast.makeText(ListviewActivity.this,appName,Toast.LENGTH_SHORT);

                }
            });
            //item长按事件
            listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                    //删除数据
                    finalShopInfoModels.remove(i);
                    //刷新listView
//                    listView.setAdapter(myAdapter);//此法不行，将会重新创建item视图对象
                    myAdapter.notifyDataSetChanged();//使用所有缓存的item视图对象

                    return true;
                }
            });
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
            if (shopInfoModel.getIconDraw() != null){
                icon.setImageDrawable(shopInfoModel.getIconDraw());
            }

            //
            name_textView.setText(shopInfoModel.getName());
            content_textView.setText(shopInfoModel.getContent());

            return view;
        }
    }





}
