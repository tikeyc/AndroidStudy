package com.tikeyc.contentproviderstudy;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by public1 on 2016/12/27.
 */

public class DBHelper extends SQLiteOpenHelper {

    public static String sqDBName = "person";

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "create table " + sqDBName + " (_id integer primary key autoincrement, number varchar, name varchar)";
        sqLiteDatabase.execSQL(sql);

        /**插入一些初始化数据，也可以不插入*/
        sqLiteDatabase.execSQL("insert into " + sqDBName + " (name) values ('tikeyc')");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
