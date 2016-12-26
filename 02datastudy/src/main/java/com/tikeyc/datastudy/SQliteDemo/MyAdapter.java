package com.tikeyc.datastudy.SQliteDemo;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.tikeyc.datastudy.R;
import com.tikeyc.datastudy.SQliteDemo.BlackNumberModel;

import java.util.ArrayList;

/**
 * Created by public1 on 2016/12/14.
 */

public class MyAdapter extends BaseAdapter {

    Context context;

    public ArrayList<BlackNumberModel> listModels;

    public MyAdapter (Context context) {

        this.context = context;
    }

    @Override
    public int getCount() {
        return listModels.size();
    }

    @Override
    public Object getItem(int i) {
        return listModels.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if (view == null) {
            view = View.inflate(context, R.layout.sqlistitem,null);
        }

        TextView textView = (TextView) view.findViewById(R.id.sq_textView1);

        BlackNumberModel blackNumberModel = listModels.get(i);

        textView.setText(blackNumberModel.getNumber());

        return view;
    }
}
