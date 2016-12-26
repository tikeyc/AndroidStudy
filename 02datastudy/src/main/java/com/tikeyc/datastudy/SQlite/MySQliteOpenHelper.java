package com.tikeyc.datastudy.SQlite;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by public1 on 2016/12/14.
 */


/**SQLiteDatabase
 *long insert() 返回id值 自增长
 * int update()
 * int delete()
 * query() 返回 Cursor数据集
 *      口口口口口
 *      口口口口口
 *      口口口口口
 *     Cursor：
 *      int getCount() 查出来的总数
 *      boolean moveToNext()
 *      Xxx getXxx(columnIndex)
 *      int getColumnIndex(columnName)
 * */


/**
 * 数据库操作的帮助类
 * */
public class MySQliteOpenHelper extends SQLiteOpenHelper {

    public Boolean isSQliteDemo = false;

    public static String sqDBName = "tikeyc";

    public static String sqDBName_Demo = "tikeyc_Demo";



    /**
     * name : tableName
     * */
    public MySQliteOpenHelper(Context context, String name, int version,boolean isSQliteDemo) {
        super(context, name, null, version);
        this.isSQliteDemo = isSQliteDemo;
    }

    /**
     * 什么时候回创建数据库文件？
     *  数据库文件不存在；连接数据库时
     *
     * 当数据库文件.db创建时调用（1次）
     * 在此方法中 建表；插入一些初始化数据
     * */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.e("TAG","public void onCreate(SQLiteDatabase sqLiteDatabase)");
        //建表
        String sql;
        if (isSQliteDemo){//默认 false

            sql = "create table " + sqDBName_Demo + " (_id integer primary key autoincrement, number varchar, name varchar)";
            sqLiteDatabase.execSQL(sql);

        }else {

            sql = "create table " + sqDBName + " (_id integer primary key autoincrement, key1 varchar, key2 double, key3 date)";
            sqLiteDatabase.execSQL(sql);

            /**插入一些初始化数据，也可以不插入*/
            sqLiteDatabase.execSQL("insert into " + sqDBName + " (key1,key2,key3) values ('value_1','value_2','value_3')");
            sqLiteDatabase.execSQL("insert into " + sqDBName + " (key1,key2,key3) values ('value__1','value__2','value__3')");

        }

    }

    /**
     * 当传入的版本号大于数据库版本号时调用
     * i : oldVersion
     * i1 : newVersion
     * */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        Log.e("TAG","public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1)");
    }
}
