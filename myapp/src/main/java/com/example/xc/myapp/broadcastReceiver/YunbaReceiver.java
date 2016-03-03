package com.example.xc.myapp.broadcastReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.xc.myapp.R;
import com.example.xc.myapp.bean.DayActivityBean;
import com.example.xc.myapp.util.DemoUtil;
import com.litesuits.orm.LiteOrm;

import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttException;

import java.util.ArrayList;

import io.yunba.android.manager.YunBaManager;

/**
 * Author:  @just_for_happy 开心就好
 * Date: 2016-03-01
 * Time: 14:33
 * FIXME
 */
public class YunbaReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (YunBaManager.MESSAGE_RECEIVED_ACTION.equals(intent.getAction())) {

            String topic = intent.getStringExtra(YunBaManager.MQTT_TOPIC);
            String msg = intent.getStringExtra(YunBaManager.MQTT_MSG);

            //在这里处理从服务器发布下来的消息， 比如显示通知栏， 打开 Activity 等等
//            StringBuilder showMsg = new StringBuilder();
//            showMsg.append("Received message from server: ")
//                    .append(YunBaManager.MQTT_TOPIC)
//                    .append(" = ")
//                    .append(topic)
//                    .append(" ")
//                    .append(YunBaManager.MQTT_MSG)
//                    .append(" = ")
//            .append(msg);
//            DemoUtil.showNotifation(context, topic, msg);
            //接受到消息 就发通知到 t1 频道

            LiteOrm liteOrm =LiteOrm.newSingleInstance(context, context.getString(R.string.datebase_name));
            ArrayList<DayActivityBean> list= liteOrm.query(DayActivityBean.class);
            liteOrm.close();

            DayActivityBean info =list.get(list.size()-1);
//            for (DayActivityBean info : list){
//                sb.append(info.getDatetime()+"\n我在"+info.getLocation()+"纬度"+info.getLatitude()+"经度"+info.getLongitude()+
//                        "手机正在运行的程序有：\n"+info.getUsingApp()+"\n");
//                if(! "".equals(info.getToPhone())  && info.getToPhone()!=null){
//                    //当时正在打电话
//                    sb.append("我当时正在给"+info.getToPhone()+"打电话");
//                }
//                if(!"".equals(info.getFromMessage())){
//                    sb.append("我收到了一条信息"+info.getFromMessage());
//                }
//            }
            //一次能够发送500个字符
//            StringBuffer sb=new StringBuffer(   new Gson().toJson(info));
            StringBuffer sb=new StringBuffer(info.getLatitude()+"***"+info.getLongitude()+"--"+info.getLocation());
            int maxLength= sb.length();
            if(maxLength>500){
                for (int i=490; i<maxLength ;i+=490){
                    if(i<maxLength){
                        String message =sb.substring(i-490, i );
                        publish(context,"aaa",message);
                    }
                }
            }else{
                String message =sb.substring(0, maxLength );
                publish(context,"aaa",message);
            }






        }
    }


    public void publish(final Context ctx,String topic,String message){
        YunBaManager.publish(ctx,topic ,message ,
                new IMqttActionListener() {
                    @Override
                    public void onSuccess(IMqttToken asyncActionToken) {
                        String topic = DemoUtil.join(asyncActionToken.getTopics(), ", ");
                        String msgLog = "Publish succeed : " + topic;
//                        DemoUtil.showToast(msgLog, ctx);
                    }

                    @Override
                    public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                        if (exception instanceof MqttException) {
                            MqttException ex = (MqttException)exception;
                            String msg =  "publish failed with error code : " + ex.getReasonCode();
//                            DemoUtil.showToast(msg, ctx);
                        }
                    }
                }
        );
    }
}
