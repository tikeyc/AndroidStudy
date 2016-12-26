package com.tikeyc.androidstudy.ListAndGrid;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;

import com.tikeyc.androidstudy.R;

import java.util.ArrayList;
import java.util.List;

public class GridViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view);

        ArrayList<ShopInfoModel> shopInfoModels = new ArrayList<ShopInfoModel>();
        //获取手机安装的APP信息
        shopInfoModels = getAllAppInfoModels();

        GridView gridView = (GridView) findViewById(R.id.gridView);
        gridView.setNumColumns(3);

        GridViewCustomAdapter adapter = new GridViewCustomAdapter(this,shopInfoModels);

        gridView.setAdapter(adapter);
    }




    ////////////获取手机安装的APP
    protected ArrayList<ShopInfoModel> getAllAppInfoModels(){

        ArrayList<ShopInfoModel> shopInfoModels = new ArrayList<ShopInfoModel>();
        //得到应用的packageManager
        PackageManager packageManager = getPackageManager();
        //创建一个主界面的intent
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        //得到包含应用信息的列表
        List<ResolveInfo> resolveInfos = packageManager.queryIntentActivities(intent,0);
        //遍历
        for (ResolveInfo resolveInfo : resolveInfos){
            //得到包名
            String packageName = resolveInfo.activityInfo.packageName;
            //
            Drawable icon = resolveInfo.loadIcon(packageManager);
            //
            String appName = resolveInfo.loadLabel(packageManager).toString();
            //
            ShopInfoModel shopInfoModel = new ShopInfoModel();
            shopInfoModel.setIconDraw(icon);
            shopInfoModel.setName(appName);
            shopInfoModels.add(shopInfoModel);
        }
        return shopInfoModels;
    }
}
