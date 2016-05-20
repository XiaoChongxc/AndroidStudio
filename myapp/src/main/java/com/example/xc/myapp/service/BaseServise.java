package com.example.xc.myapp.service;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.RemoteViews;

import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.example.xc.myapp.MyApplication;
import com.example.xc.myapp.R;
import com.example.xc.myapp.dao.PublicFunction;

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
//                LiteOrm liteOrm = LiteOrm.newSingleInstance(BaseServise.this, getString(R.string.datebase_name));
//                DayActivityBean dayActivityBean = new DayActivityBean(location, latitude, longitude, date);
//                //保存手机运行的程序
////                dayActivityBean.setUsingApp(PublicFunction.getUsingApp(BaseServise.this));
//                liteOrm.save(dayActivityBean);
//                liteOrm.close();

                //获取到 位置信息， 然后 保存在 application里面
                MyApplication.getInstance().setMyLocation(new PoiItem(location, new LatLonPoint(string2double(latitude), string2double(longitude)), location, location));
            }
        });
    }

    private   double string2double(String str){
        double  b= 0;
        try{
            b=Double.parseDouble(str);
        }catch (Exception e){
            b=0;
        }
        return b;


    }

    /***
     * 创建一个前台服务
     */
    private void createForegroundService() {

        Notification notification = new Notification();

        RemoteViews contentview = new RemoteViews(getPackageName(), R.layout.my_service);
        notification.contentView = contentview;
        contentview.setTextViewText(R.id.tv_btn_1,"查看自己的位置");
        contentview.setTextViewText(R.id.tv_btn_2,"待定");
        contentview.setTextViewText(R.id.tv_btn_3,"删除数据库");
        contentview.setImageViewResource(R.id.img_notify_icon,R.mipmap.ic_launcher);

        Intent btn1 = new Intent(PublicFunction.BTN_1_NOTIFY_BROADCAST);
        Intent btn2 = new Intent(PublicFunction.BTN_2_NOTIFY_BROADCAST);
        Intent btn3 = new Intent(PublicFunction.BTN_3_NOTIFY_BROADCAST);

        PendingIntent pBtn1 = PendingIntent.getBroadcast(this, 0, btn1, 0);
        PendingIntent pBtn2 = PendingIntent.getBroadcast(this, 0, btn2, 0);
        PendingIntent pBtn3 = PendingIntent.getBroadcast(this, 0, btn3, 0);

        contentview.setOnClickPendingIntent(R.id.tv_btn_1, pBtn1);
        contentview.setOnClickPendingIntent(R.id.tv_btn_3, pBtn2);
        contentview.setOnClickPendingIntent(R.id.tv_btn_3, pBtn3);
        notification.icon = R.mipmap.ic_launcher;
        startForeground(1, notification);

    }


}
