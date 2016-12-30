package com.tikeyc.a13graphicsstudy.Demo1.ListAndGrid;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.tikeyc.a13graphicsstudy.R;

import java.util.ArrayList;
import java.util.List;

public class ListviewActivity extends AppCompatActivity implements View.OnClickListener {

    private PopupWindow popupWindow;
    private View popupWindow_contentView;
    private ScaleAnimation scaleAnimation;

    private  int lastClickItemIndex;

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

        //获取手机安装的APP信息
        ArrayList<ShopInfoModel> shopInfoModels = shopInfoModels = getAllAppInfoModels();

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
//                Toast.makeText(ListviewActivity.this,appName,Toast.LENGTH_SHORT).show();
                Log.e("TAG",appName);
                if (popupWindow == null) {
                    popupWindow_contentView = View.inflate(ListviewActivity.this,R.layout.popupwindow_layout,null);
                    popupWindow = new PopupWindow(popupWindow_contentView,view.getWidth() - 80,view.getHeight());
//                    popupWindow.setBackgroundDrawable(new BitmapDrawable());

                    LinearLayout linearLayout1 = (LinearLayout) popupWindow_contentView.findViewById(R.id.ios_button);
                    linearLayout1.setOnClickListener(ListviewActivity.this);

                    LinearLayout linearLayout2 = (LinearLayout) popupWindow_contentView.findViewById(R.id.android_button);
                    linearLayout2.setOnClickListener(ListviewActivity.this);

                    LinearLayout linearLayout3 = (LinearLayout) popupWindow_contentView.findViewById(R.id.htnl_button);
                    linearLayout3.setOnClickListener(ListviewActivity.this);
                }
                if (popupWindow.isShowing()) {
                    popupWindow.dismiss();//还存在内存中
                }
                popupWindow.showAsDropDown(view,40,-view.getHeight());
                //
                if (scaleAnimation == null) {
                    scaleAnimation = new ScaleAnimation(0,1,0,1, Animation.RELATIVE_TO_SELF,0.0f,Animation.RELATIVE_TO_SELF,0.0f);
                    scaleAnimation.setDuration(1000);
                }
                popupWindow_contentView.startAnimation(scaleAnimation);

                lastClickItemIndex = i;

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
        //滑动监听
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            /**
             * @param absListView 表示ListView
             * @param i 状态
             *          SCROLL_STATE_IDLE:空闲
             *          SCROLL_STATE_FLING：快速滚动
             *          SCROLL_STATE_TOUCH_SCROLL：跟着手指滚动
             */
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {

                if (i == SCROLL_STATE_IDLE) {

                } else if (i == SCROLL_STATE_FLING) {

                } else if (i == SCROLL_STATE_TOUCH_SCROLL) {
                    if (popupWindow != null && popupWindow.isShowing()) {
                        popupWindow.dismiss();//还存在内存中
                    }
                }

            }

            /** 当ListView正在滚动中 持续调用
             * @param absListView
             * @param i 第一个看见的item下标
             * @param i1
             * @param i2
             */
            @Override
            public void onScroll(AbsListView absListView, int i, int i1, int i2) {
                Log.e("TAG",i1+"--"+i2);
                if (lastClickItemIndex == i) {

                }
            }
        });

    }

    @Override
    public void onClick(View view) {
        Toast.makeText(this,"onClick",Toast.LENGTH_SHORT).show();
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
