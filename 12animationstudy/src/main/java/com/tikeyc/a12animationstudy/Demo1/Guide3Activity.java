package com.tikeyc.a12animationstudy.Demo1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.tikeyc.a12animationstudy.MainActivity;
import com.tikeyc.a12animationstudy.R;

public class Guide3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide3);
    }


    public void onClickButtonAction(View view) {
        switch (view.getId()) {
            case R.id.guide3_last_button:{
                finish();
                overridePendingTransition(R.anim.left_in,R.anim.right_out);
            }
            break;
            case R.id.guide3_done_button:{
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
//                finish();
                overridePendingTransition(R.transition.bottom_in,R.animator.alpha_out);
            }
            break;
            default:
                break;
        }

    }
}
