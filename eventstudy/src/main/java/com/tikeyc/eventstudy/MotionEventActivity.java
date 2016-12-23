package com.tikeyc.eventstudy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MotionEventActivity extends AppCompatActivity {

    private int lastX;
    private int lastY;

    private RelativeLayout parentView;
    private int parentMaxRight;
    private int parentMaxBottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motion_event);



        MyImageView myImageView = (MyImageView) findViewById(R.id.motion_imageView1);
        //
        parentView = (RelativeLayout) myImageView.getParent();
        /*此处调用获取到的值为0
        parentMaxRight = parentView.getRight();
        parentMaxBottom = parentView.getBottom();
        * */

        //
        myImageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
//                Toast.makeText(MotionEventActivity.this,"第二调用OnTouchListener setOnTouchListener",Toast.LENGTH_LONG).show();

                int eventX = (int) motionEvent.getRawX();
                int eventY = (int) motionEvent.getRawY();

                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:{
                        Toast.makeText(MotionEventActivity.this,"ACTION_DOWN...",Toast.LENGTH_LONG).show();

                        //得到父师徒的
                        if (parentMaxRight == 0) {
                            parentMaxRight = parentView.getRight();
                            parentMaxBottom = parentView.getBottom();
                        }

                        //记录第一次
                        lastX = eventX;
                        lastY = eventY;
                    }
                    break;
                    case MotionEvent.ACTION_MOVE:{
                        Toast.makeText(MotionEventActivity.this,"ACTION_MOVE...",Toast.LENGTH_LONG).show();

                        int dx = eventX - lastX;
                        int dy = eventY - lastY;
                        //
                        int left = view.getLeft();
                        int top = view.getTop();
                        int right = view.getRight();
                        int bottom = view.getBottom();
                        //
                        if (left < 0) {
                            right -= left;
                            left = 0;
                        }
                        if (top < 0) {
                            bottom -= top;
                            top = 0;
                        }
                        if (right > parentMaxRight) {
                            left -= right - parentMaxRight;
                            right = parentMaxRight;
                        }
                        if (bottom > parentMaxBottom) {
                            top -= bottom - parentMaxBottom;
                            bottom = parentMaxBottom;
                        }
                        //
                        view.layout(left + dx,top + dy,right + dx,bottom + dy);
                        //移动过程中重置
                        lastX = eventX;
                        lastY = eventY;
                    }
                    break;
                    case MotionEvent.ACTION_UP:{
                        Toast.makeText(MotionEventActivity.this,"ACTION_UP...",Toast.LENGTH_LONG).show();
                    }
                    break;
                    default:
                        break;
                }

                return true;///消费 true:所有事件都交给ImageView处理所以返回true,无需再传递下去;未消费false
            }
        });

    }


    /**分发事件
     * @param ev
     * @return
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
//        Toast.makeText(this,"首先调用，最后事件结束后调用MotionEventActivity dispatchTouchEvent",Toast.LENGTH_LONG).show();

        return super.dispatchTouchEvent(ev);
    }

    /**处理：调用了回调方法；消费：return true  只要有一个地方return true了被消费了事件就不会再继续传递下去
     * 处理事件，没有地方消费（不能完全说是处理）某个事件时最终由此处理，相当于备胎
     * 处理事件的回调方法
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        Toast.makeText(this,"事件结束后最后调用MotionEventActivity onTouchEvent",Toast.LENGTH_LONG).show();

        return super.onTouchEvent(event);

    }
}
