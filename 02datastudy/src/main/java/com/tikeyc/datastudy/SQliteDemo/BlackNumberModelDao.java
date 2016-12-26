package com.tikeyc.datastudy.SQliteDemo;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.tikeyc.datastudy.SQlite.MySQliteOpenHelper;

import java.util.ArrayList;

/**
 * Created by public1 on 2016/12/14.
 */

public class BlackNumberModelDao {

    private Context context;

    private MySQliteOpenHelper mySQliteOpenHelper;

    public BlackNumberModelDao(Context context) {
        this.context = context;

        mySQliteOpenHelper = new MySQliteOpenHelper(context,MySQliteOpenHelper.sqDBName_Demo,1,true);
    }

    //曾
    public void addBlackModel(BlackNumberModel blackNumberModel) {

        //获取连接
        SQLiteDatabase sqLiteDatabase = mySQliteOpenHelper.getReadableDatabase();
        //
        ContentValues contentValues = new ContentValues();
        contentValues.put("number",blackNumberModel.getNumber());
        contentValues.put("name",blackNumberModel.getName());
        long id = sqLiteDatabase.insert(MySQliteOpenHelper.sqDBName_Demo,null,contentValues);
        blackNumberModel.setId(id + "");//+"" 转字符串
        //关闭
        sqLiteDatabase.close();
        //
        Toast.makeText(context,"插入成功 id:" + id,Toast.LENGTH_SHORT).show();

    }

    //改
    public void updateBlackNumberModel(BlackNumberModel blackNumberModel) {

        SQLiteDatabase sqLiteDatabase = mySQliteOpenHelper.getReadableDatabase();
        //
        ContentValues contentValues = new ContentValues();
        contentValues.put("number",blackNumberModel.getNumber());
        contentValues.put("name",blackNumberModel.getName());
        int i = sqLiteDatabase.update(MySQliteOpenHelper.sqDBName_Demo,contentValues,"_id=?",new String[]{blackNumberModel.getId()});
        //
        sqLiteDatabase.close();

    }

    //查
    public ArrayList<BlackNumberModel> getCacheListModels() {

        ArrayList<BlackNumberModel> listModels = new ArrayList<BlackNumberModel>();

        SQLiteDatabase sqLiteDatabase = mySQliteOpenHelper.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.query(MySQliteOpenHelper.sqDBName_Demo,null,null,null,null,null,"_id desc");//orderBy "_id desc" 根据_id倒序查询,需要将新添加放在第一个显示

        while (cursor.moveToNext()) {
            Integer id = cursor.getInt(cursor.getColumnIndex("_id"));
            String numberValue = cursor.getString(cursor.getColumnIndex("number"));//根据表中的key值number得到对应的value在第几列
            String nameValue = cursor.getString(cursor.getColumnIndex("name"));

            BlackNumberModel blackNumberModel = new BlackNumberModel(numberValue,nameValue);
            blackNumberModel.setId(id + "");

            listModels.add(blackNumberModel);
        }

        return listModels;
    }


    public void deleteBalckNumberModel(Integer id) {

        SQLiteDatabase sqLiteDatabase = mySQliteOpenHelper.getReadableDatabase();
        //
        sqLiteDatabase.delete(MySQliteOpenHelper.sqDBName_Demo,"_id=" + id,null);

        //
        sqLiteDatabase.close();

    }
}
