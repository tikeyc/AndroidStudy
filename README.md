# AndroidStudy
Android 开发学习

Android studio Mac环境搭建    
http://www.androiddevtools.cn/     
http://www.android-studio.org/

学习笔记 详细笔记见http://blog.csdn.net/u012847940/article/details/53286817#t7 
常见异常：        
    1.NullPointerException  空指针（iOS我常称为 野指针）   
    原因：调用对象的方法/属性 但对象为null    
    2.ClassCastException   
    原因：类型转换错误   
    3.ActivityNotFoundException   
    原因：没有注册Activity或注册不正确   
基本常见异常的一般分析步骤：   
    1.LogCate 最好先找到 Caused by是何种异常导致的    
    2.找到出异常的类及行号 点击进入对应代码    
    
    
       
   第一章：Android快速入门     
   第二章：四大应用组件之Activity     
   Activity 生命周期  与iOS中的viewController中的viewDidLoad,等类似        
 界面从死亡--》运行        
     创建对象--》onCreate()-->onStart()-->onResume()        
 界面从运行--》死亡      
     onPause()-->onStop()-->onDestory()       
 界面从停止--》运行       
     onRestart()-->onStart()-->onResume()       
 界面从运行--》暂停       
     onPause()        
 界面从暂停--》运行      
     onResume()   
   第三章：用户界面(上)     
   第四章：用户界面(下)    
   第五章：数据存储(上)    
   第六章：数据存储(中)    
   第七章：数据存储(下)    
   第八章：消息机制与异步任务    
   第九章：事件机制    
   第一十章：四大应用组件之Service    
   第一十一章：四大应用组件之BroadcastReceiver    
   第一十二章：四大应用组件之ContentProvider    
   第一十三章：动画    
   第一十四章：图像处理     
   第一十五章：碎片     
