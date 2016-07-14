package baizhuan.hangzhou.com.android5study.util;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * User:    Xiaoc
 * 项目名:  WZB3.0
 * Package: baizhuan.hangzhou.com.wzb30.util
 * Date:    2016-06-23
 * Time:    15:25
 * 类描述： app 常量  常用 工具类
 */
public class Const {


    /**
     * 获取到  app版本名字
     */
    public static String getVersionName(Context ctx) {
        // 获取packagemanager的实例
        PackageManager packageManager = ctx.getPackageManager();
        // getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packInfo = null;
        try {
            packInfo = packageManager.getPackageInfo(ctx.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        String version = packInfo.versionName;
        return version;
    }

    /**
     * 获取到  app版本号
     *
     * @param ctx
     * @return
     */
    public static int getVersionCode(Context ctx) {
        // 获取packagemanager的实例
        PackageManager packageManager = ctx.getPackageManager();
        // getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packInfo = null;
        try {
            packInfo = packageManager.getPackageInfo(ctx.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        int version = packInfo.versionCode;
        return version;
    }

    /***
     * 打电话
     *
     * @param tel
     * @param ctx
     */
    public static void call(String tel, Context ctx) {
        //传入服务， parse（）解析号码
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + tel));
        //通知activtity处理传入的call服务
        ctx.startActivity(intent);
    }

    /***
     * 到拨打电话 页面
     *
     * @param tel
     * @param ctx
     */
    public static void toCall(String tel, Context ctx) {
        //传入服务， parse（）解析号码
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + tel));
        //通知activtity处理传入的call服务
        ctx.startActivity(intent);
    }


    /**
     * ----------------------------------------------工具类---------------------------------------
     **/
    /**
     * String  转 int
     *
     * @param str
     * @return
     */
    public static int string2int(String str) {
        int i = 0;
        try {
            i = Integer.parseInt(str);
        } catch (Exception e) {
            return 0;
        }
        return i;
    }

    /***
     * String 转 double
     *
     * @param str
     * @return
     */
    public static double string2double(String str) {
        double i = 0;
        try {
            i = Double.parseDouble(str);
        } catch (Exception e) {
            return 0;
        }
        return i;
    }

    public static String string2(double d) {
        DecimalFormat df = new DecimalFormat("######0.00");
        return df.format(d);
    }


    /***
     * 字符串转  int 数组
     *
     * @return
     */
    public static int[] string2intArray(String str) {
        String[] sa = str.split(",");
        int[] emplv = new int[sa.length];
        for (int i = 0; i < emplv.length; i++) {
            emplv[i] = Integer.parseInt(sa[i]);
        }
        return emplv;
    }


    /***
     * 时间格式化
     *
     * @param d
     * @param format
     * @return
     */
    public static String getDateFormat(Date d, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(d);
    }


    /***
     * 获取到 一个 用于测试的 list
     *
     * @return
     */
    public static List getList() {
        List list = new ArrayList();
        for (int i = 0; i < 10; i++) {
            list.add("***" + i + "***");
        }
        return list;
    }

    public static List getList2() {
        List list = new ArrayList();
        for (int i = 0; i < 3; i++) {
            list.add("*child**" + i + "***");
        }
        return list;
    }

}
