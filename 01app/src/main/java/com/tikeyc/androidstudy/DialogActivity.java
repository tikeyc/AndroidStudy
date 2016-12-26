package com.tikeyc.androidstudy;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

public class DialogActivity extends AppCompatActivity {


    private int lastSelectedIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
    }



    @RequiresApi(api = Build.VERSION_CODES.N)
    public void showAlertDialog(View view) throws InterruptedException {
        int viewId = view.getId();

        switch (viewId){
            case R.id.Dialog_button1:{

                new AlertDialog.Builder(this)
                        .setTitle("提示")
                        .setMessage("确定删除数据？")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                        .show();

            }
            break;
            case R.id.Dialog_button2:{

                final String[] items = {"支付宝","微信","Apple Pay"};

                new AlertDialog.Builder(this)
                        .setTitle("支付方式")
                        .setSingleChoiceItems(items,lastSelectedIndex, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                lastSelectedIndex = i;
                                Toast.makeText(DialogActivity.this,items[i],Toast.LENGTH_SHORT).show();
//                                dialogInterface.dismiss();
                            }
                        })
                        .show();

            }
            break;
            case R.id.Dialog_button3:{
                View  customDialogView = View.inflate(this,R.layout.dialog_customview,null);

                final EditText nameTextView = (EditText) customDialogView.findViewById(R.id.dialogCustom_editText1);
                final EditText passTextView = (EditText) customDialogView.findViewById(R.id.dialogCustom_editText2);

                new AlertDialog.Builder(this)
                        .setView(customDialogView)
                        .setNegativeButton("取消",null)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(DialogActivity.this,nameTextView.getText().toString() + passTextView.getText().toString(),Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();

            }
            break;
            case R.id.Dialog_button4:{//回调方法，在主线程执行

                final ProgressDialog progressDialog = ProgressDialog.show(this,"title","message");
                new Thread(){
                    @Override
                    public void run() {//多线程
                        super.run();

                        try {
                            Thread.sleep(2000);
                        }catch (InterruptedException e){
                            e.printStackTrace();
                        }
                        //
                        progressDialog.dismiss();//方法在多线程执行，但内部使用Handler实现主线程移除dialog
                        //不能再多线程刷新UI（与iOS一样）
//                        Toast.makeText(DialogActivity.this,"加载完成",Toast.LENGTH_SHORT).show();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {//在主线程执行
                                Toast.makeText(DialogActivity.this,"加载完成",Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }.start();




            }
            break;
            case R.id.Dialog_button5:{
                final ProgressDialog progressDialog = new ProgressDialog(this);
                progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                progressDialog.show();

                //
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        int count = 20;
                        progressDialog.setMax(count);
                        for (int i = 0; i < count; i++){
                            try {
                                Thread.sleep(100);
                            }catch (InterruptedException e){
                                e.printStackTrace();
                            }
                            progressDialog.setProgress(progressDialog.getProgress() + 1);
                        }

                        progressDialog.dismiss();
                    }
                }).start();
            }
            break;
            case R.id.Dialog_button6:{
                //
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                //
                DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

                    }
                },year,month,day);
                datePickerDialog.show();

            }
            break;
            case R.id.Dialog_button7:{
                //
                Calendar calendar = Calendar.getInstance();
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minute = calendar.get(Calendar.MINUTE);
                //
                TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {

                    }
                },hour,minute,true);
                timePickerDialog.show();

            }
            break;
            default:
                break;
        }
    }
}
