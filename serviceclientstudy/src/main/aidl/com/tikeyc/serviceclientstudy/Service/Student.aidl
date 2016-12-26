// Student.aidl
//第一类AIDL文件
//这个文件的作用是引入了一个序列化对象 Book 供其他的AIDL文件使用
//注意：Student.aidl与Student.java的包名应当是一样的
package com.tikeyc.serviceclientstudy.Service;

// Declare any non-default types here with import statements

parcelable Student;
