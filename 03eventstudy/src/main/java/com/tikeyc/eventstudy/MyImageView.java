package com.tikeyc.eventstudy;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by public1 on 2016/12/22.
 */

public class MyImageView extends ImageView {

    public MyImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }



    /**分发事件
     * @param ev
     * @return
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
//        Toast.makeText(this.getContext(),"第一调用MyImageView dispatchTouchEvent",Toast.LENGTH_LONG).show();

        return super.dispatchTouchEvent(ev);
    }


    /**处理事件，没有地方消费（不能完全说是处理）某个事件时最终由此处理，相当于备胎
     * 处理事件的回调方法
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        Toast.makeText(this.getContext(),"最后调用MyImageView onTouchEvent",Toast.LENGTH_LONG).show();

        return super.onTouchEvent(event);

    }


    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        return super.dispatchKeyEvent(event);
    }

}
