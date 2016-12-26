package com.tikeyc.datastudy;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.tikeyc.datastudy.SQlite.MySQliteOpenHelper;
import com.tikeyc.datastudy.SQliteDemo.SQliteDemoActivity;


/**
 * 创建表
 * create table tableName(
 *     _id integer primary key autoincrement,
 *     key1 varchar,
 *     key2 double,
 *     key3 date
 * )
 *
 * 插入：
 * insert into tableName (key1,key2,key3) values ('value1','value2','value3')
 * 删除：
 * delete from tableName where _id = 1
 * 更新：
 * update tableName set key1=value where _id = 1
 * 查找：
 * selected * form tableName
 * selected * form tableName where _id = 1
 * */

/**
 *
 * */

public class SQliteActivity extends AppCompatActivity {


    private double money;

    private boolean isUpdateDB = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);

    }


    /////////////

    public void onClickButton(View view){

        switch (view.getId()){
            case R.id.sqlite_button1:{//创建库

                MySQliteOpenHelper mySQliteOpenHelper = new MySQliteOpenHelper(this,MySQliteOpenHelper.sqDBName,1,false);
                //获取连接
                SQLiteDatabase sqLiteDatabase = mySQliteOpenHelper.getReadableDatabase();

                Toast.makeText(this,"DB创建成功",Toast.LENGTH_SHORT).show();
            }
            break;
            case R.id.sqlite_button2:{//更新库

                MySQliteOpenHelper mySQliteOpenHelper = new MySQliteOpenHelper(this,MySQliteOpenHelper.sqDBName,2,false);
                //获取连接
                SQLiteDatabase sqLiteDatabase = mySQliteOpenHelper.getReadableDatabase();

                isUpdateDB = true;
                Toast.makeText(this,"DB更新成功",Toast.LENGTH_SHORT).show();

            }
            break;
            case R.id.sqlite_button3:{//插入

                MySQliteOpenHelper mySQliteOpenHelper = new MySQliteOpenHelper(this,MySQliteOpenHelper.sqDBName,isUpdateDB ? 2 : 1,false);
                //获取连接
                SQLiteDatabase sqLiteDatabase = mySQliteOpenHelper.getReadableDatabase();
                //执行insert
                ContentValues contentValues = new ContentValues();
                contentValues.put("key1","iOS");
                money += 1000.0;
                contentValues.put("key2",money);
                contentValues.put("key3","2016-12-14");
                long id = sqLiteDatabase.insert(MySQliteOpenHelper.sqDBName,null,contentValues);
                //关闭
                sqLiteDatabase.close();
                //
                Toast.makeText(this,"插入成功 id:" + id,Toast.LENGTH_SHORT).show();

            }
            break;
            case R.id.sqlite_button4:{//更新
                MySQliteOpenHelper mySQliteOpenHelper = new MySQliteOpenHelper(this,MySQliteOpenHelper.sqDBName,isUpdateDB ? 2 : 1,false);
                //获取连接
                SQLiteDatabase sqLiteDatabase = mySQliteOpenHelper.getReadableDatabase();
                //执行update : update tableName set key1=value where _id = 1
                ContentValues contentValues = new ContentValues();
                contentValues.put("key1","iOS");
                money += 1000.0;
                contentValues.put("key2",money);
                contentValues.put("key3","2016-12-14");
                String whereClause = "_id=?";
                String[] whereArgs = new String[]{"3"};
                int updateCount = sqLiteDatabase.update(MySQliteOpenHelper.sqDBName,contentValues,whereClause,whereArgs);
                //关闭
                sqLiteDatabase.close();

                //
                Toast.makeText(this,"更新成功 updateCount:" + updateCount,Toast.LENGTH_SHORT).show();

            }
            break;
            case R.id.sqlite_button5:{//删除
                MySQliteOpenHelper mySQliteOpenHelper = new MySQliteOpenHelper(this,MySQliteOpenHelper.sqDBName,isUpdateDB ? 2 : 1,false);
                //获取连接
                SQLiteDatabase sqLiteDatabase = mySQliteOpenHelper.getReadableDatabase();
                //执行 delete from tableName where _id = 4
                int deleteCount = sqLiteDatabase.delete(MySQliteOpenHelper.sqDBName,"_id=?",null);
                //关闭
                sqLiteDatabase.close();
                //
                Toast.makeText(this,"删除成功 deleteCount:" + deleteCount,Toast.LENGTH_SHORT).show();
            }
            break;
            case R.id.sqlite_button6:{//查询
                MySQliteOpenHelper mySQliteOpenHelper = new MySQliteOpenHelper(this,MySQliteOpenHelper.sqDBName,isUpdateDB ? 2 : 1,false);
                //获取连接
                SQLiteDatabase sqLiteDatabase = mySQliteOpenHelper.getReadableDatabase();
                //执行query: selected * form tableName 或  selected * form tableName where _id = 1
                Cursor cursor = sqLiteDatabase.query(MySQliteOpenHelper.sqDBName, null, null, null, null, null, null);//orderBy "_id desc" 根据_id倒序查询
                //取出cursor中所有的数据
                while (cursor.moveToNext()) {
                    int id = cursor.getInt(0);//因为创建表的时候 id排在第0个
                    String key1 = cursor.getString(1);//因为创建表的时候 key1排在第1个
                    double key2 = cursor.getDouble(cursor.getColumnIndex("key2"));
                    String key3 = cursor.getString(cursor.getColumnIndex("key3"));
                    Toast.makeText(this,key1 + "---" + key2 + "---" + key3,Toast.LENGTH_LONG).show();
                }
                //关闭
                cursor.close();
                sqLiteDatabase.close();
                //
                Toast.makeText(this,"查询结束 queryCount:" + cursor.getCount(),Toast.LENGTH_SHORT).show();

            }
            break;


            /**
             * update tableName set key2=12000 where _id = 0
             * update tableName set key2=15000 where _id = 1
             * 下方此操作 中 要么2条更新都成功，要么都失败的处理方式
             * */
            case R.id.sqlite_button7:{//事务处理

                SQLiteDatabase sqLiteDatabase = null;

                try {
                    MySQliteOpenHelper mySQliteOpenHelper = new MySQliteOpenHelper(this,MySQliteOpenHelper.sqDBName,isUpdateDB ? 2 : 1,false);
                    //获取连接
                    sqLiteDatabase = mySQliteOpenHelper.getReadableDatabase();
                    //开启事务(获取连接后)
                    sqLiteDatabase.beginTransaction();

                    //更新1
                    ContentValues contentValues1 = new ContentValues();
                    contentValues1.put("key1","Android");
                    money += 5000.0;
                    contentValues1.put("key2",money);
                    contentValues1.put("key3","2016-12-14");
                    String whereClause = "_id=?";
                    String[] whereArgs = new String[]{"0"};
                    int updateCount1 = sqLiteDatabase.update(MySQliteOpenHelper.sqDBName,contentValues1,whereClause,whereArgs);

                    /////////如果不开启事务 更新1将成功更新数据库数据
                    /*设置异常（设置断言，相当于iOS中的NSAssert）* */
                    boolean flag = true;
                    if (flag) {
                        throw new RuntimeException("出异常了！");
                    }
                    /////////更新1执行了 但因为开启了事务，而且上面面抛出了异常所以和更新2一样都不成功

                    //更新2
                    ContentValues contentValues2 = new ContentValues();
                    contentValues2.put("key1","Swift");
                    money += 15000.0;
                    contentValues2.put("key2",money);
                    contentValues2.put("key3","2016-12-14");
                    int updateCount2 = sqLiteDatabase.update(MySQliteOpenHelper.sqDBName,contentValues2,"_id=?",new String[]{"1"});

                    //设置事务成功(在全部SQ执行完后)
                    sqLiteDatabase.setTransactionSuccessful();

                } catch (Exception e){


                } finally {
                    //结束事务（在finally中）
                    if (sqLiteDatabase != null){
                        sqLiteDatabase.endTransaction();
                        sqLiteDatabase.close();
                    }

                }

            }
            break;
            default:
                break;
        }
    }




    //Demo演示
    public void onClickSQliteDemo(View view) {
        Intent intent = new Intent(this,SQliteDemoActivity.class);
        startActivity(intent);

    }
}
