package com.tikeyc.datastudy.SQliteDemo;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.tikeyc.datastudy.R;

import java.security.PublicKey;
import java.util.ArrayList;

public class SQliteDemoActivity extends AppCompatActivity {


    private  ListView   listView;
    private  MyAdapter  myAdapter;

    private ArrayList<BlackNumberModel> listModels;

    BlackNumberModelDao blackNumberModelDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite_demo);


        initProperty();

    }



    public void initProperty() {

        blackNumberModelDao = new BlackNumberModelDao(this);

        listModels = getListModels();

        listView = (ListView) findViewById(R.id.sq_listView);

        myAdapter = new MyAdapter(this);

        myAdapter.listModels = listModels;

        listView.setAdapter(myAdapter);


        /**长按item提示ContextMenu 操作：1.更新，2.删除
         *
         * 也可以给listView添加item长按事件来实现
         * */
        addContextMenuActionToListViewIten();


    }


    //////////////////get cache data

    public ArrayList<BlackNumberModel> getListModels() {

        listModels = blackNumberModelDao.getCacheListModels();


        return listModels;
    }


    //////////////////add data action
    public void onClickAddNumber(View view) {

        final EditText editText = new EditText(this);

        new AlertDialog.Builder(this)
                .setTitle("添加黑名单")
                .setView(editText)
                .setNegativeButton("取消", null)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String number = editText.getText().toString();
                        if (number != null) {
                            BlackNumberModel blackNumberModel = new BlackNumberModel(number,"sqlite");
                            //添加到数据库
                            blackNumberModelDao.addBlackModel(blackNumberModel);
                            //添加到列表
                            listModels.add(0,blackNumberModel);
                            myAdapter.notifyDataSetChanged();
                        }
                    }
                })
                .show();

    }





    /**长按item提示ContextMenu 操作：1.更新，2.删除
     *
     * 也可以给listView添加item长按事件来实现
     * */

    public void addContextMenuActionToListViewIten() {
        listView.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {//长按item回调方法
                contextMenu.addSubMenu(0,1,0,"更新");
                contextMenu.addSubMenu(0,2,0,"删除");

            }
        });

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();//获得AdapterContextMenuInfo,以此来获得选择的listview项目

        int position = menuInfo.position;

        final BlackNumberModel blackNumberModel = listModels.get(position);

        switch (item.getItemId()) {
            case 1:{//更新

                final EditText editText = new EditText(this);
                editText.setHint(blackNumberModel.getNumber());
                new AlertDialog.Builder(this)
                        .setTitle("更新黑名单")
                        .setView(editText)
                        .setNegativeButton("取消", null)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                String newNumber = editText.getText().toString();
                                if (newNumber != null) {
                                    //更新数据库
                                    blackNumberModel.setNumber(newNumber);
                                    blackNumberModelDao.updateBlackNumberModel(blackNumberModel);
                                    //更新列表
                                    myAdapter.notifyDataSetChanged();
                                }
                            }
                        })
                        .show();

            }
            break;
            case 2:{//删除
                Integer id = Integer.parseInt(blackNumberModel.getId());
                //数据库删除
                blackNumberModelDao.deleteBalckNumberModel(id);
                //表中删除
                listModels.remove(position);
                myAdapter.notifyDataSetChanged();

            }
            break;
            default:
                break;

        }


        return super.onContextItemSelected(item);
    }
}
