package com.tikeyc.servicestudy.Service;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

/**
 * Created by public1 on 2016/12/23.
 */
public class Student implements Parcelable {

    private int id;
    private String name;

    protected Student(Parcel in) {
        id = in.readInt();
        name = in.readString();
    }

    public Student() {

    }

    //////////////////////////////////////

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }


    //////////////必须实现Parcelable接口

    /**
     *
     */
    public static final Creator<Student> CREATOR = new Creator<Student>() {
        @Override
        public Student createFromParcel(Parcel in) {
            //解包：读取保重的数据并封装成对象
            Log.e("TAG","createFromParcel（）");
            return new Student(in);
        }

        @Override
        public Student[] newArray(int size) {
            return new Student[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    /**将当前对象的属性数据打包，写到包对象中
     * @param parcel
     * @param i
     */
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        Log.e("TAG","打包writeToParcel（）");
        parcel.writeInt(id);
        parcel.writeString(name);
    }
}
