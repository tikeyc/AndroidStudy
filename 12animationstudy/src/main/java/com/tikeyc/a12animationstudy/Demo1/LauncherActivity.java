package com.tikeyc.a12animationstudy.Demo1;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.RelativeLayout;

import com.tikeyc.a12animationstudy.MainActivity;
import com.tikeyc.a12animationstudy.R;

public class LauncherActivity extends Activity {

    private RelativeLayout launcherLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);


        launcherLayout = (RelativeLayout) findViewById(R.id.activity_launcher);
        //显示动画
        showAnimation();

    }


    public void showAnimation() {
        //
        RotateAnimation rotateAnimation = new RotateAnimation(0,360, Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        rotateAnimation.setDuration(2000);
        //
        AlphaAnimation alphaAnimation = new AlphaAnimation((float)0.0,(float)1.0);
        rotateAnimation.setDuration(2000);
        //
        ScaleAnimation scaleAnimation = new ScaleAnimation(0,1,0,1,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        scaleAnimation.setDuration(2000);

        //
        AnimationSet animationSet = new AnimationSet(true);
        animationSet.addAnimation(rotateAnimation);
        animationSet.addAnimation(alphaAnimation);
        animationSet.addAnimation(scaleAnimation);

        launcherLayout.startAnimation(animationSet);

        rotateAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                Log.e("TAG","onAnimationStart");
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Log.e("TAG","onAnimationEnd");

                Intent intent = new Intent(LauncherActivity.this, Guide1Activity.class);
                startActivity(intent);
                //关闭自己，以便在MainActivity不能在返回当前launcher界面
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                Log.e("TAG","onAnimationRepeat");
            }
        });

    }
}
