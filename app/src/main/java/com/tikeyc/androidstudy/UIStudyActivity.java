package com.tikeyc.androidstudy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;
import android.widget.RadioGroup;

public class UIStudyActivity extends AppCompatActivity {

    private boolean isOn = false;

    private EditText textView1;
    private Button button1;
    private ImageView imageView1;

    private CheckBox checkBox1;
    private CheckBox checkBox2;
    private CheckBox checkBox3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uistudy);


        //
        initViewAndProperty();
    }


    /////////////////////////////////////////init

    public void initViewAndProperty(){
        ////////////////////////
        textView1 = (EditText) findViewById(R.id.UIStudy_editText1);
        button1 = (Button) findViewById(R.id.UIStudy_button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = textView1.getText().toString();
                if (message.length() == 0) {
                    message = "未输入";
                }
                Toast.makeText(UIStudyActivity.this,message,Toast.LENGTH_SHORT).show();
            }
        });

        ////////////////////////ImageView
        imageView1 = (ImageView) findViewById(R.id.UIStudy_imageView1);
        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isOn){
                    imageView1.setBackgroundResource(android.R.drawable.alert_dark_frame);
                    imageView1.setImageResource(android.R.drawable.btn_star_big_on);
                }else {
                    imageView1.setBackgroundResource(android.R.drawable.alert_light_frame);
                    imageView1.setImageResource(android.R.drawable.btn_star_big_off);
                }
                isOn = !isOn;
            }
        });

        ////////////////////////checkBox
        checkBox1 = (CheckBox) findViewById(R.id.UIStudy_checkBox1);
        checkBox2 = (CheckBox) findViewById(R.id.UIStudy_checkBox2);
        checkBox3 = (CheckBox) findViewById(R.id.UIStudy_checkBox3);
        checkBox3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Toast.makeText(UIStudyActivity.this,checkBox3.getText().toString(),Toast.LENGTH_SHORT).show();
            }
        });

        Button button3 = (Button) findViewById(R.id.UIStudy_button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringBuffer message = new StringBuffer();

                if (checkBox1.isChecked()){
                    message.append(checkBox1.getText().toString()).append(" ");
                }
                if (checkBox2.isChecked()){
                    message.append(checkBox2.getText().toString()).append(" ");
                }
                if (checkBox3.isChecked()){
                    message.append(checkBox3.getText().toString());
                }

                if (message.length() == 0){
                    message.append("未选择");
                }
                Toast.makeText(UIStudyActivity.this,message,Toast.LENGTH_SHORT).show();
            }
        });

        ////////////////////////
        RadioButton radioButton1 = (RadioButton) findViewById(R.id.UIStudy_radioButton1);
        RadioButton radioButton2 = (RadioButton) findViewById(R.id.UIStudy_radioButton2);
        RadioButton radioButton3 = (RadioButton) findViewById(R.id.UIStudy_radioButton3);
        RadioGroup  radioGroup   = (RadioGroup) findViewById(R.id.UIStudy_RadioGroup1);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton radioButton = (RadioButton) findViewById(i);
                Toast.makeText(UIStudyActivity.this,radioButton.getText().toString(),Toast.LENGTH_SHORT).show();
            }
        });


        /////////////////显示 ContextMenu
        Button button4 = (Button) findViewById(R.id.UIStudy_button4);
        //长按显示
        button4.setOnCreateContextMenuListener(this);//Activity 已经实现了View.OnCreateContextMenuListener
    }


    /////////////////////OptionsMenu  请尝试点击手机底部按钮的menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        menu.addSubMenu(0,1,0,"添加");
        menu.addSubMenu(0,2,0,"删除");
        //菜单文件方式
//        MenuInflater menuInflater = getMenuInflater();
//        menuInflater.inflate(R.menu.option_menu,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        clickMenuItemAction(item);

        return super.onOptionsItemSelected(item);
    }

    /////////////////////ContextMenu
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        menu.addSubMenu(0,1,0,"添加");
        menu.addSubMenu(0,2,0,"删除");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        clickMenuItemAction(item);

        return super.onContextItemSelected(item);
    }

    public void clickMenuItemAction(MenuItem item){
        switch (item.getItemId()){
            case 1:{
                Toast.makeText(UIStudyActivity.this,item.getTitle().toString(),Toast.LENGTH_SHORT).show();
            }
            break;
            case 2:{
                Toast.makeText(UIStudyActivity.this,item.getTitle().toString(),Toast.LENGTH_SHORT).show();
            }
            break;
            default:
                break;
        }
    }

}
