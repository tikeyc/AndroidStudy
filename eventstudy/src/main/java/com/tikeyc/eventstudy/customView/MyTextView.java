package com.tikeyc.eventstudy.customView;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by public1 on 2016/12/22.
 */

public class MyTextView extends TextView {
    private Context context;

    //new创建对象,
    public MyTextView(Context context) {
        super(context);

        this.context = context;

        Toast.makeText(context,"MyTextView(Context context)",Toast.LENGTH_SHORT).show();
    }

    //布局文件创建对象
    public MyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);

        Toast.makeText(context,"MyTextView(Context context, AttributeSet attrs)",Toast.LENGTH_SHORT).show();
    }


    /**
     * 当使用布局文件创建对象是才会调用
     * 相当于iOS中的awakeFormNib
     * 一般重写此方法得到subView对象
     */
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        Toast.makeText(context,"onFinishInflate()",Toast.LENGTH_SHORT).show();
    }


    /**
     * 无论new或使用布局文件创建视图对象都调用
     * 一般重写此方法得到subView对象
     */
    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

        Toast.makeText(context,"onAttachedToWindow()",Toast.LENGTH_SHORT).show();
    }


    /**
     * 当系统调用mearure()中计算出视图的尺寸后调用系统onMeasure()
     * 我们重写此方法来根据我们的实际需求改变尺寸
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int measuredHeight = this.getMeasuredHeight();
        int measuredWidth = this.getMeasuredWidth();

        Toast.makeText(context,"onMeasure():" + measuredHeight + "---" + measuredWidth,Toast.LENGTH_SHORT).show();

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    /**重写主要调整subView的layout
     * 在layout过程中，如果视图位置/强制重新布局就会调用此方法
     * @param changed
     * @param left
     * @param top
     * @param right
     * @param bottom
     */
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }
}
