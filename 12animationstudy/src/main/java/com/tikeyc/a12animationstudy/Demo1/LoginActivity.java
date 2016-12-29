package com.tikeyc.a12animationstudy.Demo1;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.tikeyc.a12animationstudy.MainActivity;
import com.tikeyc.a12animationstudy.R;

public class LoginActivity extends AppCompatActivity {

    private EditText editText;

    private ProgressBar myProgressBar;

    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editText = (EditText) findViewById(R.id.login_editText);

        //自定义的ProgressBar my_progress.xml
        myProgressBar = (ProgressBar) findViewById(R.id.main_custom_progressBar);

    }

    @Override
    protected void onResume() {
        super.onResume();
        myProgressBar.setProgress(0);
    }

    public void hideKeyboard(View v) {
        InputMethodManager imm = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }

    public void loginButtonAcion(View view) {

        if (editText.getText().length() == 0) {
            Animation animation = AnimationUtils.loadAnimation(this,R.transition.shake);
            editText.startAnimation(animation);

        } else {
            hideKeyboard(editText);
            //
            Button button = (Button) view;
            if (loginButton == null)loginButton = button;
            loginButton.setEnabled(false);
            //
            showProgerssBar();
        }

    }


    public void showProgerssBar(){

        //
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected Void doInBackground(Void... voids) {

                int count = 30;
                myProgressBar.setMax(count);
                for (int i = 0; i < count; i++) {
                    SystemClock.sleep(50);//模拟网络 休息50毫秒
                    publishProgress();
                }


                return null;
            }

            @Override
            protected void onProgressUpdate(Void... values) {
                super.onProgressUpdate(values);
                //
                myProgressBar.incrementProgressBy(1);
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                //
                loginButton.setEnabled(true);
            }
        }.execute();

    }
}
