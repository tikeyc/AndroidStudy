package com.tikeyc.androidstudy.ListAndGrid;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.tikeyc.androidstudy.R;

import java.util.ArrayList;

/**
 * Created by public1 on 2016/12/12.
 */

public class GridViewCustomAdapter extends  BaseAdapter{

    private Context context;

    private ArrayList<ShopInfoModel> shopInfoModels;

    public GridViewCustomAdapter(Context context, ArrayList<ShopInfoModel> shopInfoModels) {
        super();
        this.context = context;
        this.shopInfoModels = shopInfoModels;
    }


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

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if (view == null){
            view = View.inflate(context, R.layout.gridviewitem,null);
        }

        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
        TextView textView = (TextView) view.findViewById(R.id.textView);

        ShopInfoModel shopInfoMode = shopInfoModels.get(i);
        imageView.setImageDrawable(shopInfoMode.getIconDraw());
        textView.setText(shopInfoMode.getName());

        return view;
    }
}

