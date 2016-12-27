package com.tikeyc.a11contentresolverstudy;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }


    /**
     * 获取联系人信息
     */
    public void getContactList() {
        //
        ContentResolver contentResolver = getContentResolver();

        String[] projection = {ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER};

        Cursor cursor = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,projection,null,null,null);

        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            Log.e("TAG",name + number);
        }
        cursor.close();
    }


    public void onClickButtonAction(View view) {
        //
        ContentResolver contentResolver = getContentResolver();


        switch (view.getId()) {
            case R.id.main_insert_button:{
                //
                Uri uri = Uri.parse("content://com.tikeyc.contentproviderstudy.personprovider/person/");
                ContentValues contentValues = new ContentValues();
                contentValues.put("name","tikeyc_ios");
                contentResolver.insert(uri,contentValues);
            }
            break;
            case R.id.main_update_button:{
                //
                Uri uri = Uri.parse("content://com.tikeyc.contentproviderstudy.personprovider/person/2");
                ContentValues contentValues = new ContentValues();
                contentValues.put("name","tikeyc_ios_java");
                contentResolver.update(uri,contentValues,null,null);
            }
            break;
            case R.id.main_delete_button:{
                //
                Uri uri = Uri.parse("content://com.tikeyc.contentproviderstudy.personprovider/person/2");

                contentResolver.delete(uri,null,null);
            }
            break;
            case R.id.main_query_button:{
                //
                Uri uri = Uri.parse("content://com.tikeyc.contentproviderstudy.personprovider/person");

                Cursor cursor = contentResolver.query(uri,null,null,null,null);

                while (cursor.moveToNext()) {
                    int id = cursor.getInt(0);
                    String name  = cursor.getString(cursor.getColumnIndex("name"));
                    Log.e("TAG",id + name);
                }
                cursor.close();

            }
            break;
            default:
                break;
        }
    }
}
