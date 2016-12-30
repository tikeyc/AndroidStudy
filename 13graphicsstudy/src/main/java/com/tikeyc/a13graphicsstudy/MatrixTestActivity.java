package com.tikeyc.a13graphicsstudy;

import android.content.Intent;
import android.graphics.Matrix;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class MatrixTestActivity extends AppCompatActivity {

    private EditText scale_editText1;
    private EditText rotate_editText2;
    private EditText translateX_editText3;
    private EditText translateY_editText4;

    private ImageView matrixImgView;

    private Matrix matrix;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matrix_test);

        scale_editText1 = (EditText) findViewById(R.id.matrix_editText1);
        rotate_editText2 = (EditText) findViewById(R.id.matrix_editText2);
        translateX_editText3 = (EditText) findViewById(R.id.matrix_editText3);
        translateY_editText4 = (EditText) findViewById(R.id.matrix_editText4);

        matrixImgView = (ImageView) findViewById(R.id.matrix_imageView);
        matrixImgView.setScaleType(ImageView.ScaleType.MATRIX);//必须设置

        matrix = new Matrix();

    }




    public void onClickButtonAction(View view) {

        switch (view.getId()) {
            case R.id.matrix_scale_button:{
                float scale = Float.parseFloat(scale_editText1.getText().toString());
                float sx = scale;
                float sy = scale;
                matrix.postScale(sx,sy);
                //
                matrixImgView.setImageMatrix(matrix);
            }
            break;
            case R.id.matrix_rotate_button:{
                float degrees = Float.parseFloat(rotate_editText2.getText().toString());
//                matrix.postRotate(degrees,px,py);
                matrix.postRotate(degrees);
                //
                matrixImgView.setImageMatrix(matrix);

            }
            break;
            case R.id.matrix_trasition_button:{
                float offSet_X = Float.parseFloat(translateX_editText3.getText().toString());
                float offSet_Y = Float.parseFloat(translateY_editText4.getText().toString());
                matrix.postTranslate(offSet_X,offSet_Y);
                //
                matrixImgView.setImageMatrix(matrix);

            }
            break;
            case R.id.matrix_reset_button:{
                matrix.reset();
                //
                matrixImgView.setImageMatrix(matrix);

            }
            break;
            default:
                break;
        }

    }
}
