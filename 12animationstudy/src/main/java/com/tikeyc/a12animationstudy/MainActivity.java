package com.tikeyc.a12animationstudy;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    public void onClickButtonAction(View view) {


        switch (view.getId()) {
            case R.id.main_view_animation_button:{
                Intent intent = new Intent(this,TestViewAnimationActivity.class);
                startActivity(intent);

            }
            break;
            case R.id.main_drawable_animation_button:{
                Intent intent = new Intent(this,TestDrawableAnimationActivity.class);
                startActivity(intent);

            }
            break;

            default:
                break;
        }
    }
}
