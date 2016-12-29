package com.tikeyc.a12animationstudy;

import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class TestDrawableAnimationActivity extends AppCompatActivity {

    private ImageView imageView;

    private AnimationDrawable animationDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_drawable_animation);


        imageView = (ImageView) findViewById(R.id.animation_imageView);
    }



    public void onClickButtonAction(View view) {


        switch (view.getId()) {
            case R.id.start_animation_button:{
                if (animationDrawable == null) {
                    /**设置帧动画图片
                     * imageView.setImageResource(R.drawable.runing_animation_test);代码设置，或者Xml中设置android:src="@drawable/runing_animation_test"
                     * */
                    animationDrawable = (AnimationDrawable) imageView.getDrawable();
                    animationDrawable.start();
                }
            }
            break;
            case R.id.stop_animation_button:{
                if (animationDrawable != null) {
                    animationDrawable.stop();
                    animationDrawable = null;
                }
            }
            break;
            default:
                break;

        }
    }
}
