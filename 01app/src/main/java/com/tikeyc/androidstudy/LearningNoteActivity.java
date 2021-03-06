package com.tikeyc.androidstudy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/*学习笔记
* 常见异常：
*     1.NullPointerException  空指针（iOS我常称为 野指针）
*     原因：调用对象的方法/属性 但对象为null
*     2.ClassCastException
*     原因：类型转换错误
*     3.ActivityNotFoundException
*     原因：没有注册Activity或注册不正确
* 基本常见异常的一般分析步骤：
*     1.LogCate 最好先找到 Caused by是何种异常导致的
*     2.找到出异常的类及行号 点击进入对应代码
* */
public class LearningNoteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learning_note);
    }
}
