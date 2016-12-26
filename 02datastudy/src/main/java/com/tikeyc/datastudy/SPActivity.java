package com.tikeyc.datastudy;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SPActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;

    private EditText keyText;
    private EditText valueText;

    private static String spStorageName = "tikeyc_sp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sp);

        keyText = (EditText) findViewById(R.id.editText1);
        valueText = (EditText) findViewById(R.id.editText2);

        /*
        *tikeyc_sp 路径/data/data/packageName/shared_prefs/tikeyc_sp.xml
        * Context.MODE_PRIVATE私有的
        * */
        sharedPreferences = getSharedPreferences(spStorageName, Context.MODE_PRIVATE);
    }


    public void onClickButton(View view){

        String key = keyText.getText().toString();

        switch (view.getId()){
            case R.id.button1:{

                SharedPreferences.Editor editor = sharedPreferences.edit();


                String value = valueText.getText().toString();

                editor.putString(key,value);

//                editor.remove(key);

                editor.commit();//必须调用

                Toast.makeText(this,"保存成功",Toast.LENGTH_SHORT).show();

            }
            break;
            case R.id.button2:{

                String message = "没有找到";
                /*
                *key：tikeyc_sp.xml中的key
                * message:当没有找到key对应的value时将返回该值
                * */
                String value = sharedPreferences.getString(key,message);

                valueText.setText(value);
            }
            break;
            default:
                break;
        }
    }
}
