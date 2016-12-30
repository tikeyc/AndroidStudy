package com.tikeyc.a13graphicsstudy.Demo1.ListAndGrid;

import android.graphics.drawable.Drawable;


/**
 * Created by public1 on 2016/12/9.
 */

public class ShopInfoModel {


    private int icon;
    private String name;
    private String content;

    private Drawable iconDraw;

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Drawable getIconDraw() {
        return iconDraw;
    }

    public void setIconDraw(Drawable iconDraw) {
        this.iconDraw = iconDraw;
    }

    @Override
    public String toString() {
        return "ShopInfoModel{" +
                "icon=" + icon +
                ", name='" + name + '\'' +
                ", content='" + content + '\'' +
                ", iconDraw=" + iconDraw +
                '}';
    }
}
