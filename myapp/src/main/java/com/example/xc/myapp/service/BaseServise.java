package com.example.xc.myapp.service;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.RemoteViews;

import com.example.xc.myapp.R;
import com.example.xc.myapp.bean.DayActivityBean;
import com.example.xc.myapp.dao.PublicFunction;
import com.litesuits.orm.LiteOrm;

/**
 * Author:  @just_for_happy 开心就好
 * Date: 2016-02-24
 * Time: 15:52
 * FIXME
 */
public class BaseServise extends Service {

    private final String DataBaseName = "Mydb.db";



    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }



    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("TAG", "----------------oncreate-------------------");

        createForegroundService();
    }

    @Override
    public void onStart(Intent intent, int startId) {
        Log.i("TAG", "----------------onStart-------------------");
        getLocation();
    }

    /***
     * 获取到 地址
     */
    private void getLocation() {
        PublicFunction.getLocation(this, new PublicFunction.DoThing() {
            @Override
            public void doThing(String location, String latitude, String longitude, String date) {
                //保存数据到 数据库
                LiteOrm liteOrm = LiteOrm.newSingleInstance(BaseServise.this, getString(R.string.datebase_name));
                DayActivityBean dayActivityBean = new DayActivityBean(location, latitude, longitude, date);
                //保存手机运行的程序
//                dayActivityBean.setUsingApp(PublicFunction.getUsingApp(BaseServise.this));
                liteOrm.save(dayActivityBean);
                liteOrm.close();
            }
        });
    }

    /***
     * 创建一个前台服务
     */
    private void createForegroundService() {

        Notification notification = new Notification();
        RemoteViews contentview = new RemoteViews(getPackageName(), R.layout.my_service);
        notification.contentView = contentview;
        Intent btn1 = new Intent(PublicFunction.BTN_1_NOTIFY_BROADCAST);
        Intent btn2 = new Intent(PublicFunction.BTN_2_NOTIFY_BROADCAST);
        Intent btn3 = new Intent(PublicFunction.BTN_3_NOTIFY_BROADCAST);
        PendingIntent pBtn1 = PendingIntent.getBroadcast(this, 0, btn1, 0);
        PendingIntent pBtn2 = PendingIntent.getBroadcast(this, 0, btn2, 0);
        PendingIntent pBtn3 = PendingIntent.getBroadcast(this, 0, btn3, 0);
        contentview.setOnClickPendingIntent(R.id.notify_btn_1, pBtn1);
        contentview.setOnClickPendingIntent(R.id.notify_btn_2, pBtn2);
        contentview.setOnClickPendingIntent(R.id.notify_btn_3, pBtn3);
        notification.icon=R.mipmap.ic_launcher;
        startForeground(1, notification);

    }


}
