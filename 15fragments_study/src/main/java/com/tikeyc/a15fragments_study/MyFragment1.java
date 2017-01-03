package com.tikeyc.a15fragments_study;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by public1 on 2017/1/3.
 */


/**
 *添加
 MyFragment1_onAttach
 MyFragment1_onCreate
 ** MyFragment1_onCreateView
 ** MyFragment1_onActivityCreated
 MyFragment1_onStart
 MyFragment1_onResume

 *移除
 MyFragment1_onPause
 MyFragment1_onStop
 MyFragment1_onDestroyView
 MyFragment1_onDestroy
 MyFragment1_onDetach

 * */
public class MyFragment1 extends Fragment {


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        Log.e("TAG","MyFragment1_onAttach");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.e("TAG","MyFragment1_onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.e("TAG","MyFragment1_onCreateView");
        //加载布局得到视图

        TextView textView = new TextView(getActivity());
        textView.setText("MyFragment1");
        textView.setBackgroundColor(Color.CYAN);

        return textView;

//        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Log.e("TAG","MyFragment1_onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();

        Log.e("TAG","MyFragment1_onStart");
    }

    @Override
    public void onResume() {
        super.onResume();

        Log.e("TAG","MyFragment1_onResume");
    }

    @Override
    public void onPause() {
        super.onPause();

        Log.e("TAG","MyFragment1_onPause");
    }

    @Override
    public void onStop() {
        super.onStop();

        Log.e("TAG","MyFragment1_onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        Log.e("TAG","MyFragment1_onDestroyView");
    }


    @Override
    public void onDestroy() {
        super.onDestroy();

        Log.e("TAG","MyFragment1_onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();

        Log.e("TAG","MyFragment1_onDetach");
    }
}
