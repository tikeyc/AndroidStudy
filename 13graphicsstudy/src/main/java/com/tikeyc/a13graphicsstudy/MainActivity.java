package com.tikeyc.a13graphicsstudy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.tikeyc.a13graphicsstudy.Demo1.ListAndGrid.ListviewActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickButtonAction(View view) {

        switch (view.getId()) {
            case R.id.main_handle_img_button:{
                Intent intent = new Intent(this,TuPainTestActivity.class);
                startActivity(intent);
            }
            break;
            case R.id.main_pain_img_button:{
                Intent intent = new Intent(this,DrawViewTestActivity.class);
                startActivity(intent);
            }
            break;
            case R.id.main_demo1_button:{
                Intent intent = new Intent(this,ListviewActivity.class);
                startActivity(intent);
            }
            break;
            default:
                break;
        }

    }
}
