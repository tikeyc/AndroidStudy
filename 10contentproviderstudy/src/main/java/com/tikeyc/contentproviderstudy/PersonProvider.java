package com.tikeyc.contentproviderstudy;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;

/**
 * Created by public1 on 2016/12/27.
 */

public class PersonProvider extends ContentProvider {

    //存放合法Uri容器
    private static UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    //保存一些合法的Uri
    /**
     * content://com.tikeyc.contentproviderstudy.personprovider/person    不根据id操作
     * content://com.tikeyc.contentproviderstudy.personprovider/person/1  根据id操作
     * */
    static {
        uriMatcher.addURI("com.tikeyc.contentproviderstudy.personprovider","/person",1);
        uriMatcher.addURI("com.tikeyc.contentproviderstudy.personprovider","/person/#",1);//#匹配任意数字
    }

    private DBHelper dbHelper;

    public PersonProvider() {
    }

    @Override
    public boolean onCreate() {

        dbHelper = new DBHelper(getContext(),DBHelper.sqDBName,null,1);

        return false;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] strings, String s, String[] strings1, String s1) {

        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();

        int code = uriMatcher.match(uri);
        if (code == 1) {//不根据id查询
            Cursor cursor = sqLiteDatabase.query("person",strings,s,strings1,null,null,null);
            return cursor;

        } else if (code == 2) {
            long id = ContentUris.parseId(uri);
            Cursor cursor = sqLiteDatabase.query("person",strings,"_id=?",new String[]{id+""},null,null,null);
            sqLiteDatabase.close();
            return cursor;

        } else {
            sqLiteDatabase.close();
            throw new RuntimeException("查询的uri不合法");
        }

//        return null;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        //
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        //
        int code = uriMatcher.match(uri);
        if (code == 1) {//
            long id = sqLiteDatabase.insert(DBHelper.sqDBName,null,contentValues);
            //将id添加到uri中
            uri = ContentUris.withAppendedId(uri,id);
            sqLiteDatabase.close();
            return uri;

        }  else {
            sqLiteDatabase.close();
            throw new RuntimeException("插入的uri不合法");
        }

//        return null;
    }

    @Override
    public int delete(Uri uri, String s, String[] strings) {
        //
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        //
        int deleteCount = -1;
        int code = uriMatcher.match(uri);
        if (code == 1) {//
            deleteCount = sqLiteDatabase.delete(DBHelper.sqDBName,s,strings);


        } else if (code == 2) {
            long id = ContentUris.parseId(uri);
            deleteCount = sqLiteDatabase.delete(DBHelper.sqDBName,"_id="+id,null);
        } else {
            sqLiteDatabase.close();
            throw new RuntimeException("删除的uri不合法");
        }

        sqLiteDatabase.close();
        return deleteCount;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String s, String[] strings) {

        //
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        //
        int updateCount = -1;
        int code = uriMatcher.match(uri);
        if (code == 1) {//
            updateCount = sqLiteDatabase.update(DBHelper.sqDBName,contentValues,s,strings);


        } else if (code == 2) {
            long id = ContentUris.parseId(uri);
            updateCount = sqLiteDatabase.update(DBHelper.sqDBName,contentValues,"_id="+id,null);
        } else {
            sqLiteDatabase.close();
            throw new RuntimeException("更新的uri不合法");
        }

        sqLiteDatabase.close();
        return updateCount;
    }
}
