package com.tikeyc.a13graphicsstudy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class TuPainTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tu_pain_test);
    }


    public void onClickButtonAction(View view) {

        switch (view.getId()) {
            case R.id.tu_pain_test_bitmap_button:{
                Intent intent = new Intent(this,SaveOrReadImgActivity.class);
                startActivity(intent);
            }
            break;
            case R.id.tu_pain_test_scale_img_button:{
                Intent intent = new Intent(this,MatrixTestActivity.class);
                startActivity(intent);
            }
            break;
            case R.id.tu_pain_test_shape_button:{
                //
            }
            break;
            case R.id.tu_pain_test_selector_drawble_button:{
                //
            }
            break;
            case R.id.tu_pain_test_selector_shape_button:{
                //
            }
            break;
            default:
                break;
        }

    }
}
