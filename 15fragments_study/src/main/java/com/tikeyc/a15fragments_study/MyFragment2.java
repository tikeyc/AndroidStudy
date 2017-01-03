package com.tikeyc.a15fragments_study;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by public1 on 2017/1/3.
 */

public class MyFragment2 extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //加载布局得到视图

        TextView textView = new TextView(getActivity());
        textView.setText("MyFragment2");
        textView.setBackgroundColor(Color.RED);

        return textView;

//        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
