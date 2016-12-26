// IStudentService.aidl
package com.tikeyc.serviceclientstudy.Service;

//导入所需要使用的非默认支持数据类型的包
import com.tikeyc.serviceclientstudy.Service.Student;
// Declare any non-default types here with import statements

interface IStudentService {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);

    Student getStudentById(int id);
}
