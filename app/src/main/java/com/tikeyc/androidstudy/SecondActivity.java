package com.tikeyc.androidstudy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class SecondActivity extends AppCompatActivity {

    private EditText textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        EditText textView1 = (EditText) findViewById(R.id.main2_editText1);

        Intent intent = getIntent();
        String inputMessage = intent.getStringExtra("main_message");
        textView1.setText(inputMessage);

        textView2 = (EditText) findViewById(R.id.main2_editText2);

    }


    public void backButtonAction(View view){
        //一般返回
//        finish();
        //带数据返回
        int resultCode = 3;
        Intent intent = new Intent();
        intent.putExtra("Result",textView2.getText().toString());
        setResult(resultCode,intent);

        finish();
    }
}
