package com.tikeyc.a12animationstudy.Demo1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.tikeyc.a12animationstudy.R;

public class Guide2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide2);
    }


    public void onClickButtonAction(View view) {
        switch (view.getId()) {
            case R.id.guide2_last_button:{
                finish();
                overridePendingTransition(R.anim.left_in,R.anim.right_out);
            }
            break;
            case R.id.guide2_next_button:{
                Intent intent = new Intent(this,Guide3Activity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.right_in,R.anim.left_out);
            }
            break;
            default:
                break;
        }

    }
}
