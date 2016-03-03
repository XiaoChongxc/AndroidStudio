package com.example.xc.myapp.broadcastReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.example.xc.myapp.R;
import com.example.xc.myapp.bean.DayActivityBean;
import com.example.xc.myapp.dao.PublicFunction;
import com.litesuits.orm.LiteOrm;

import java.util.ArrayList;

/**
 * Author:  @just_for_happy 开心就好
 * Date: 2016-02-25
 * Time: 14:29
 * FIXME
 */
public class NotificationBroadcastReceiver  extends BroadcastReceiver  {

    @Override
    public void onReceive(Context context, Intent intent) {
        String action =intent.getAction();
        Log.i("TAG", "~~~~~~~~~~~~~~~~~~~~~~~~~"+action+"~~~~~~~~~~~~~~~~~~~~~~~~~");

        if(action.equals(PublicFunction.BTN_1_NOTIFY_BROADCAST)){

            LiteOrm liteOrm =LiteOrm.newSingleInstance(context,context.getString(R.string.datebase_name));
            ArrayList<DayActivityBean> list= liteOrm.query(DayActivityBean.class);
            liteOrm.close();
            StringBuffer sb=new StringBuffer();
            for (DayActivityBean info : list){
                sb.append(info.getDatetime()+"\n我在"+info.getLocation()+"纬度"+info.getLatitude()+"经度"+info.getLongitude()+
                        "手机正在运行的程序有：\n"+info.getUsingApp()+"\n");
                if(! "".equals(info.getToPhone())  && info.getToPhone()!=null){
                    //当时正在打电话
                    sb.append("我当时正在给"+info.getToPhone()+"打电话");
                }
                if(!"".equals(info.getFromMessage())){
                    sb.append("我收到了一条信息"+info.getFromMessage());
                }
                Toast.makeText(context,sb.toString(),Toast.LENGTH_SHORT).show();

            }

        }else if(action.equals(PublicFunction.BTN_2_NOTIFY_BROADCAST)){

        }else if(action.equals(PublicFunction.BTN_3_NOTIFY_BROADCAST)){

        }

    }
}
