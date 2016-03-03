package com.example.xc.myapp.broadcastReceiver;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.example.xc.myapp.R;
import com.example.xc.myapp.bean.DayActivityBean;
import com.example.xc.myapp.dao.PublicFunction;
import com.litesuits.orm.LiteOrm;

/**
 * Author:  @just_for_happy 开心就好
 * Date: 2016-02-25
 * Time: 10:19
 * FIXME
 */

public class PhoneStatReceiver extends BroadcastReceiver {

    private static final String TAG = "PhoneStatReceiver";

//        private static MyPhoneStateListener phoneListener = new MyPhoneStateListener();

    private static boolean incomingFlag = false;

    private static String incoming_number = null;

    @Override
    public void onReceive(Context context, Intent intent) {
        //如果是拨打电话
        if(intent.getAction().equals(Intent.ACTION_NEW_OUTGOING_CALL)){
            incomingFlag = false;
            String phoneNumber = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER);
            Log.i(TAG, "call OUT:" + phoneNumber);
            String contactName=PublicFunction.getSIMContactsByPhone(context,phoneNumber);
            if(contactName ==null){
                saveInfo(context,phoneNumber+"---------------",1);
            }else{
                saveInfo(context,contactName+"---------------",1);
            }

        }else{
            //如果是来电
            TelephonyManager tm =
                    (TelephonyManager)context.getSystemService(Service.TELEPHONY_SERVICE);

            switch (tm.getCallState()) {
                case TelephonyManager.CALL_STATE_RINGING:
                    incomingFlag = true;//标识当前是来电
                    incoming_number = intent.getStringExtra("incoming_number");
                    Log.i(TAG, "RINGING :"+ incoming_number);
                    break;
                case TelephonyManager.CALL_STATE_OFFHOOK:
                    if(incomingFlag){
                        Log.i(TAG, "incoming ACCEPT :"+ incoming_number);
                    }
                    break;

                case TelephonyManager.CALL_STATE_IDLE:
                    if(incomingFlag){
                        Log.i(TAG, "incoming IDLE");
                    }
                    break;
            }
            String contactName=PublicFunction.getSIMContactsByPhone(context,incoming_number);
            if(contactName ==null){
                saveInfo(context,incoming_number+"---------------",0);
            }else{
                saveInfo(context,contactName+"---------------",0);
            }

        }



    }
    private  void saveInfo(final Context context,final String phone,final int type){

        PublicFunction.getLocation(context, new PublicFunction.DoThing() {
            @Override
            public void doThing(String location, String latitude, String longitude, String date) {
                LiteOrm liteOrm = LiteOrm.newSingleInstance(context, context.getString(R.string.datebase_name));
                DayActivityBean dayActivityBean = new DayActivityBean(location, latitude, longitude, date);
                if(type==0){
                    //来电
                    dayActivityBean.setCallPhone(phone);
                }else{
                    //去电
                    dayActivityBean.setToPhone(phone);
                }
                liteOrm.save(dayActivityBean);
            }
        });
    }


}
