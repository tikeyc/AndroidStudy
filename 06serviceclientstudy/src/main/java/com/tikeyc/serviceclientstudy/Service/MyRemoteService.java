package com.tikeyc.serviceclientstudy.Service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

/**
 * Created by public1 on 2016/12/23.
 */

public class MyRemoteService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new StudentService();
    }

    /**
     * 处理Student相关的业务逻辑类
     */
    public class StudentService extends IStudentService.Stub {

        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }

        @Override
        public Student getStudentById(int id) throws RemoteException {
            Student student = new Student();
            student.setId(100);
            student.setName("tikeyc");

            return student;
        }
    }
}
