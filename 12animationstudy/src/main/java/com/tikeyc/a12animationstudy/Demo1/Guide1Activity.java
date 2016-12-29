package com.tikeyc.a12animationstudy.Demo1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.tikeyc.a12animationstudy.R;

public class Guide1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide1);
    }

    public void onClickButtonAction(View view) {
        Intent intent = new Intent(this,Guide2Activity.class);
        startActivity(intent);
        int enterAnim = R.anim.right_in;//要显示的Activity的动画
        int exitAnim = R.anim.left_out;//要隐藏的Activituy的动画
        overridePendingTransition(enterAnim,exitAnim);

    }
}
