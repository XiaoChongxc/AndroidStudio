package com.example.xc.myapp.activity;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.xc.myapp.R;
import com.example.xc.myapp.dao.PublicFunction;
import com.example.xc.myapp.service.BaseServise;

import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends Activity {

    @Bind(R.id.btn_start)
    Button btnStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //注册一个闹钟   定时唤醒cpu  并执行 定位操作
        startAlarm();

    }

    /***
     * 开始监听服务
     */
    @OnClick(R.id.btn_start)
    void startService(){
        Intent  intent=new Intent(this, BaseServise.class);
        startService(intent);

//        Notification notification = new Notification();
//        RemoteViews contentview = new RemoteViews(getPackageName(), R.layout.my_service);
//        notification.contentView = contentview;
//        Intent btn1 = new Intent(PublicFunction.BTN_1_NOTIFY_BROADCAST);
//        Intent btn2 = new Intent(PublicFunction.BTN_2_NOTIFY_BROADCAST);
//        Intent btn3 = new Intent(PublicFunction.BTN_3_NOTIFY_BROADCAST);
//        PendingIntent pBtn1 = PendingIntent.getBroadcast(this, 0, btn1, 0);
//        PendingIntent pBtn2 = PendingIntent.getBroadcast(this, 0, btn2, 0);
//        PendingIntent pBtn3 = PendingIntent.getBroadcast(this, 0, btn3, 0);
//        contentview.setOnClickPendingIntent(R.id.notify_btn_1, pBtn1);
//        contentview.setOnClickPendingIntent(R.id.notify_btn_2, pBtn2);
//        contentview.setOnClickPendingIntent(R.id.notify_btn_3, pBtn3);
//        notification.icon=R.mipmap.ic_launcher;
//        NotificationManager nm=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
//        nm.notify(1,notification);

    }
    /***
     * 注册闹钟
     */
    private void startAlarm(){
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);


//        alarmManager.setRepeating(int type,long startTime,long intervalTime,PendingIntent pi);
//        在规定的时间精确的执行闹钟，比set方法设置的精度更高
//        setExact(int type, long triggerAtMillis, PendingIntent operation)：
//        AlarmManager.ELAPSED_REALTIME:
//        闹钟在手机睡眠状态下不可用，该状态下闹钟使用相对时间（相对于系统启动开始），状态值为3;
//        AlarmManager.ELAPSED_REALTIME_WAKEUP
//        闹钟在睡眠状态下会唤醒系统并执行提示功能，该状态下闹钟也使用相对时间，状态值为2；
//        AlarmManager.RTC
//        闹钟在睡眠状态下不可用，该状态下闹钟使用绝对时间，即当前系统时间，状态值为1；
//        AlarmManager.RTC_WAKEUP
//        表示闹钟在睡眠状态下会唤醒系统并执行提示功能，该状态下闹钟使用绝对时间，状态值为0;
//        AlarmManager.POWER_OFF_WAKEUP
//        表示闹钟在手机关机状态下也能正常进行提示功能，所以是5个状态中用的最多的状态之一，该状态下闹钟也是用绝对时间，状态值为4；不过本状态好像受SDK版本影响，某些版本并不支持；

        Intent intent=new Intent(PublicFunction.ALARM_BROADCAST);
        PendingIntent pi=PendingIntent.getBroadcast(this,0,intent,0);

        alarmManager.setRepeating(  AlarmManager.RTC_WAKEUP, new Date().getTime(), 1*1000*60, pi);


    }


}
