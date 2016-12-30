package com.tikeyc.a13graphicsstudy;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.graphics.drawable.shapes.Shape;
import android.view.View;
import android.widget.Toast;

/**
 * Created by public1 on 2016/12/29.
 */

public class MyView extends View {

    private Context context;

    private ShapeDrawable shapeDrawable;
    private Paint paint;

    public MyView(Context context) {
        super(context);

        this.context = context;

        //
        shapeDrawable = new ShapeDrawable(new OvalShape());
        shapeDrawable.getPaint().setColor(Color.RED);//指定颜色
        shapeDrawable.setBounds(100,30,300,100);//位置 画出来的是椭圆
//        shapeDrawable.setBounds(10,10,100,100);//位置 画出来的是正圆

        //
        paint = new Paint();
        paint.setColor(Color.BLACK);//字体颜色
        paint.setTextSize(20);//字体大小
        paint.setTypeface(Typeface.DEFAULT_BOLD);//粗体
        paint.setAntiAlias(true);//消除锯齿
    }

    /**
     * 当使用布局文件创建对象是才会调用
     * 相当于iOS中的awakeFormNib
     * 一般重写此方法得到subView对象
     */
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

//        Toast.makeText(context,"onFinishInflate()",Toast.LENGTH_SHORT).show();
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

//        int measuredHeight = this.getMeasuredHeight();
//        int measuredWidth = this.getMeasuredWidth();
//
//        Toast.makeText(context,"onMeasure():" + measuredHeight + "---" + measuredWidth,Toast.LENGTH_SHORT).show();

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


    /**想当于iOS中的drawInRect()
     * @param canvas 画布
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //画绿色背景
        canvas.drawColor(Color.LTGRAY);

        //画椭圆
        shapeDrawable.draw(canvas);//将自己画到canvas上

        //画文本
        canvas.drawText("画上去的圆和文字",100,200,paint);

    }


    /**
     * 视图销毁时调用(视图从父视图中remove时、所在Activity销毁时)
     */
    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }
}
