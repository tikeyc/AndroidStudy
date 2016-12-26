package com.tikeyc.datastudy.SQliteDemo;

/**
 * Created by public1 on 2016/12/14.
 */

public class BlackNumberModel {

    private String number;

    private String name;

    private String id;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {

        return id;
    }



    public BlackNumberModel(String number, String name) {
        this.number = number;
        this.name = name;
    }



    public String getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return "BlackNumberModel{" +
                "number='" + number + '\'' +
                ", name='" + name + '\'' +
                ", id='" + id + '\'' +
                '}';
    }

}
