package com.example.administrator.mytest.util;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.mytest.R;

import utils.DensityUtils;

/**
 * Author:  @just_for_happy 开心就好
 * Date: 2016-02-01
 * Time: 15:20
 * FIXME   自定义的测试类
 */
public class T {

    public static void showTop(Context ctx, String str){
        WindowManager.LayoutParams params = new WindowManager.LayoutParams();
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        Toast toast = new Toast(ctx);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP, 0, DensityUtils.dp2px(ctx, 58));
        View v= LayoutInflater.from(ctx).inflate(R.layout.custom_toast,null,false);
        TextView t= (TextView)v.findViewById(R.id.tv_toast);
        t.setText(str);
        toast.setView(v);
        toast.show();

    }


}
