package com.example.xc.myapp;

import android.app.Application;
import android.util.Log;

import com.amap.api.services.core.PoiItem;
import com.example.xc.myapp.util.DemoUtil;

import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;

import io.yunba.android.manager.YunBaManager;

/**
 * Author:  @just_for_happy 开心就好
 * Date: 2016-02-29
 * Time: 16:46
 * FIXME
 */
public class MyApplication  extends Application {

    private static MyApplication mApplication;

    private static  MyApplication getInstance(){
        if(mApplication==null){
            mApplication=new MyApplication();
        }
        return mApplication;
    }



    @Override
    public void onCreate() {
        super.onCreate();
        startBlackService();

    }

    private void startBlackService() {
        YunBaManager.start(getApplicationContext());

        IMqttActionListener listener = new IMqttActionListener() {

            @Override
            public void onSuccess(IMqttToken asyncActionToken) {
                String topic = DemoUtil.join(asyncActionToken.getTopics(), ",");
                Log.d("TAG", "Subscribe succeed : " + topic);
//				DemoUtil.showToast( "Subscribe succeed : " + topic, getApplicationContext());
                StringBuilder showMsg = new StringBuilder();
                showMsg.append("subscribe succ：").append(YunBaManager.MQTT_TOPIC)
                        .append(" = ").append(topic);
            }

            @Override
            public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                String msg =  "Subscribe failed : " + exception.getMessage();
                Log.d("TAG", msg);
//				DemoUtil.showToast(msg, getApplicationContext());
//

            }
        };

        //for test
        YunBaManager.subscribe(getApplicationContext(), new String[]{"t1", "t2", "t3"}, listener);
    }


    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    /**对方的位置*/
    private PoiItem personLocation;

    public PoiItem getPersonLocation() {
        return personLocation;
    }

    public void setPersonLocation(PoiItem personLocation) {
        this.personLocation = personLocation;
    }
}
