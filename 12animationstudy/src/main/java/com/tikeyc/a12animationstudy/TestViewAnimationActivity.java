package com.tikeyc.a12animationstudy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

public class TestViewAnimationActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_test_view_animation);

        imageView = (ImageView) findViewById(R.id.view_animation_imageView);

    }



    public void onClickButtonAction(View view) {


        switch (view.getId()) {
            case R.id.code_scale_button:{
                ScaleAnimation scaleAnimation = new ScaleAnimation(0.5f,1.5f,0,1, Animation.ABSOLUTE,imageView.getWidth()/2,Animation.ABSOLUTE,0);
//                scaleAnimation = new ScaleAnimation(0.5f,1.5f,0,1, Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0);
                scaleAnimation.setStartOffset(1000);//延迟1秒
                scaleAnimation.setDuration(2000);//持续2秒
                scaleAnimation.setFillBefore(true);//动画结束 还原
                //
                imageView.startAnimation(scaleAnimation);

            }
            break;
            case R.id.xml_scale_button:{
                /**
                 * 1.定义动画文件 2.加载动画文件得到动画对象3.启动动画
                 *
                 * */
                Animation scaleAnimation = AnimationUtils.loadAnimation(this,R.anim.scale_test);
                imageView.startAnimation(scaleAnimation);

            }
            break;
            //////////////////////

            case R.id.code_rotate_button:{
                //相对于自己的中心点旋转
                RotateAnimation rotateAnimation = new RotateAnimation(0,359,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
//                scaleAnimation.setStartOffset(1000);//延迟1秒
                rotateAnimation.setDuration(1000);//持续2秒
                rotateAnimation.setFillBefore(true);//动画结束 还原
                rotateAnimation.setRepeatCount(Animation.INFINITE);
                //设置变化率
//                rotateAnimation.setInterpolator(new DecelerateInterpolator());//减速
                rotateAnimation.setInterpolator(new LinearInterpolator());//线性编号（可以解决重复动画时停顿的问题）
                imageView.startAnimation(rotateAnimation);

            }
            break;
            case R.id.xml_rotate_button:{


            }
            break;
            //////////////////////

            case R.id.code_alpha_button:{
                float fromAlpha = (float) 1.0;
                float toAlpha = (float) 0.3;
                AlphaAnimation alphaAnimation = new AlphaAnimation(fromAlpha,toAlpha);
                alphaAnimation.setDuration(3000);//持续2秒
                alphaAnimation.setFillBefore(true);//动画结束 还原
                imageView.startAnimation(alphaAnimation);

            }
            break;
            case R.id.xml_alpha_button:{


            }
            break;
            //////////////////////

            case R.id.code_translation_button:{
                TranslateAnimation translateAnimation = new TranslateAnimation(Animation.ABSOLUTE,0,Animation.RELATIVE_TO_SELF,0,Animation.ABSOLUTE,1,Animation.RELATIVE_TO_SELF,1);
                translateAnimation.setDuration(2000);//持续2秒
                translateAnimation.setFillBefore(true);//动画结束 还原
                imageView.startAnimation(translateAnimation);
            }
            break;
            case R.id.xml_translation_button:{


            }
            break;
            //////////////////////

            case R.id.code_animationSet_button:{

                //
                float fromAlpha = (float) 1.0;
                float toAlpha = (float) 0.3;
                AlphaAnimation alphaAnimation = new AlphaAnimation(fromAlpha,toAlpha);
                alphaAnimation.setDuration(2000);//持续2秒
                alphaAnimation.setRepeatCount(3);
                alphaAnimation.setFillBefore(true);//动画结束 还原

                //相对于自己的中心点旋转
                RotateAnimation rotateAnimation = new RotateAnimation(0,359,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
                rotateAnimation.setDuration(3000);//持续3秒
                rotateAnimation.setRepeatCount(3);
                rotateAnimation.setFillBefore(true);//动画结束 还原


                //
                AnimationSet animationSet = new AnimationSet(true);

                animationSet.addAnimation(alphaAnimation);
                animationSet.addAnimation(rotateAnimation);

                imageView.startAnimation(animationSet);

                ////////////动画监听
                animationSet.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        Log.e("TAG","onAnimationStart");
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        Log.e("TAG","onAnimationEnd");
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                        Log.e("TAG","onAnimationRepeat");
                    }
                });

            }
            break;
            case R.id.xml_animationSet_button:{
                Animation scaleAnimation = AnimationUtils.loadAnimation(this,R.animator.animationset_test);//报错也能运行成功并实现效果
                imageView.startAnimation(scaleAnimation);

            }
            break;


            default:
                break;
        }
    }
}
