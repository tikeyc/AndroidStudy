package com.tikeyc.messageandthreadstudy.ListView;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;
import com.tikeyc.messageandthreadstudy.R;

import java.util.ArrayList;

import circleImageView.CircleImageView;

/**
 * Created by public1 on 2016/12/20.
 */

public class MyAdapter extends BaseAdapter {

    public ArrayList<LiveInfoModel> liveInfoModels;

    public int screenWidth;

    Context context;

    public MyAdapter(Context context) {
        this.context = context;

        //
        ListViewActivity listViewActivity = (ListViewActivity) context;
        WindowManager manager = listViewActivity.getWindowManager();
        DisplayMetrics outMetrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(outMetrics);
        screenWidth = outMetrics.widthPixels;
        int screenHeight = outMetrics.heightPixels;
    }

    @Override
    public int getCount() {
        return liveInfoModels.size();
    }

    @Override
    public Object getItem(int i) {
        return liveInfoModels.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if (view == null) {
            view = View.inflate(context, R.layout.livelistitem,null);
            //自定义设置item的高度
            AbsListView.LayoutParams param = new AbsListView.LayoutParams(screenWidth - 100,screenWidth + 50);
            view.setLayoutParams(param);
        }

        //
        final CircleImageView iconImgView = (CircleImageView) view.findViewById(R.id.icon_imageView);
        TextView nickTV = (TextView)  view.findViewById(R.id.nick_textView);
        TextView cityTV = (TextView)  view.findViewById(R.id.city_textView);
        TextView online_usersTV = (TextView)  view.findViewById(R.id.online_users_textView);
        final ImageView portraitImgView = (ImageView) view.findViewById(R.id.portrait_imageView);

        //
        LiveInfoModel liveInfoModel = liveInfoModels.get(i);
        String imgUrl = "http://img.meelive.cn/" + liveInfoModel.creator.portrait;
        //Picasso加载图片
        Picasso.with(context).load(imgUrl).placeholder(null).into(iconImgView);
        //
        nickTV.setText(liveInfoModel.creator.nick);
        cityTV.setText(liveInfoModel.city);
        online_usersTV.setText(liveInfoModel.online_users);
        //Picasso加载图片
        Picasso.with(context).load(imgUrl).placeholder(null).into(portraitImgView);

        return view;
    }
}
