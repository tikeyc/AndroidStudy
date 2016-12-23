package com.tikeyc.eventstudy.customView;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tikeyc.eventstudy.R;

public class CustomViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //设置id为content的布局的子View
//        setContentView(R.layout.activity_custom_view);
        /**
         * 1.setContentView(int layoutId)
         * 2.setContentView(View view)
         *      2.1动态加载布局文件得到视图对象
         *      2.2动态创建视图对象
        * */
        //2.1动态加载布局文件得到视图对象
//        View view = View.inflate(this,R.layout.activity_custom_view,null);
        //2.2动态创建视图对象
        MyTextView textView = new MyTextView(this);
        textView.setText("custom view");
        textView.setBackgroundColor(Color.CYAN);
        setContentView(textView);


        //强制重写布局 相当于iOS中的setNeedLayout
        textView.requestLayout();
    }


    public void test() {
        //
        Window window = getWindow();
        View decorView = window.getDecorView();
        Toast.makeText(this,decorView.toString()+"--"+decorView.getClass().getSuperclass().getName(),Toast.LENGTH_LONG).show();
    }
}
