package com.example.xc.myapp.broadcastReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;

import com.example.xc.myapp.R;
import com.example.xc.myapp.bean.DayActivityBean;
import com.example.xc.myapp.dao.PublicFunction;
import com.litesuits.orm.LiteOrm;

/**
 * Author:  @just_for_happy 开心就好
 * Date: 2016-02-25
 * Time: 10:50
 * FIXME  短信的广播
 */
public class MessageReceiver  extends BroadcastReceiver {

    @Override
    public void onReceive(final Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        if (null != bundle) {
            Object[] smsObj = (Object[]) bundle.get("pdus");
            for (Object object : smsObj) {
              final   SmsMessage    msg = SmsMessage.createFromPdu((byte[]) object);
//                System.out.println("number:" + msg.getOriginatingAddress()
//                        + "   body:" + msg.getDisplayMessageBody() + "  time:"
//                        + msg.getTimestampMillis());
                //保存接受短信记录
                PublicFunction.getLocation(context, new PublicFunction.DoThing() {
                    @Override
                    public void doThing(String location, String latitude, String longitude,String date) {
                        LiteOrm liteOrm =LiteOrm.newSingleInstance(context,context.getString(R.string.datebase_name));
                        DayActivityBean dayActivityBean=new DayActivityBean(location,latitude,longitude,date);
                        String contactName=PublicFunction.getSIMContactsByPhone(context,msg.getOriginatingAddress());
                        if(contactName==null){//获取不到联系人名字
                            dayActivityBean.setFromMessage(msg.getOriginatingAddress());
                        }else{
                            dayActivityBean.setFromMessage(contactName);
                        }
                        liteOrm.save(dayActivityBean);

                    }
                });



            }
        }
    }


}
