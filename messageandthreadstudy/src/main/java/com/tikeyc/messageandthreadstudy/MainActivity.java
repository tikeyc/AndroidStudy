package com.tikeyc.messageandthreadstudy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }



    public void onClickButtonAction(View view) {

        switch (view.getId()) {

            case R.id.message_main_button1: {
                Intent intent = new Intent(this,TestHanderActivity.class);
                startActivity(intent);

            }
            break;
            case R.id.message_main_button2: {
                Intent intent = new Intent(this,HandlerDemoActivity.class);
                startActivity(intent);
            }
            break;
            case R.id.message_main_button3: {
                Intent intent = new Intent(this,AsyncTaskActivity.class);
                startActivity(intent);

            }
            break;
            case R.id.message_main_button4: {
                Intent intent = new Intent(this,TestJsonActivity.class);
                startActivity(intent);

            }
            break;
            default:
                break;

        }

    }
}
