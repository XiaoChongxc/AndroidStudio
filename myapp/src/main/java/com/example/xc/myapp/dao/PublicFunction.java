package com.example.xc.myapp.dao;

import android.app.ActivityManager;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.location.LocationManagerProxy;
import com.amap.api.location.LocationProviderProxy;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Author:  @just_for_happy 开心就好
 * Date: 2016-02-25
 * Time: 11:08
 * FIXME   一些公共的方法
 */

public class PublicFunction {

    public static final  String ALARM_BROADCAST="COM.XC.BROADCAST.ALARM";

    public static final  String BTN_1_NOTIFY_BROADCAST="COM.XC.BROADCAST.NOTIFY_BTN_1";

    public static final  String BTN_2_NOTIFY_BROADCAST="COM.XC.BROADCAST.NOTIFY_BTN_2";

    public static final  String BTN_3_NOTIFY_BROADCAST="COM.XC.BROADCAST.NOTIFY_BTN_3";

    public static final  String  SendMessage="getLocation";

    public  static final String MY_CUSTOM_BROADCAST="com.xc.broadcast.custom";




    private static SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    /***
     * 获取到 地址
     */
    public static void getLocation(final Context ctx, final DoThing callBack) {

        LocationManagerProxy mLocationManagerProxy = LocationManagerProxy
                .getInstance(ctx);
        // 混合定位
        mLocationManagerProxy.setGpsEnable(false);      //不使用gps定位
        mLocationManagerProxy.requestLocationData(
                LocationProviderProxy.AMapNetwork, -1, 15,
                new AMapLocationListener() {
                    @Override
                    public void onStatusChanged(String provider, int status,
                                                Bundle extras) {
                    }

                    @Override
                    public void onProviderEnabled(String provider) {
                    }

                    @Override
                    public void onProviderDisabled(String provider) {
                    }

                    @Override
                    public void onLocationChanged(Location location) {
                    }

                    @Override
                    public void onLocationChanged(AMapLocation amapLocation) {
                        if (amapLocation != null && amapLocation.getAMapException().getErrorCode() == 0) {
                            callBack.doThing(amapLocation.getExtras().getString("desc"),amapLocation.getLatitude()+"",amapLocation.getLongitude()+"",sdf.format(new Date()));
                        }
                    }
                });
    }
    /**得到手机通讯录联系人信息**/
//    private void getPhoneContacts() {
//        ContentResolver resolver = mContext.getContentResolver();
//
//        // 获取手机联系人
//        Cursor phoneCursor = resolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,PHONES_PROJECTION, null, null, null);
//
//
//        if (phoneCursor != null) {
//            while (phoneCursor.moveToNext()) {
//
//                //得到手机号码
//                String phoneNumber = phoneCursor.getString(PHONES_NUMBER_INDEX);
//                //当手机号码为空的或者为空字段 跳过当前循环
//                if (TextUtils.isEmpty(phoneNumber))
//                    continue;
//
//                //得到联系人名称
//                String contactName = phoneCursor.getString(PHONES_DISPLAY_NAME_INDEX);
//
//                //得到联系人ID
//                Long contactid = phoneCursor.getLong(PHONES_CONTACT_ID_INDEX);
//
//                //得到联系人头像ID
//                Long photoid = phoneCursor.getLong(PHONES_PHOTO_ID_INDEX);
//
//                //得到联系人头像Bitamp
//                Bitmap contactPhoto = null;
//
//                //photoid 大于0 表示联系人有头像 如果没有给此人设置头像则给他一个默认的
//                if(photoid > 0 ) {
//                    Uri uri = ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, contactid);
//                    InputStream input = ContactsContract.Contacts.openContactPhotoInputStream(resolver, uri);
//                    contactPhoto = BitmapFactory.decodeStream(input);
//                }else {
//                    contactPhoto = BitmapFactory.decodeResource(getResources(), R.drawable.contact_photo);
//                }
//
//                mContactsName.add(contactName);
//                mContactsNumber.add(phoneNumber);
//                mContactsPhonto.add(contactPhoto);
//            }
//
//            phoneCursor.close();
//        }
//    }


    /**得到手机SIM卡联系人人信息**/
    public static String  getSIMContactsByPhone(Context mContext,String phone) {
        StringBuffer sb=new StringBuffer();
        ContentResolver resolver = mContext.getContentResolver();
        // 获取Sims卡联系人
        Uri uri = Uri.parse("content://icc/adn");
        Cursor phoneCursor = resolver.query(uri,new String[]{ ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME , ContactsContract.CommonDataKinds.Phone.NUMBER } ,
                ContactsContract.CommonDataKinds.Phone.NUMBER+"='"+phone+"'"
                ,null,  null);

        if (phoneCursor != null) {
            while (phoneCursor.moveToNext()) {
                // 得到手机号码
                String phoneNumber = phoneCursor.getString(1);
                // 当手机号码为空的或者为空字段 跳过当前循环
                if (TextUtils.isEmpty(phoneNumber))
                    continue;
                // 得到联系人名称
                String contactName = phoneCursor.getString(0);
                if(contactName==null ||contactName.equals("")){
                    return null;
                }
                sb.append(phoneNumber+":"+contactName+"   ");
            }
            phoneCursor.close();
        }
        return sb.toString();
    }
    public static String getUsingApp(Context  ctx){
        StringBuffer sb=new StringBuffer();
        ActivityManager manager = (ActivityManager) ctx.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> list =manager.getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo runInf : list){
            sb.append("******"+runInf.processName+"*******");
        }
        return sb.toString();
    }


    public  interface DoThing{
        void doThing(String location,String latitude,String longitude,String date);

    }
    //获取手机信息
    public static String getUid(Context ctx){
        TelephonyManager TelephonyMgr = (TelephonyManager)ctx.getSystemService(ctx.TELEPHONY_SERVICE);
        String szImei = TelephonyMgr.getDeviceId();
        return szImei;
    }


}
