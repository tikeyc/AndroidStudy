package com.tikeyc.a15fragments_study;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.tikeyc.a15fragments_study.Demo1.TitleFragmentActivity;

/**Fragment
 *
 * Android3.0开始新增，意为碎片(使用android.support.v4.app.Fragment)
 * 用来组件Activity界面的局部模块，也可以说一个Activity由多个Fragment组成
 * 一个fragment必须总是嵌入在一个activity中
 * 本质上会产生一个FrameLayout它加载的布局为其子布局
 */
public class MainActivity extends FragmentActivity {

    private MyFragment1 myFragment1;
    private MyFragment2 myFragment2;

    public MainActivity() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }



    public void onClickButtonAction(View view) {
        //            MyFragment2 myFragment2 = new MyFragment2();

        //
        FragmentManager fragmentManager = getSupportFragmentManager();
        //
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (view.getId() == R.id.fragment_add_button) {
            //
            if (myFragment1 == null) {
                myFragment1 = new MyFragment1();

                //将当前操作添加到回退栈，执行此方法将出现：点击手机下面back回到上一个状态
                fragmentTransaction.addToBackStack(null);

                //
                fragmentTransaction.add(R.id.Fragment_Contaimer,myFragment1);
                fragmentTransaction.commit();
            }


        } else if (view.getId() == R.id.fragment_remove_button) {

            if (myFragment1 != null) {
                fragmentTransaction.remove(myFragment1);
                fragmentTransaction.commit();
                myFragment1 = null;
            }

        }
    }


    public void goToDemo(View view) {

        Intent intent = new Intent(this, TitleFragmentActivity.class);
        startActivity(intent);
    }
}
