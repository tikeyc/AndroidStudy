package com.tikeyc.messageandthreadstudy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Map;

public class TestJsonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_json);



//        testJsonToObject2();

        testJsonToList2();

    }


    /*将Json格式的字符串{}转换成Java对象，使用原生API
    {
        "id": 2527484,
            "level": 62,
            "gender": 0,
            "nick": "🐯。陆小曼",
            "portrait": "http://img2.inke.cn/MTQ4MjA2MDUyNzQ0MSMxODgjanBn.jpg"
    }*/
    public void testJsonToObject1() throws JSONException {
        String jsonString = "{\"id\": 2527484,\"level\": 62,\"gender\": 0,\"nick\": \"\uD83D\uDC2F。陆小曼\",\"portrait\": \"http://img2.inke.cn/MTQ4MjA2MDUyNzQ0MSMxODgjanBn.jpg\"}";

        JSONObject jsonObject = new JSONObject(jsonString);

        String nick = jsonObject.getString("nick");
        String portrait = jsonObject.getString("portrait");

        //
        ShopInfoModel shopInfoModel = new ShopInfoModel();
        shopInfoModel.nick = nick;
        shopInfoModel.portrait = portrait;

    }


    /*将Json格式的字符串{}转换成Java对象，使用Gson
    * */
    public void testJsonToObject2() {
        String jsonString = "{\"id\": 2527484,\"level\": 62,\"gender\": 0,\"nick\": \"\uD83D\uDC2F。陆小曼\",\"portrait\": \"http://img2.inke.cn/MTQ4MjA2MDUyNzQ0MSMxODgjanBn.jpg\"}";

        Gson gson = new Gson();
        ShopInfoModel shopInfoModel = gson.fromJson(jsonString,ShopInfoModel.class);


        Toast.makeText(this,shopInfoModel.nick + shopInfoModel.portrait,Toast.LENGTH_LONG).show();

    }

    //////////////////////////////////////////////////

    /*将Json格式的字符串[]转换成Java对象的List，使用原生API
    * */

    public void testJsonToList1() throws JSONException {
        String jsonString = "[{\"id\": 2527484,\"level\": 62,\"gender\": 0,\"nick\": \"\uD83D\uDC2F。陆小曼\",\"portrait\": \"http://img2.inke.cn/MTQ4MjA2MDUyNzQ0MSMxODgjanBn.jpg\"}," +
                "{\"id\": 7726608,\"level\": 49,\"gender\": 0,\"nick\": \"念小兮baby\",\"portrait\": \"http://img2.inke.cn/MTQ4MTgxODEwNTk4OSM2MTAjanBn.jpg\"}]";

        ArrayList<ShopInfoModel> shopInfoModels = new ArrayList<ShopInfoModel>();

        JSONArray jsonArray = new JSONArray(jsonString);

        for (int i = 0 ;i < jsonArray.length();i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);

            //
            String nick = jsonObject.getString("nick");
            String portrait = jsonObject.getString("portrait");

            //
            ShopInfoModel shopInfoModel = new ShopInfoModel();
            shopInfoModel.nick = nick;
            shopInfoModel.portrait = portrait;
            //
            shopInfoModels.add(shopInfoModel);
        }

    }

    /*将Json格式的字符串[]转换成Java对象的List，使用Gson
    * */
    public void testJsonToList2() {
        String jsonString = "[{\"id\": 2527484,\"level\": 62,\"gender\": 0,\"nick\": \"\uD83D\uDC2F。陆小曼\",\"portrait\": \"http://img2.inke.cn/MTQ4MjA2MDUyNzQ0MSMxODgjanBn.jpg\"}," +
                "{\"id\": 7726608,\"level\": 49,\"gender\": 0,\"nick\": \"念小兮baby\",\"portrait\": \"http://img2.inke.cn/MTQ4MTgxODEwNTk4OSM2MTAjanBn.jpg\"}]";

        Gson gson = new Gson();
        Type typeOfT = new TypeToken<ArrayList<ShopInfoModel>>(){}.getType();
        ArrayList<ShopInfoModel> shopInfoModels = gson.fromJson(jsonString,typeOfT);

        ShopInfoModel shopInfoModel = shopInfoModels.get(0);
        Toast.makeText(this,shopInfoModel.nick + shopInfoModel.portrait,Toast.LENGTH_LONG).show();
    }


    //////////////////////////////////////////////////对象转Json
    /*将Java对象转换成Json格式的字符串，使用Gson
    * */
    public void testObjectToJson() {
        ShopInfoModel shopInfoModel = new ShopInfoModel();
        shopInfoModel.nick = "念小兮baby";
        shopInfoModel.portrait = "http://img2.inke.cn/MTQ4MTgxODEwNTk4OSM2MTAjanBn.jpg";

        String jsonString = new Gson().toJson(shopInfoModel);

        Toast.makeText(this,jsonString,Toast.LENGTH_LONG).show();
    }


    /*特别的 key为数字，key为my nick 时无法各类创建此种属性时
    {
            "id": 2527484,
            "2": 62,
            "gender": 0,
            "my nick": "🐯。陆小曼",
            "portrait": "http://img2.inke.cn/MTQ4MjA2MDUyNzQ0MSMxODgjanBn.jpg"
    }
    * */
    public void testJsonToMap() {
        String jsonString = "{\"id\": 2527484,\"level\": 62,\"gender\": 0,\"my nick\": \"\uD83D\uDC2F。陆小曼\",\"portrait\": \"http://img2.inke.cn/MTQ4MjA2MDUyNzQ0MSMxODgjanBn.jpg\"}";

        Gson gson = new Gson();
        Type typeOfT = new TypeToken<Map<String,Object>>(){}.getType();
        Map<String,Object> map = gson.fromJson(jsonString,typeOfT);

        Toast.makeText(this,map.toString(),Toast.LENGTH_LONG).show();

    }

}
