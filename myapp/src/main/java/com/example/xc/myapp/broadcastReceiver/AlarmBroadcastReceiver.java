package com.example.xc.myapp.broadcastReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.xc.myapp.service.BaseServise;

/**
 * Author:  @just_for_happy 开心就好
 * Date: 2016-02-26
 * Time: 10:34
 * FIXME          闹钟启动的广播
 */
public class AlarmBroadcastReceiver  extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("TAG","~~~~~~~~~~~~~~~~~~~~~~~~~闹钟广播启动了呀~~~~~~~~~~~~~~~~~~~~~~~~~");
        Intent intent1=new Intent(context, BaseServise.class);
        context.startService(intent1);


    }


}
