package com.example.xc.myapp.broadcastReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import com.example.xc.myapp.R;
import com.example.xc.myapp.activity.map.LocationActivity;
import com.example.xc.myapp.dao.PublicFunction;
import com.litesuits.orm.LiteOrm;

import java.lang.reflect.Method;

/**
 * Author:  @just_for_happy 开心就好
 * Date: 2016-02-25
 * Time: 14:29
 * FIXME
 */
public class NotificationBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        Log.i("TAG", "~~~~~~~~~~~~~~~~~~~~~~~~~" + action + "~~~~~~~~~~~~~~~~~~~~~~~~~");

        if (action.equals(PublicFunction.BTN_1_NOTIFY_BROADCAST)) {

            Intent intent1 = new Intent(context, LocationActivity.class);
            intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent1);
            collapseStatusBar(context);

        } else if (action.equals(PublicFunction.BTN_2_NOTIFY_BROADCAST)) {
            collapseStatusBar(context);

        } else if (action.equals(PublicFunction.BTN_3_NOTIFY_BROADCAST)) {
            //删除 数据库信息
            collapseStatusBar(context);
            LiteOrm liteOrm = LiteOrm.newSingleInstance(context, context.getString(R.string.datebase_name));
            if (liteOrm.deleteDatabase()) {
                Toast.makeText(context, "数据删除成功！", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "数据删除失败！", Toast.LENGTH_SHORT).show();
            }
        }

    }

    public static void collapseStatusBar(Context context) {
        try {
            Object statusBarManager = context.getSystemService("statusbar");
            Method collapse;

            if (Build.VERSION.SDK_INT <= 16) {
                collapse = statusBarManager.getClass().getMethod("collapse");
            } else {
                collapse = statusBarManager.getClass().getMethod("collapsePanels");
            }
            collapse.invoke(statusBarManager);
        } catch (Exception localException) {
            localException.printStackTrace();
        }
    }
}
